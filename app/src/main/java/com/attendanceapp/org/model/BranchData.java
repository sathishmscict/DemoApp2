package com.attendanceapp.org.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public class BranchData {

    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("ORIGINAL_ERROR")
    @Expose
    private String oRIGINALERROR;
    @SerializedName("ERROR_STATUS")
    @Expose
    private Boolean eRRORSTATUS;
    @SerializedName("RECORDS")
    @Expose
    private Boolean rECORDS;
    @SerializedName("Data")
    @Expose
    private List<Datum> data = null;

    public String getMESSAGE() {
        return mESSAGE;
    }

    public void setMESSAGE(String mESSAGE) {
        this.mESSAGE = mESSAGE;
    }

    public String getORIGINALERROR() {
        return oRIGINALERROR;
    }

    public void setORIGINALERROR(String oRIGINALERROR) {
        this.oRIGINALERROR = oRIGINALERROR;
    }

    public Boolean getERRORSTATUS() {
        return eRRORSTATUS;
    }

    public void setERRORSTATUS(Boolean eRRORSTATUS) {
        this.eRRORSTATUS = eRRORSTATUS;
    }

    public Boolean getRECORDS() {
        return rECORDS;
    }

    public void setRECORDS(Boolean rECORDS) {
        this.rECORDS = rECORDS;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


public class Datum {

    @SerializedName("BRANCH_ID")
    @Expose
    private Integer bRANCHID;
    @SerializedName("COMPANY_ID")
    @Expose
    private Integer cOMPANYID;
    @SerializedName("BRANCH_NAME")
    @Expose
    private String bRANCHNAME;
    @SerializedName("BRANCH_ADDRESS")
    @Expose
    private String bRANCHADDRESS;
    @SerializedName("BRANCH_PASSWORD")
    @Expose
    private String bRANCHPASSWORD;

    public Integer getBRANCHID() {
        return bRANCHID;
    }

    public void setBRANCHID(Integer bRANCHID) {
        this.bRANCHID = bRANCHID;
    }

    public Integer getCOMPANYID() {
        return cOMPANYID;
    }

    public void setCOMPANYID(Integer cOMPANYID) {
        this.cOMPANYID = cOMPANYID;
    }

    public String getBRANCHNAME() {
        return bRANCHNAME;
    }

    public void setBRANCHNAME(String bRANCHNAME) {
        this.bRANCHNAME = bRANCHNAME;
    }

    public String getBRANCHADDRESS() {
        return bRANCHADDRESS;
    }

    public void setBRANCHADDRESS(String bRANCHADDRESS) {
        this.bRANCHADDRESS = bRANCHADDRESS;
    }

    public String getBRANCHPASSWORD() {
        return bRANCHPASSWORD;
    }

    public void setBRANCHPASSWORD(String bRANCHPASSWORD) {
        this.bRANCHPASSWORD = bRANCHPASSWORD;
    }

}
}
