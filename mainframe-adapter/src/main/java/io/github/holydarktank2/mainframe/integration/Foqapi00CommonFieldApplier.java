package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.application.context.OnlineBankingEnrichedContext;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionFoqapi00OuterRequest;
import org.springframework.stereotype.Component;

@Component
public class Foqapi00CommonFieldApplier {

    public void apply(AddTransactionFoqapi00OuterRequest foqapi00, OnlineBankingEnrichedContext context) {
        var request = context.frontendRequest();

        foqapi00.setFoqapi00ApplicantId(request.applicantId());
        foqapi00.setFoqapi00OpId(request.opId());
        foqapi00.setFoqapi00FuncCod(request.funcCod());
        foqapi00.setFoqapi00BusinessCod(request.businessCod());
        foqapi00.setFoqapi00InOutward(request.inOutward());
        foqapi00.setFoqapi00FxNo(request.fxNo());
        foqapi00.setFoqapi00NoticeNo(request.noticeNo());
        foqapi00.setFoqapi00SpecBank(request.specBank());
        foqapi00.setFoqapi00DocuNo(request.docuNo());
        foqapi00.setFoqapi00Qualify(request.qualify());
        foqapi00.setFoqapi00BirthDate(request.birthDate());
        foqapi00.setFoqapi00IssueDate(request.issueDate());
        foqapi00.setFoqapi00ExpireDate(request.expireDate());
        foqapi00.setFoqapi00CountryNo(request.countryNo());
        foqapi00.setFoqapi00SpotFxCurency(request.spotFxCurrency());
        foqapi00.setFoqapi00SpotFxAmount(request.spotFxAmount());
        foqapi00.setFoqapi00SpotNtAmount(request.spotNtAmount());
        foqapi00.setFoqapi00SpotToUsdAmt(request.spotToUsdAmt());
        foqapi00.setFoqapi00ContractNo(request.contractNo());
        foqapi00.setFoqapi00BuySell(request.buySell());
        foqapi00.setFoqapi00TradeCode(request.tradeCode());
        foqapi00.setFoqapi00AttributeCode(request.attributeCode());
        foqapi00.setFoqapi00ToUsFxrate(request.toUsFxRate());
        foqapi00.setFoqapi00UseOfFunds(context.otherSystemData().useOfFundsOverride());

        foqapi00.setFoqapi00BankCode(context.customerReferenceData().bankCode());
        foqapi00.setFoqapi00Noc1ToTgic(context.customerReferenceData().noc1ToTgic());
        foqapi00.setFoqapi00ProcessBrn(context.customerReferenceData().processBranchFromDb());
        foqapi00.setFoqapi00SerialNo(context.bankMessageData().serialNo());
        foqapi00.setFoqapi00ReasonCod(context.bankMessageData().reasonCode());
        foqapi00.setFoqapi00NoRepFlag(context.bankMessageData().noRepFlag());
        foqapi00.setFoqapi00AprovNoFlag(context.otherSystemData().aprovNoFlag());
    }
}
