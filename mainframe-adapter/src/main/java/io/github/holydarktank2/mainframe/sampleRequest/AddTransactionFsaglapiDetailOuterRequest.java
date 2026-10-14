package io.github.holydarktank2.mainframe.sampleRequest;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsaglapiDetail
 */
@Getter
@Setter
@ToString
public class AddTransactionFsaglapiDetailOuterRequest implements Serializable {

  private static final long serialVersionUID = 8318993679153272752L;

  private String fsaglapiAccBrn;

  private String fsaglapiOppBrn;

  private String fsaglapiTxnNoSer;

  private String fsaglapiTlrKind;

  private String fsaglapi2751Curr;

  private String fsaglapiCurrCode;

  private String fsaglapiAccCode;

  private String fsaglapiSubCode;

  private String fsaglapiDbCrCode;

  private String fsaglapiTxnType;

  private BigDecimal fsaglapiTxnAmt;

  private String fsaglapiA832Kind;

  private String fsaglapi27103Kind;

}
