# mainframe-adapter-pattern

本專案示範如何在巨大 `AddTransactionOuterRequest` 下，使用
- 共用欄位套用器（applier）
- 情境策略（scenario strategy）
- 外部資料補齊層（context resolver）

來支援多個 `/api/ebanking` 情境，避免每個 API 都維護一支巨大 Assembler。

---

## `/api/ebanking` 現有 API

- `POST /api/ebanking/apply`
- `POST /api/ebanking/query`

兩者欄位組合幾乎相同，但資料來源與情境規則不同。

---

## 1) Service 到外部呼叫的責任分工

### Controller
只接前端 DTO，呼叫 facade service。

### Facade Service
流程固定：
1. `OnlineBankingContextResolver` 做資料補齊
2. `OnlineBankingRequestAssembler` 組 `AddTransactionOuterRequest`
3. `BankCenterTransactionClient` 呼叫銀行
4. `OnlineBankingResponseMapper` 轉前端回應

### Context Resolver（外部資料補齊層）
整合三種來源：
- `CustomerReferenceProvider`（可對應 DB）
- `BankMessageProvider`（可對應銀行電文）
- `OtherSystemProvider`（其他外部系統）

輸出 `OnlineBankingEnrichedContext`，供後續組裝使用。

### Request Assembler
只做組裝，吃 `OnlineBankingEnrichedContext` + 情境策略。

### Scenario Strategy
- `OnlineBankingApplyScenarioStrategy`
- `OnlineBankingQueryScenarioStrategy`

兩者共用同一條組裝主流程，只在差異欄位做覆蓋。

### Client
`BankCenterTransactionClient` 負責真正對外傳輸（HTTP/SOAP/Host）。

---

## 2) 解決「欄位過多」的方法

### A. 共用 applier
- `OuterRequestCommonFieldApplier`：外層共用欄位
- `Foqapi00CommonFieldApplier`：FOQAPI00 共同欄位

### B. 情境策略
- apply/query 共用組裝主流程
- 差異由 strategy 注入，避免複製整支 assembler

### C. 外部資料補齊層
- service 不直接散落 DB/電文/外部系統呼叫
- 統一在 resolver 取值與整併，降低耦合

---

## 3) 為什麼不是 100 支從零開始的 Assembler

本專案採用：
- 少量共用 applier
- 薄情境 assembler
- 多個情境 strategy

因此擴充 100 種情境時，通常只要新增：
1. 一個情境 strategy（必要時）
2. 少量情境差異欄位邏輯
3. 測試

而不是複製 100 次大量 setter。

---

## 4) 優缺點

### 優點
- 欄位重複明顯下降
- apply/query 可共用主流程
- 外部資料來源邏輯集中、可測試
- 便於擴充新情境

### 缺點
- 類別數增加
- 初期設計成本較高
- 需處理外部來源失敗策略（retry/fallback/降級）

---

## 5) 主要類別

### API
- `OnlineBankingApplyController`
- `OnlineBankingQueryController`

### Facade
- `OnlineBankingApplyFacadeService`
- `OnlineBankingQueryFacadeService`

### Context
- `OnlineBankingContextResolver`
- `OnlineBankingEnrichedContext`
- `CustomerReferenceProvider` / `BankMessageProvider` / `OtherSystemProvider`

### Assembly
- `OnlineBankingRequestAssembler`
- `Foqapi00CommonFieldApplier`
- `OnlineBankingApplyScenarioStrategy`
- `OnlineBankingQueryScenarioStrategy`

---

## 6) 測試

```bash
cd /home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter
mvn test
```

包含：
- Context resolver 多來源整併與失敗測試
- apply/query 策略差異測試
- facade 流程與錯誤行為測試
- controller 回應測試

