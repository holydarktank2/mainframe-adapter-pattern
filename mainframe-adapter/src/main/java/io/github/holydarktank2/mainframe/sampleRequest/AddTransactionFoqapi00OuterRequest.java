package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFoqapi00
 */
@Getter
@Setter
@ToString
public class AddTransactionFoqapi00OuterRequest implements Serializable {

  private String foqapi00ReturnCod;

  private String foqapi00ReasonCod;

  private String foqapi00BankCode;

  private String foqapi00SerialNo;

  private String foqapi00Noc1ToTgic;

  private String foqapi00FuncCod;

  private String foqapi00BusinessCod;

  private String foqapi00ProcessBrn;

  private String foqapi00InOutward;

  private String foqapi00ApplicantId;

  private String foqapi00OpId;

  private String foqapi00FxNo;

  private String foqapi00NoticeNo;

  private String foqapi00SpecBank;

  private String foqapi00DocuNo;

  private String foqapi00Qualify;

  private String foqapi00NoRepFlag;

  private String foqapi00AprovNoFlag;

  private String foqapi00BirthDate;

  private String foqapi00IssueDate;

  private String foqapi00ExpireDate;

  private String foqapi00PropertyNo;

  private String foqapi00SubClass;

  private String foqapi00CountryNo;

  private String foqapi00SpotFxCurency;

  private BigDecimal foqapi00SpotFxAmount;

  private BigDecimal foqapi00SpotNtAmount;

  private BigDecimal foqapi00SpotToUsdAmt;

  private String foqapi00ApToTgic;

  private String foqapi00ContractNo;

  private String foqapi00ExtendNo;

  private String foqapi00BuySell;

  private String foqapi00TradeCode;

  private String foqapi00AttributeCode;

  private String foqapi00FfExpiryDate;

  private BigDecimal foqapi00FfRate;

  private Integer foqapi00PartialSeqNo;

  private String foqapi00FfBancode;

  private BigDecimal foqapi00ToUsFxrate;

  private String foqapi00OrigClass;

  private String foqapi00OrigClassSub;

  private String foqapi00UseOfFunds;

  private String foqapi00Filler;
}
