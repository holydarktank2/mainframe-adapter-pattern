package io.github.holydarktank2.mainframe.sampleRequest;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsa83fioDetail
 */
@Getter
@Setter
@ToString
public class AddTransactionFsa83fioDetailOuterRequest implements Serializable {

  private static final long serialVersionUID = -5357905510145850906L;


  private String fsa83fioAccCode;

  private String fsa83fioSubCode;

  private String fsa83fioDbCrCode;

  private String fsa83fioCur;

  private BigDecimal fsa83fioTxAmt;

  private String fsa83fioMemo;

}
