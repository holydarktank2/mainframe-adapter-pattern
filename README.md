# mainframe-adapter-pattern

本專案示範：同一支銀行中心 `AddTransactionOuterRequest`，如何支援多種完全不同功能，以及相似功能共享大量欄位。

---

## 1. 真實銀行模型來源（sampleRequest）

使用你提供的真實欄位模型：

- `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/sampleRequest/AddTransactionOuterRequest.java`
- `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/sampleRequest/AddTransactionOuterResponse.java`

---

## 2. 新增多個 Controller 示範的兩種情境

## 2.1 完全不同功能（使用不同區塊）

### A. 網銀申請 Controller
- Controller: `OnlineBankingApplyController`
- Endpoint: `POST /api/ebanking/apply`
- Assembler: `OnlineBankingApplyRequestAssembler`
- 主要區塊：`foqapi00`（`begin8/pgmName8/end8`）

這條流程主要操作 `foqapi00` 欄位（例如 applicantId、docuNo、spotFxAmount），與匯款流程用到的區塊不同。

## 2.2 相似功能（大量相同欄位）

### B. 遠期匯款 Controller
- Controller: `ForwardRemittanceController`
- Endpoint: `POST /api/remittance/forward`
- 主要區塊：`fsfrmtio`（`begin13/pgmName13/end13`）

### C. 即期匯款 Controller
- Controller: `SpotRemittanceController`
- Endpoint: `POST /api/remittance/spot`
- 主要區塊：同樣是 `fsfrmtio`

兩條流程都大量使用相同 `fsfrmtio` 欄位，只在少數欄位（例如 `ecMk`）或流程用途不同。

---

## 3. Service 到外部服務，如何解決欄位過多

本專案採「分層組裝 + 共用欄位套用器」：

1. **Controller（前端入口）**
   - 只收/回前端 DTO，不處理銀行欄位細節。

2. **Facade Service（用例層）**
   - 統一流程：
     - `RequestAssembler` 組外部請求
     - `BankCenterTransactionClient` 呼叫銀行
     - `ResponseMapper` 轉回前端 DTO

3. **Request Assembler（場景層）**
   - 每個業務場景保留自己的組裝規則。
   - 只處理「該場景專屬」欄位。

4. **Common Field Applier（共享欄位層）**
   - `OuterRequestCommonFieldApplier`：統一套用外層共用欄位（txnCode、empNo、businessDate...）
   - `FsfrmtioCommonFieldApplier`：統一套用 `fsfrmtio` 常見欄位

5. **BankCenterTransactionClient（外部整合邊界）**
   - 將 HTTP/SOAP/Host 細節隔離在介面後方，讓業務邏輯不耦合 transport 細節。

---

## 4. 「100 種情境要 100 個 Assembler 嗎？」

不建議做成 100 個巨大且完全重複的 Assembler。

本專案示範的做法是：

- **保留場景 Assembler**（可讀性高，方便維護）
- 但把大量重複 setter 抽成 **Common Field Applier**
- 讓新場景只需：
  1. 決定要啟用哪個區塊（例如 begin8 / begin13）
  2. 寫少量場景差異欄位
  3. 重用共用欄位套用器

也就是：
- 不是「100 個從零開始」
- 而是「100 個薄場景 Assembler + 少數可重用欄位元件」

---

## 5. 這個方法的優缺點

### 優點
1. **可讀性高**：每個 Controller/Service 對應一個業務場景，初階工程師容易追。
2. **修改風險可控**：某場景調整欄位時，影響範圍小。
3. **共用避免重複**：透過 Common Field Applier 降低重複 setter。
4. **測試清楚**：Assembler/Service/Controller 可分層單測。
5. **外部依賴隔離**：`BankCenterTransactionClient` 便於替換與 mock。

### 缺點
1. **類別數量變多**：場景越多，檔案數量越多。
2. **初期設計成本較高**：要先定義 DTO、Assembler、Mapper、Service。
3. **欄位規格變更時需同步更新多處**：DTO、Assembler、測試需一起改。
4. **非零執行成本**：多一層映射/組裝（通常可接受）。

---

## 6. 端到端流程（前端 -> 後端 -> 銀行 -> 前端）

以 `POST /api/remittance/forward` 為例：

1. 前端送 `ForwardRemittanceFrontendRequest`
2. `ForwardRemittanceController` 呼叫 `ForwardRemittanceFacadeService`
3. Service 呼叫 `ForwardRemittanceRequestAssembler`
4. Assembler 內部使用：
   - `OuterRequestCommonFieldApplier`
   - `FsfrmtioCommonFieldApplier`
   組成 `AddTransactionOuterRequest`
5. Service 呼叫 `BankCenterTransactionClient.addTransaction(...)`
6. 收到 `AddTransactionOuterResponse`
7. `ForwardRemittanceResponseMapper` 轉成 `ForwardRemittanceFrontendResponse`
8. Controller 回傳前端

---

## 7. 主要類別清單

### Controllers
- `ForwardRemittanceController`
- `SpotRemittanceController`
- `OnlineBankingApplyController`

### Services
- `ForwardRemittanceFacadeService`
- `SpotRemittanceFacadeService`
- `OnlineBankingApplyFacadeService`

### Assemblers / Mappers
- `ForwardRemittanceRequestAssembler`
- `SpotRemittanceRequestAssembler`
- `OnlineBankingApplyRequestAssembler`
- `ForwardRemittanceResponseMapper`
- `SpotRemittanceResponseMapper`
- `OnlineBankingApplyResponseMapper`

### Shared appliers
- `OuterRequestCommonFieldApplier`
- `FsfrmtioCommonFieldApplier`

---

## 8. 測試

```bash
cd /home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter
mvn test
```

