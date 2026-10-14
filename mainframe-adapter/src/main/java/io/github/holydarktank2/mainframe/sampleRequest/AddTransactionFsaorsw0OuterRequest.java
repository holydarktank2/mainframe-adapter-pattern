package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsaorsw0
 */
@Getter
@Setter
@ToString
public class AddTransactionFsaorsw0OuterRequest implements Serializable {

  private String fsaorsw0DataType;

  private String fsaorsw0Curency;

  private String fsaorsw0CountryCod;

  private String fsaorsw0SwiftTid;

  private String fsaorsw0FxNo;

  private String fsaorsw0TxCod;

  private String fsaorsw0ValueDate;

  private BigDecimal fsaorsw0Amount;

  private String fsaorsw0ProcessBrn;

  private String fsaorsw0SwMtType;

  private String fsaorsw0SwBankOpcod;

  private String fsaorsw0SwReleaseFlage;

  private String fsaorsw0ReceivingBrn;

  private String fsaorsw0ProcessDate;

  private String fsaorsw0TxTime;

  private String fsaorsw0ReleaseTime;

  private String fsaorsw0BrnType;

  private String fsaorsw0BusHour;

  private String fsaorsw0Fil;
}
