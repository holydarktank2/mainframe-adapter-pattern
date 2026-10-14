package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsfratio
 */
@Getter
@Setter
@ToString
public class AddTransactionFsfratioOuterRequest implements Serializable {

  private String fsfratioProcMod;

  private String fsfratioReturnCod;

  private String fsfratioFileUpdateMk;

  private String fsfratioDealType;

  private String fsfratioRefNo;

  private String fsfratioInitTime;

  private String fsfratioTxCod;

  private String fsfratioOldBranch;

  private String fsfratioOldBusType;

  private String fsfratioOldDealBase;

  private String fsfratioOldCurencyBidden;

  private String fsfratioOldCurencyB;

  private String fsfratioOldCurencyS;

  private BigDecimal fsfratioOldAmountB;

  private BigDecimal fsfratioOldAmountS;

  private BigDecimal fsfratioOldSpotFxrate;

  private BigDecimal fsfratioOldCostRate;

  private BigDecimal fsfratioOldMidFxrateQuot;

  private String fsfratioOldEmployeeMk;

  private String fsfratioNewBranch;

  private String fsfratioNewBusType;

  private String fsfratioNewDealBase;

  private String fsfratioNewCurencyBidden;

  private String fsfratioNewCurencyB;

  private String fsfratioNewCurencyS;

  private BigDecimal fsfratioNewAmountB;

  private BigDecimal fsfratioNewAmountS;

  private BigDecimal fsfratioNewSpotFxrate;

  private BigDecimal fsfratioNewCostRate;

  private BigDecimal fsfratioNewMidFxrateQuot;

  private String fsfratioNewEmployeeMk;

  private BigDecimal fsfratioDelCostRate;

  private String fsfratioDelReason;

  private BigDecimal fsfratioLargeUsAmt;

  private String fsfratioNoticeNo;

  private String fsfratioFxrateType;

  private String fsfratioProcessBrn;

  private String fsfratioDealKind;

  private String fsfratioOperatorId;

  private String fsfratioFiller;
}
