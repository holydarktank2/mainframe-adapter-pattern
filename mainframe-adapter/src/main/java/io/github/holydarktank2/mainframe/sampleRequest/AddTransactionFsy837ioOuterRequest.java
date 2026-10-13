package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsy837io
 */
@Getter
@Setter
@ToString
public class AddTransactionFsy837ioOuterRequest implements Serializable {

  private String fsy837ioReturnCod;

  private String fsy837ioProcessMod;

  private String fsy837ioTermId;

  private String fsy837ioProcessBrn;

  private String fsy837ioAcBrn;

  private String fsy837ioRecvBrn;

  private String fsy837ioY837No;

  private String fsy837ioEmpNo;

  private String fsy837ioBusType;

  private String fsy837ioTxType;

  private String fsy837ioTxCod;

  private String fsy837ioCustId;

  private String fsy837ioFxNo;

  private String fsy837ioOldY837No;

  private String fsy837ioCurency;

  private BigDecimal fsy837ioTxnAmt;

  private String fsy837ioFiller;
}
