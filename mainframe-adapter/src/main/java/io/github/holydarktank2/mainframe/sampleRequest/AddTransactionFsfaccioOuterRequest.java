package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsfaccio
 */
@Getter
@Setter
@ToString
public class AddTransactionFsfaccioOuterRequest implements Serializable {

  private String fsfaccioProcessMod;

  private String fsfaccioEcMk;

  private String fsfaccioReturnCod;

  private String fsfaccioBusType;

  private String fsfaccioDepType;

  private String fsfaccioDealType;

  private String fsfaccioRefNo;

  private String fsfaccioTxCod;

  private String fsfaccioAccBrn;

  private String fsfaccioProcessBrancd;

  private String fsfaccioNewRecvBranch;

  private String fsfaccioNewCustomerId;

  private String fsfaccioNewInterbnchNetSign;

  private BigDecimal fsfaccioNewInterbnchNet;

  private String fsfaccioNewInterbnchRemitSign;

  private BigDecimal fsfaccioNewInterbnchRemit;

  private String fsfaccioNewInterbnchDiffSign;

  private BigDecimal fsfaccioNewInterbnchDiff;

  private String fsfaccioNewAutoDepMk;

  private String fsfaccioOldRecvBranch;

  private String fsfaccioOldCustomerId;

  private String fsfaccioOldInterbnchNetSign;

  private BigDecimal fsfaccioOldInterbnchNet;

  private String fsfaccioOldInterbnchRemitSign;

  private BigDecimal fsfaccioOldInterbnchRemit;

  private String fsfaccioOldInterbnchDiffSign;

  private BigDecimal fsfaccioOldInterbnchDiff;

  private String fsfaccioOldAutoDepMk;

  private String fsfaccioEmpNo;

  private String fsfaccioFil;

}
