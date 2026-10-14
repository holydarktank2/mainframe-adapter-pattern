package io.github.holydarktank2.mainframe.sampleRequest;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFofsrnew
 */
@Getter
@Setter
@ToString
public class AddTransactionFofsrnewOuterRequest implements Serializable {

  private String fofsrnewFunc;

  private String fofsrnewRefNo;

  private String fofsrnewIbSeqNo;

  private String fofsrnewValueSeqNo;

  private String fofsrnewReturnCod;

  private String fofsrnewReceivingBrn;

  private String fofsrnewCustomerNo;

  private String fofsrnewCoBank;

  private String fofsrnewForwardType;

  private String fofsrnewSwapCurency;

  private String fofsrnewFil2;

  private String fofsrnewValueDate;

  private String fofsrnewOperatorId;

  private BigDecimal fofsrnewFxRate;

  private String fofsrnewCurency;

  private String fofsrnewFxNo;

  private String fofsrnewExtendNo;

  private String fofsrnewOldFxNo;

  private String fofsrnewOldExtendNo;

  private BigDecimal fofsrnewFxAmt;

  private BigDecimal fofsrnewOodFxAmt;

  private BigDecimal fofsrnewNtdAmt;

  private BigDecimal fofsrnewOldNtdAmt;

  private String fofsrnewValueRelFxCod;

  private String fofsrnewValueRelFxNo;

  private String fofsrnewUpdateMk;

  private String fofsrnewWwDliuib;

  private String fofsrnewScheduleMk;

  private String fofsrnewRelTxCod;

  private BigDecimal fofsrnewToNtFxRateB;

  private BigDecimal fofsrnewToNtFxRateS;

  private BigDecimal fofsrnewValueNtAmt;

  private String fofsrnewDataMark;

  private String fofsrnewProcessBranchId;

  private String fofsrnewFil;
}
