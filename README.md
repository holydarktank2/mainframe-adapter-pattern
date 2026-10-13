# mainframe-adapter-pattern

銀行交易 API 轉換層架構示例，使用 Adapter/Converter + Builder Pattern，將 Domain 請求轉換為統一的 `AddTransactionOuterRequest`。

## 專案結構

- `/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/core`
  - `TransactionConverter`：抽象轉換介面
  - `GenericBuilder`：通用 Builder
  - `FieldMapping`：欄位映射註解
  - `FieldMappingSupport`：註解驅動映射與必填驗證
- `/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/domain`
  - `ForwardRemittanceRequest`
  - `SpotRemittanceRequest`
  - `OnlineBankingRequest`
  - `ForeignExchangeRequest`
- `/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/converter`
  - 各業務域 Converter 實作
- `/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/outer`
  - `AddTransactionOuterRequest`（統一外部 API 請求模型）
- `/mainframe-adapter/src/test`
  - 轉換與驗證單元測試

## 使用方式

```java
ForwardRemittanceRequest request = ForwardRemittanceRequest.builder()
    .refNo("TX001")
    .contractNo("C001")
    .dealType("FWD")
    .amount(new BigDecimal("1000.00"))
    .build();

AddTransactionOuterRequest outerRequest =
    new ForwardRemittanceConverter().convert(request);

// bankApi.addTransaction(outerRequest);
```

## 測試

```bash
cd /home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter
mvn test
```
