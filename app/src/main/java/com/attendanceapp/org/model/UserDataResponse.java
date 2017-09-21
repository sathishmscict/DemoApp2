package com.attendanceapp.org.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SATHISH on 11-Sep-17.
 */

public class UserDataResponse {


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

        @SerializedName("COMPNAY_ID")
        @Expose
        private Integer cOMPNAYID;
        @SerializedName("CONTACT_PERSON")
        @Expose
        private String cONTACTPERSON;
        @SerializedName("COMPANY_NAME")
        @Expose
        private String cOMPANYNAME;
        @SerializedName("ADDRESS")
        @Expose
        private String aDDRESS;
        @SerializedName("EMAIL")
        @Expose
        private String eMAIL;
        @SerializedName("WEBSITE")
        @Expose
        private String wEBSITE;
        @SerializedName("MOBILE_NO")
        @Expose
        private String mOBILENO;
        @SerializedName("REMARK")
        @Expose
        private String rEMARK;
        @SerializedName("IS_ACTIVE")
        @Expose
        private Boolean iSACTIVE;

        public Integer getCOMPNAYID() {
            return cOMPNAYID;
        }

        public void setCOMPNAYID(Integer cOMPNAYID) {
            this.cOMPNAYID = cOMPNAYID;
        }

        public String getCONTACTPERSON() {
            return cONTACTPERSON;
        }

        public void setCONTACTPERSON(String cONTACTPERSON) {
            this.cONTACTPERSON = cONTACTPERSON;
        }

        public String getCOMPANYNAME() {
            return cOMPANYNAME;
        }

        public void setCOMPANYNAME(String cOMPANYNAME) {
            this.cOMPANYNAME = cOMPANYNAME;
        }

        public String getADDRESS() {
            return aDDRESS;
        }

        public void setADDRESS(String aDDRESS) {
            this.aDDRESS = aDDRESS;
        }

        public String getEMAIL() {
            return eMAIL;
        }

        public void setEMAIL(String eMAIL) {
            this.eMAIL = eMAIL;
        }

        public String getWEBSITE() {
            return wEBSITE;
        }

        public void setWEBSITE(String wEBSITE) {
            this.wEBSITE = wEBSITE;
        }

        public String getMOBILENO() {
            return mOBILENO;
        }

        public void setMOBILENO(String mOBILENO) {
            this.mOBILENO = mOBILENO;
        }

        public String getREMARK() {
            return rEMARK;
        }

        public void setREMARK(String rEMARK) {
            this.rEMARK = rEMARK;
        }

        public Boolean getISACTIVE() {
            return iSACTIVE;
        }

        public void setISACTIVE(Boolean iSACTIVE) {
            this.iSACTIVE = iSACTIVE;
        }

    }
}
