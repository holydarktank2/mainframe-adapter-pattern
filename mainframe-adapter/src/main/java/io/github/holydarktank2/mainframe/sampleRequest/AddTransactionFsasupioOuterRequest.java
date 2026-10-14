package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsasupio
 */
@Getter
@Setter
@ToString
public class AddTransactionFsasupioOuterRequest implements Serializable {

  private String fsasupioRecvBrn;

  private String fsasupioBusinessKind1;

  private String fsasupioRefNo;

  private String fsasupioPermitNo;

  private Integer fsasupioVersion;

  private String fsasupioBusinessKind2;

  private String fsasupioBusinessKind3;

  private String fsasupioBusinessKind4;

  private String fsasupioTxCod;

  private String fsasupioProcessMod;

  private String fsasupioSuperId;

  private String fsasupioOperatorId;

  private String fsasupioTerminalId;

  private String fsasupioCurrency;

  private BigDecimal fsasupioAmount;

  private String fsasupioFamlNo;

  private String fsasupioCustomerId;

  private String fsasupioRemitCnty;

  private String fsasupioBusinessKind5;

  private String fsasupioFxWatchList1;

  private String fsasupioFxWatchList2;

  private String fsasupioFil;
}
