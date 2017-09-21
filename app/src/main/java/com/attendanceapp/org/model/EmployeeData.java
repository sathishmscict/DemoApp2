package com.attendanceapp.org.model;

/**
 * Created by SATHISH on 13-Sep-17.
 */


import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class EmployeeData {

        @SerializedName("MESSAGE")
        @Expose
        private String mESSAGE;
        @SerializedName("ORIGINAL_ERROR")
        @Expose
        private String oRIGINALERROR;
        @SerializedName("ERROR_STATUS")
        @Expose
        private Boolean eRRORSTATUS;
        @SerializedName("TOTAL_EMPLOYEES")
        @Expose
        private Integer tOTALEMPLOYEES;
        @SerializedName("TOTAL_PRESENT")
        @Expose
        private Integer tOTALPRESENT;
        @SerializedName("TOTAL_ABSENT")
        @Expose
        private Integer tOTALABSENT;
        @SerializedName("TOTAL_LEAVE")
        @Expose
        private Integer tOTALLEAVE;
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

        public Integer getTOTALEMPLOYEES() {
            return tOTALEMPLOYEES;
        }

        public void setTOTALEMPLOYEES(Integer tOTALEMPLOYEES) {
            this.tOTALEMPLOYEES = tOTALEMPLOYEES;
        }

        public Integer getTOTALPRESENT() {
            return tOTALPRESENT;
        }

        public void setTOTALPRESENT(Integer tOTALPRESENT) {
            this.tOTALPRESENT = tOTALPRESENT;
        }

        public Integer getTOTALABSENT() {
            return tOTALABSENT;
        }

        public void setTOTALABSENT(Integer tOTALABSENT) {
            this.tOTALABSENT = tOTALABSENT;
        }

        public Integer getTOTALLEAVE() {
            return tOTALLEAVE;
        }

        public void setTOTALLEAVE(Integer tOTALLEAVE) {
            this.tOTALLEAVE = tOTALLEAVE;
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

            @SerializedName("EmpId")
            @Expose
            private String empId;
            @SerializedName("DeptId")
            @Expose
            private String deptId;
            @SerializedName("BranchId")
            @Expose
            private String branchId;
            @SerializedName("EmpName")
            @Expose
            private String empName;
            @SerializedName("Photo")
            @Expose
            private String photo;
            @SerializedName("InTime")
            @Expose
            private String inTime;

            @SerializedName("OutTime")
            @Expose
            private String outTime;

            @SerializedName("TotalCount")
            @Expose
            private Integer totalCount;


            @SerializedName("LeaveStatus")
            @Expose
            private String leaveStatus;


            public String getLeaveStatus() {
                return leaveStatus;
            }

            public void setLeaveStatus(String leaveStatus) {
                this.leaveStatus = leaveStatus;
            }

            public String getEmpId() {
                return empId;
            }

            public void setEmpId(String empId) {
                this.empId = empId;
            }

            public String getDeptId() {
                return deptId;
            }

            public void setDeptId(String deptId) {
                this.deptId = deptId;
            }

            public String getBranchId() {
                return branchId;
            }

            public void setBranchId(String branchId) {
                this.branchId = branchId;
            }

            public String getEmpName() {
                return empName;
            }

            public void setEmpName(String empName) {
                this.empName = empName;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getInTime() {
                return inTime;
            }

            public void setInTime(String inTime) {
                this.inTime = inTime;
            }

            public String getOutTime() {
                return outTime;
            }

            public void setOutTime(String outTime) {
                this.outTime = outTime;
            }

            public Integer getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(Integer totalCount) {
                this.totalCount = totalCount;
            }

        }
    }


