# mainframe-adapter-pattern

本專案示範：
- 前端送入一筆「遠期匯款」請求
- 後端如何依照銀行中心真實欄位（`sampleRequest`）組出 `AddTransactionOuterRequest`
- 呼叫銀行中心後，如何把 `AddTransactionOuterResponse` 轉成前端需要的回應

> 目前示範功能：**Forward Remittance（FSFRMTIO）**

---

## 1. 真實銀行模型來源

實際欄位使用以下類別（由你提供）：

- `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/sampleRequest/AddTransactionOuterRequest.java`
- `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/sampleRequest/AddTransactionOuterResponse.java`
- `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/sampleRequest/AddTransactionFsfrmtioOuterRequest.java`

---

## 2. 端到端元件（前端 -> 後端 -> 銀行 -> 前端）

### (A) 前端進入點：Controller

- `ForwardRemittanceController`
- API：`POST /api/remittance/forward`
- 收 `ForwardRemittanceFrontendRequest`，回 `ForwardRemittanceFrontendResponse`

### (B) 用例服務：Facade Service

- `ForwardRemittanceFacadeService`
- 責任：
  1. 呼叫 `ForwardRemittanceRequestAssembler` 把前端 DTO 轉成 `AddTransactionOuterRequest`
  2. 呼叫 `BankCenterTransactionClient.addTransaction(...)` 發送到銀行中心
  3. 呼叫 `ForwardRemittanceResponseMapper` 把外部回應轉回前端 DTO

### (C) 請求組裝：Assembler

- `ForwardRemittanceRequestAssembler`
- 重點：
  - 設定外層交易欄位：`txnCode / txnUniqueKey / processBrnNo / empNo / ...`
  - 設定 FSFRMTIO 區塊啟用標記：`begin13 / pgmName13 / end13`
  - 填入 `fsfrmtio` 區塊真實欄位：
    - `fsfrmtioRefNo`
    - `fsfrmtioTxCod`
    - `fsfrmtioProcessBranch`
    - `fsfrmtioInwardBranch`
    - `fsfrmtioCurency`
    - `fsfrmtioNewInterbnchNet`
    - `fsfrmtioNewInterbnchRemit`
    - `fsfrmtioOldInterbnchNet`
    - `fsfrmtioOldInterbnchRemit`

### (D) 回應轉換：Mapper

- `ForwardRemittanceResponseMapper`
- 從 `AddTransactionOuterResponse` 取值並回給前端：
  - `outReturnCode`
  - `outErrorMsg`
  - `outTxnNo`
  - `outSerialNo`
  - `outNoticeNo`
  - `outCustomerNo`
  - `outFwdContNo1`
  - `outFwdValueFx1 / outFwdValueNt1 / outFwdValueUs1`

---

## 3. 實際流程（逐步）

1. 前端呼叫 `POST /api/remittance/forward`
2. Controller 收到 `ForwardRemittanceFrontendRequest`
3. Service 呼叫 Assembler 轉成 `AddTransactionOuterRequest`
4. Service 呼叫 `BankCenterTransactionClient` 送銀行中心
5. 銀行中心回 `AddTransactionOuterResponse`
6. Service 呼叫 Mapper 轉成 `ForwardRemittanceFrontendResponse`
7. Controller 回傳 JSON 給前端

---

## 4. 關鍵程式路徑

- Controller
  - `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/controller/ForwardRemittanceController.java`
- Service
  - `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/application/ForwardRemittanceFacadeService.java`
- Request Assembler
  - `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/integration/ForwardRemittanceRequestAssembler.java`
- Response Mapper
  - `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/integration/ForwardRemittanceResponseMapper.java`
- Frontend DTO
  - `/home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter/src/main/java/io/github/holydarktank2/mainframe/controller/dto/`

---

## 5. 測試

```bash
cd /home/runner/work/mainframe-adapter-pattern/mainframe-adapter-pattern/mainframe-adapter
mvn test
```

新增測試：
- `ForwardRemittanceRequestAssemblerTest`
- `ForwardRemittanceFacadeServiceTest`
- `ForwardRemittanceControllerTest`

---

## 6. 你要擴充其他功能時

如果要擴充即期匯款/網銀申請/外匯交易，請複製同樣模式：

1. 新增 frontend request/response DTO
2. 新增對應 request assembler（填對應 sampleRequest 子區塊）
3. 新增 response mapper（讀對應 response 欄位）
4. 新增 facade service 方法
5. 新增 controller endpoint

