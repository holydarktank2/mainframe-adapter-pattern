package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsatxlst
 */
@Getter
@Setter
@ToString
public class AddTransactionFsatxlstOuterRequest implements Serializable {

  private String fsatxlstReturnCod;

  private String fsatxlstProcessMod;

  private String fsatxlstProcessBrn;

  private String fsatxlstEmpNo;

  private String fsatxlstTxCod;

  private String fsatxlstCustId;

  private String fsatxlstFxNo;

  private String fsatxlstSeqNo;

  private String fsatxlstGlifTxno;

  private String fsatxlstCurency;

  private BigDecimal fsatxlstTxnAmt;

  private BigDecimal fsatxlstRecAmtFx;

  private BigDecimal fsatxlstRecAmtNt;

  private String fsatxlstAcBrn;

  private String fsatxlstRecvBrn;

  private String fsatxlstBusType;

  private String fsatxlstLargeSettleMk;

  private BigDecimal fsatxlstLargeSettleUs;

  private String fsatxlstFiller;
}
