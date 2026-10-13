package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionFoqapi00OuterRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.springframework.stereotype.Component;

@Component
public class OnlineBankingApplyRequestAssembler {
    private final OuterRequestCommonFieldApplier outerRequestCommonFieldApplier;

    public OnlineBankingApplyRequestAssembler(OuterRequestCommonFieldApplier outerRequestCommonFieldApplier) {
        this.outerRequestCommonFieldApplier = outerRequestCommonFieldApplier;
    }

    public AddTransactionOuterRequest assemble(OnlineBankingApplyFrontendRequest frontendRequest) {
        AddTransactionOuterRequest outerRequest = new AddTransactionOuterRequest();

        outerRequestCommonFieldApplier.apply(
                outerRequest,
                frontendRequest.txnCode(),
                frontendRequest.txnUniqueKey(),
                frontendRequest.processBrnNo(),
                frontendRequest.processBrnId(),
                frontendRequest.processBrnType(),
                frontendRequest.processMod(),
                frontendRequest.empNo(),
                frontendRequest.opId(),
                frontendRequest.termId(),
                frontendRequest.businessDate()
        );

        outerRequest.setBegin8("BEGIN8");
        outerRequest.setPgmName8("FOQAPI00");
        outerRequest.setEnd8("END8");

        AddTransactionFoqapi00OuterRequest foqapi00 = outerRequest.getFoqapi00();
        foqapi00.setFoqapi00ApplicantId(frontendRequest.applicantId());
        foqapi00.setFoqapi00OpId(frontendRequest.opId());
        foqapi00.setFoqapi00FuncCod(frontendRequest.funcCod());
        foqapi00.setFoqapi00BusinessCod(frontendRequest.businessCod());
        foqapi00.setFoqapi00InOutward(frontendRequest.inOutward());
        foqapi00.setFoqapi00FxNo(frontendRequest.fxNo());
        foqapi00.setFoqapi00NoticeNo(frontendRequest.noticeNo());
        foqapi00.setFoqapi00SpecBank(frontendRequest.specBank());
        foqapi00.setFoqapi00DocuNo(frontendRequest.docuNo());
        foqapi00.setFoqapi00Qualify(frontendRequest.qualify());
        foqapi00.setFoqapi00BirthDate(frontendRequest.birthDate());
        foqapi00.setFoqapi00IssueDate(frontendRequest.issueDate());
        foqapi00.setFoqapi00ExpireDate(frontendRequest.expireDate());
        foqapi00.setFoqapi00CountryNo(frontendRequest.countryNo());
        foqapi00.setFoqapi00SpotFxCurency(frontendRequest.spotFxCurrency());
        foqapi00.setFoqapi00SpotFxAmount(frontendRequest.spotFxAmount());
        foqapi00.setFoqapi00SpotNtAmount(frontendRequest.spotNtAmount());
        foqapi00.setFoqapi00SpotToUsdAmt(frontendRequest.spotToUsdAmt());
        foqapi00.setFoqapi00ContractNo(frontendRequest.contractNo());
        foqapi00.setFoqapi00BuySell(frontendRequest.buySell());
        foqapi00.setFoqapi00TradeCode(frontendRequest.tradeCode());
        foqapi00.setFoqapi00AttributeCode(frontendRequest.attributeCode());
        foqapi00.setFoqapi00ToUsFxrate(frontendRequest.toUsFxRate());
        foqapi00.setFoqapi00UseOfFunds(frontendRequest.useOfFunds());

        return outerRequest;
    }
}
