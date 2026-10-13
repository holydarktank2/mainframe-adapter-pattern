package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsfrmtio
 */
@Getter
@Setter
@ToString
public class AddTransactionFsfrmtioOuterRequest implements Serializable {

  private String fsfrmtioProcessMod;

  private String fsfrmtioEcMk;

  private String fsfrmtioReturnCod;

  private String fsfrmtioBusType;

  private String fsfrmtioDepType;

  private String fsfrmtioRefNo;

  private String fsfrmtioTxCod;

  private String fsfrmtioProcessBranch;

  private String fsfrmtioInwardBranch;

  private String fsfrmtioCurency;

  private String fsfrmtioEmpNo;

  private String fsfrmtioNewCustomerId;

  private BigDecimal fsfrmtioNewInterbnchNet;

  private BigDecimal fsfrmtioNewInterbnchRemit;

  private String fsfrmtioNewInterbnchDiffSign;

  private BigDecimal fsfrmtioNewInterbnchDiff;

  private String fsfrmtioOldCustomerId;

  private BigDecimal fsfrmtioOldInterbnchNet;

  private BigDecimal fsfrmtioOldInterbnchRemit;

  private String fsfrmtioOldInterbnchDiffSign;

  private BigDecimal fsfrmtioOldInterbnchDiff;

  private String fsfrmtioFil;
}
