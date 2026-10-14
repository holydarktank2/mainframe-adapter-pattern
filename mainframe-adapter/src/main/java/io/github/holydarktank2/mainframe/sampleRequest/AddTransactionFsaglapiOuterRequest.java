package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsaglapi
 */
@Getter
@Setter
@ToString
public class AddTransactionFsaglapiOuterRequest implements Serializable {

  private static final long serialVersionUID = 1747807453070574620L;

  private String fsaglapiReturnCode;

  private String fsaglapiTxnDate;

  private String fsaglapiBrCode;

  private String fsaglapiTmid;

  private String fsaglapiTxnNo;

  private String fsaglapiTlrNo;

  private String fsaglapiAccNo;

  private String fsaglapiTxnStat;

  private String fsaglapiSupCode;

  private String fsaglapiChanCode;

  private String fsaglapiOrgTxnNo;

  private String fsaglapiSevTlr;

  private List<AddTransactionFsaglapiDetailOuterRequest> detail;

  private String fsaglapiNegFlag;

  private String fsaglapiCheckPay;

  private String fsaglapiRecvBrn;

  private String fsaglapiTxCode;

  private String fsaglapi2751Type;

  private String fsaglapiVirTlr;

  private String fsaglapiIbNo;

  private String fsaglapiRealTlrMk;

  private String fsaglapiRepNo;

  private String fsaglapiDoubleFlag;

  private String fsaglapiFiller;

  /**
   * 預設值
   */
  public AddTransactionFsaglapiOuterRequest() {
    this.detail = new ArrayList<>();
  }
}
