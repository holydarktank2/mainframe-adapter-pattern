package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFsa83fio
 */
@Getter
@Setter
@ToString
public class AddTransactionFsa83fioOuterRequest implements Serializable {


  private String fsa83fioProcessMod;

  private String fsa83fioTermId;

  private String fsa83fioGlBrn;

  private List<AddTransactionFsa83fioDetailOuterRequest> detail;

  private String fsa83fioReturnCod;

  private String fsa83fioBackRcode;

  private BigDecimal fsa83fioRrn;

  private String fsa83fioEcSupId;

  private String fsa83fioFil;

  public AddTransactionFsa83fioOuterRequest() {
    this.detail = new ArrayList<>();
  }
}
