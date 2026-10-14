package io.github.holydarktank2.mainframe.sampleRequest;


import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The outer request of AddTransactionFstafaml
 */
@Getter
@Setter
@ToString
public class AddTransactionFstafamlOuterRequest implements Serializable {

  private String fstafamlFamlFxNo;

  private String fstafamlFamlNo;

  private String fstafamlLinlReturnCode;

  private String fstafamlLinkReturnMsg;

  private String fstafamlAmlRef;

  private String fstafamlAmlNameBlack;

  private String fstafamlAmlIdRisk;

  private String fstafamlFamlProcMod;

  private String fstafamlVerNo;

  private String fstafamlEmpId;

  private String fstafamlOpName;

  private String fstafamlTermId;

  private String fstafamlTransId;

  private String fstafamlAmlNameBlackAuth;

  private String fstafamlAmlIdRiskAuth;

  private String fstafamlCustomerId;

  private String fstafamlAmlIdRiskError;

  private String fstafamlMediaOr;

  private String fstafamlFamlFilx;
}
