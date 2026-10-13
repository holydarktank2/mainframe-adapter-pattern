# mainframe-adapter-pattern

此專案示範如何把多功能銀行交易（匯款、網銀申請、外匯等）統一走 `addTransaction` 外部 API，並讓各 Domain 只關注自己的欄位。

---

## 1) 問題與目標

銀行主機 API `AddTransactionOuterRequest`/`AddTransactionOuterResponse` 是統一入口，實務上會非常大（上百到上千欄位）。

目標：
- Domain service 不直接操作巨大外部模型
- 每個功能只定義自己需要的 request/response
- 集中轉換邏輯，避免散落在各處
- 提供初階工程師可直接照抄的使用流程

---

## 2) 簡化後設計（初階工程師優先）

> 為了好上手，這份實作優先採「明確類別 + 固定流程」：
> - Domain Request → Converter → `AddTransactionOuterRequest`
> - 呼叫銀行 API
> - `AddTransactionOuterResponse` → Response Converter → Domain Response

主要類別：

- 外部統一模型
  - `AddTransactionOuterRequest`
  - `AddTransactionOuterResponse`
  - `BankApiClient`（你實作真正 HTTP/SOAP/Host 呼叫）

- 4 組 Domain Request
  - `ForwardRemittanceRequest`
  - `SpotRemittanceRequest`
  - `OnlineBankingRequest`
  - `ForeignExchangeRequest`

- 4 組 Domain Response
  - `ForwardRemittanceResponse`
  - `SpotRemittanceResponse`
  - `OnlineBankingResponse`
  - `ForeignExchangeResponse`

- 4 組 Request Converter + 4 組 Response Converter
  - `ForwardRemittanceConverter` / `ForwardRemittanceResponseConverter`
  - `SpotRemittanceConverter` / `SpotRemittanceResponseConverter`
  - `OnlineBankingConverter` / `OnlineBankingResponseConverter`
  - `ForeignExchangeConverter` / `ForeignExchangeResponseConverter`

- 統一入口服務（最簡單用法）
  - `SimpleBankTransactionService`

---

## 3) 如何建立「各功能外部呼叫」並轉成統一呼叫

### Step A. Domain 只建立自己的 request

```java
ForwardRemittanceRequest request = ForwardRemittanceRequest.builder()
    .refNo("TX001")
    .contractNo("CTR001")
    .dealType("FWD")
    .amount(new BigDecimal("2000"))
    .build();
```

### Step B. 準備銀行 API 呼叫實作

```java
BankApiClient bankApiClient = outerRequest -> {
    // 這裡接你的真實銀行 API 呼叫
    AddTransactionOuterResponse response = new AddTransactionOuterResponse();
    response.setTransactionType(outerRequest.getTransactionType());
    response.setResponseCode("0000");
    response.setResponseMessage("SUCCESS");
    response.putSectionField("basic", "refNo", outerRequest.getSection("basic").get("refNo"));
    response.putSectionField("remittance", "bankSerialNo", "BK20261013001");
    return response;
};
```

### Step C. 用簡化服務直接執行

```java
SimpleBankTransactionService service = new SimpleBankTransactionService();
ForwardRemittanceResponse result = service.executeForwardRemittance(request, bankApiClient);
```

> 初階工程師建議：先只用 `SimpleBankTransactionService`，不要一開始就自己拼裝所有 converter。

---

## 4) 擴充新功能（照這個模板）

假設你要新增「股票查詢」：

1. 新增 `StockInquiryRequest`（Domain 欄位）
2. 新增 `StockInquiryResponse`
3. 新增 `StockInquiryConverter`（Request -> OuterRequest）
4. 新增 `StockInquiryResponseConverter`（OuterResponse -> DomainResponse）
5. 在 `SimpleBankTransactionService` 新增 `executeStockInquiry(...)`
6. 新增對應測試，至少驗證：
   - request 關鍵欄位有被映射
   - response 關鍵欄位有被反解
   - validation 失敗時會丟 `ConversionException`

---

## 5) 驗證與測試

```bash
cd /home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter
mvn test
```

現有測試：
- `TransactionConvertersTest`：各功能 request 轉換與驗證
- `SimpleBankTransactionServiceTest`：完整 request→外部呼叫→response 轉換流程

---

## 6) 專案路徑

- `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe`
- `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/test/java/io/github/holydarktank2/mainframe`

