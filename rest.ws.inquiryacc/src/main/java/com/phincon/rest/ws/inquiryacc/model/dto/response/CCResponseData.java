package com.phincon.rest.ws.inquiryacc.model.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CCResponseData {
    private String cardNbr;
    private int cardPrd;
    private String cardPrdCurr;
    private String cardPrdType;
    private String cardAplType;
    private String cardSts;
    private String cardBlk;
    private String cardInactiveDt;
    private int cardCrLimit;
    private int cardAcStmBal;
    private String cardAcPyDueDt;
    private int cardAcPyDueAmt;
    private int cardAcCurBal;
    private int cardAcAvailCr;
    private int cardAcPointBal;
    private String cardEmbName;
    private String cardAcLstStmDt;
    private int cardAcLstPyAmt;
    private String cardAcLstPyDte;
    private int cardAcCTDPyAmt;
    private String cardCustomer;
    private String cardCIF;

}
