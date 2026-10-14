package io.github.holydarktank2.mainframe.sampleRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * add transaction to mainframe
 */
@Getter
@Setter
@ToString
public class AddTransactionOuterRequest{

  public static final String NO = "N";

  private String handleErrorFlag;

  private String txnCode;

  private String txnUniqueKey;

  private String processBrnNo;

  private String processBrnId;

  private String processBrnType;

  private String empNo;

  private String opId;

  private String processMod;

  private String termId;

  private String businessDate;

  private String filler1;

  private String begin1;

  private String pgmName1;

  private String end1;

  private AddTransactionFsaglapiOuterRequest fsaglapi;


  private String begin2;

  private String pgmName2;

  private String end2;

  private AddTransactionFstnotc1OuterRequest fstnotc1;

  private String begin3;

  private String pgmName3;

  private String end3;

  private AddTransactionFoqs0001OuterRequest foqs0001;

  private String begin4;

  private String pgmName4;

  private String end4;

  private AddTransactionFsposio2OuterRequest fsposio2;

  private String begin5;

  private String pgmName5;

  private String end5;

  private AddTransactionFsa83fioOuterRequest fsa83fio;

  private String begin6;

  private String pgmName6;

  private String end6;

  private AddTransactionFoisautoOuterRequest foisauto;

  private String begin7;

  private String pgmName7;

  private String end7;

  private AddTransactionFstafamlOuterRequest fstafaml;

  private String begin8;

  private String pgmName8;

  private String end8;

  private AddTransactionFoqapi00OuterRequest foqapi00;

  private String begin9;

  private String pgmName9;

  private String end9;

  private AddTransactionFsfratioOuterRequest fsfratio;

  private String begin10;

  private String pgmName10;

  private String end10;

  private AddTransactionFofsrnewOuterRequest fofsrnew;

  private String begin11;

  private String pgmName11;

  private String end11;

  private AddTransactionFsaorsw0OuterRequest fsaorsw0;

  private String begin12;

  private String pgmName12;

  private String end12;

  private AddTransactionFsfaccioOuterRequest fsfaccio;


  private String begin13;

  private String pgmName13;

  private String end13;

  private AddTransactionFsfrmtioOuterRequest fsfrmtio;

  private String begin14;

  private String pgmName14;

  private String end14;

  private AddTransactionFsasupioOuterRequest fsasupio;

  private String begin15;

  private String pgmName15;

  private String end15;

  private AddTransactionFsatxlstOuterRequest fsatxlst;

  private String begin16;

  private String pgmName16;


  private String end16;

  private AddTransactionFsy837ioOuterRequest fsy837io;

  private String fillerChar500;

  private String fillerNum500;

  // for AddEbankBySettlement temp only
  private String payFxFlag;

  /**
   * 預設值
   */
  public AddTransactionOuterRequest() {
    this.handleErrorFlag = NO;
    this.fsaglapi = new AddTransactionFsaglapiOuterRequest();
    this.fstnotc1 = new AddTransactionFstnotc1OuterRequest();
    this.foqs0001 = new AddTransactionFoqs0001OuterRequest();
    this.fsposio2 = new AddTransactionFsposio2OuterRequest();
    this.fsa83fio = new AddTransactionFsa83fioOuterRequest();
    this.foisauto = new AddTransactionFoisautoOuterRequest();
    this.fstafaml = new AddTransactionFstafamlOuterRequest();
    this.foqapi00 = new AddTransactionFoqapi00OuterRequest();
    this.fsfratio = new AddTransactionFsfratioOuterRequest();
    this.fofsrnew = new AddTransactionFofsrnewOuterRequest();
    this.fsaorsw0 = new AddTransactionFsaorsw0OuterRequest();
    this.fsfaccio = new AddTransactionFsfaccioOuterRequest();
    this.fsfrmtio = new AddTransactionFsfrmtioOuterRequest();
    this.fsasupio = new AddTransactionFsasupioOuterRequest();
    this.fsatxlst = new AddTransactionFsatxlstOuterRequest();
    this.fsy837io = new AddTransactionFsy837ioOuterRequest();
  }
}
