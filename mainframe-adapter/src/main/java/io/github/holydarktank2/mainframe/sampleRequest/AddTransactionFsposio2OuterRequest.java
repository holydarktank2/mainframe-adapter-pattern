package io.github.holydarktank2.mainframe.sampleRequest;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsposio2
 */
@Getter
@Setter
@ToString
public class AddTransactionFsposio2OuterRequest implements Serializable {

  private String fsposio2ProcessMod;

  private String fsposio2BuyOrSell;

  private String fsposio2BranchNo;

  private String fsposio2TxType;

  private String fsposio2NoticeTrn;

  private String fsposio2OldTxTime;

  private String fsposio2OldBusinessType;

  private String fsposio2OldRecvBranch;

  private String fsposio2OldFxrateMk;

  private String fsposio2OldDealingDate;

  private String fsposio2OldNoticeMk;

  private String fsposio2OldNoticeValueMk;

  private String fsposio2OldCurency;

  private BigDecimal fsposio2OldFxAmt;

  private BigDecimal fsposio2OldNtAmt;

  private String fsposio2NewTxTime;

  private String fsposio2NewBusinessType;

  private String fsposio2NewRecvBranch;

  private String fsposio2NewFxrateMk;

  private String fsposio2NewDealingDate;

  private String fsposio2NewNoticeMk;

  private String fsposio2NewNoticeValueMk;

  private String fsposio2NewCurency;

  private BigDecimal fsposio2NewFxAmt;

  private BigDecimal fsposio2NewNtAmt;

  private String fsposio2AdjustMk;

  private BigDecimal fsposio2AdjustFxAmt;

  private BigDecimal fsposio2AdjustNtAmt;

  private String fsposio2RollbackMk;

  private String fsposio2ReturnCod;

  private String fsposio2AdjustMkC;

  private String fsposio2PositionUnlock;

  private String fsposio2RefNo;

  private String fsposio2NoticeNo;

  private String fsposio2PermitNego;

  private String fsposio2TxCode;

  private String fsposio2DealStatus;

  private String fsposio2DealType;

  private String fsposio2SightForwardMk;

  private String fsposio2OperatorId;

  private BigDecimal fsposio2NegoFxrate;

  private BigDecimal fsposio2CostFxrate;

  private BigDecimal fsposio2ToUsFxrate;

  private BigDecimal fsposio2DelCostrate;

  private String fsposio2DataMark;

  private String fsposio2OldOtherExchg;

  private String fsposio2OldEmployeeMk;

  private String fsposio2OldNoticeRecvDate;

  private String fsposio2OldNoticeValueDate;

  private String fsposio2OldFdsnegoMk;

  private String fsposio2NewOtherExchg;

  private String fsposio2NewEmployeeMk;

  private String fsposio2NewNoticeRecvDate;

  private String fsposio2NewNoticeValueDate;

  private String fsposio2NewFdsnegoMk;

  private String fsposio2TxnNo;

  private String fsposio2CurencyBidden;

  private Integer fsposio2ExchgLinkCnt;

  private String fsposio2OldCurencyOt;

  private BigDecimal fsposio2OldFxAmtOt;

  private BigDecimal fsposio2OldMidFxrateBid;

  private BigDecimal fsposio2OldMidFxrateQuot;

  private String fsposio2NewCurencyOt;

  private BigDecimal fsposio2NewFxAmtOt;

  private BigDecimal fsposio2NewMidFxrateBid;

  private BigDecimal fsposio2NewMidFxrateQuot;

  private BigDecimal fsposio2MarketRateUsNt;

  private BigDecimal fsposio2CostRateUsNt;

  private BigDecimal fsposio2CostRateUsFx;

  private BigDecimal fsposio2CurencySUsBid;

  private BigDecimal fsposio2SpotFxrate;

  private BigDecimal fsposio2SpotCostFxrate;

  private BigDecimal fsposio2SwapPoint;

  private BigDecimal fsposio2OriginalSwapPoint;

  private String fsposio2SightToForwardMk;

  private BigDecimal fsposio2SwapUpfrontFee;

  private BigDecimal fsposio2OSwapUpfrontFee;

  private String fsposio2DownType;

  private Integer fsposio2DownPeriod;

  private String fsposio2AssignDate;

  private String fsposio2FwdRateDay;

  private String fsposio2FwdBusType;

  private String fsposio2SeqNo;

  private String fsposio2OldCnyMk;

  private String fsposio2NewCnyMk;

  private String fsposio2BusinessId;

  private String fsposio2NoticeCnyMk;

  private Integer fsposio2DownPeriodRedef;

  private String fsposio2Filler;
}
