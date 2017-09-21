package com.attendanceapp.org.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SATHISH on 16-Sep-17.
 */

public class EmployeeAttendance {

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

        @SerializedName("EmpId")
        @Expose
        private Integer empId;
        @SerializedName("DeptId")
        @Expose
        private Integer deptId;
        @SerializedName("BranchId")
        @Expose
        private Integer branchId;
        @SerializedName("CompanyId")
        @Expose
        private Integer companyId;
        @SerializedName("Month")
        @Expose
        private Integer month;
        @SerializedName("Year")
        @Expose
        private Integer year;




        @SerializedName("WorkingHours")
        @Expose
        private String WorkingHours;



        @SerializedName("Day1")
        @Expose
        private String day1;
        @SerializedName("Day2")
        @Expose
        private String day2;
        @SerializedName("Day3")
        @Expose
        private String day3;
        @SerializedName("Day4")
        @Expose
        private String day4;
        @SerializedName("Day5")
        @Expose
        private String day5;
        @SerializedName("Day6")
        @Expose
        private String day6;
        @SerializedName("Day7")
        @Expose
        private String day7;
        @SerializedName("Day8")
        @Expose
        private String day8;
        @SerializedName("Day9")
        @Expose
        private String day9;
        @SerializedName("Day10")
        @Expose
        private String day10;
        @SerializedName("Day11")
        @Expose
        private String day11;
        @SerializedName("Day12")
        @Expose
        private String day12;
        @SerializedName("Day13")
        @Expose
        private String day13;
        @SerializedName("Day14")
        @Expose
        private String day14;
        @SerializedName("Day15")
        @Expose
        private String day15;
        @SerializedName("Day16")
        @Expose
        private String day16;
        @SerializedName("Day17")
        @Expose
        private String day17;
        @SerializedName("Day18")
        @Expose
        private String day18;
        @SerializedName("Day19")
        @Expose
        private String day19;
        @SerializedName("Day20")
        @Expose
        private String day20;
        @SerializedName("Day21")
        @Expose
        private String day21;
        @SerializedName("Day22")
        @Expose
        private String day22;
        @SerializedName("Day23")
        @Expose
        private String day23;
        @SerializedName("Day24")
        @Expose
        private String day24;
        @SerializedName("Day25")
        @Expose
        private String day25;
        @SerializedName("Day26")
        @Expose
        private String day26;
        @SerializedName("Day27")
        @Expose
        private String day27;
        @SerializedName("Day28")
        @Expose
        private String day28;
        @SerializedName("Day29")
        @Expose
        private String day29;
        @SerializedName("Day30")
        @Expose
        private String day30;
        @SerializedName("Day31")
        @Expose
        private String day31;

        public Integer getEmpId() {
            return empId;
        }

        public void setEmpId(Integer empId) {
            this.empId = empId;
        }

        public Integer getDeptId() {
            return deptId;
        }

        public void setDeptId(Integer deptId) {
            this.deptId = deptId;
        }

        public Integer getBranchId() {
            return branchId;
        }

        public void setBranchId(Integer branchId) {
            this.branchId = branchId;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public Integer getMonth() {
            return month;
        }

        public void setMonth(Integer month) {
            this.month = month;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }


        public String getWorkingHours() {
            return WorkingHours;
        }

        public void setWorkingHours(String workingHours) {
            WorkingHours = workingHours;
        }

        public String getDay1() {
            return day1;
        }

        public void setDay1(String day1) {
            this.day1 = day1;
        }

        public String getDay2() {
            return day2;
        }

        public void setDay2(String day2) {
            this.day2 = day2;
        }

        public String getDay3() {
            return day3;
        }

        public void setDay3(String day3) {
            this.day3 = day3;
        }

        public String getDay4() {
            return day4;
        }

        public void setDay4(String day4) {
            this.day4 = day4;
        }

        public String getDay5() {
            return day5;
        }

        public void setDay5(String day5) {
            this.day5 = day5;
        }

        public String getDay6() {
            return day6;
        }

        public void setDay6(String day6) {
            this.day6 = day6;
        }

        public String getDay7() {
            return day7;
        }

        public void setDay7(String day7) {
            this.day7 = day7;
        }

        public String getDay8() {
            return day8;
        }

        public void setDay8(String day8) {
            this.day8 = day8;
        }

        public String getDay9() {
            return day9;
        }

        public void setDay9(String day9) {
            this.day9 = day9;
        }

        public String getDay10() {
            return day10;
        }

        public void setDay10(String day10) {
            this.day10 = day10;
        }

        public String getDay11() {
            return day11;
        }

        public void setDay11(String day11) {
            this.day11 = day11;
        }

        public String getDay12() {
            return day12;
        }

        public void setDay12(String day12) {
            this.day12 = day12;
        }

        public String getDay13() {
            return day13;
        }

        public void setDay13(String day13) {
            this.day13 = day13;
        }

        public String getDay14() {
            return day14;
        }

        public void setDay14(String day14) {
            this.day14 = day14;
        }

        public String getDay15() {
            return day15;
        }

        public void setDay15(String day15) {
            this.day15 = day15;
        }

        public String getDay16() {
            return day16;
        }

        public void setDay16(String day16) {
            this.day16 = day16;
        }

        public String getDay17() {
            return day17;
        }

        public void setDay17(String day17) {
            this.day17 = day17;
        }

        public String getDay18() {
            return day18;
        }

        public void setDay18(String day18) {
            this.day18 = day18;
        }

        public String getDay19() {
            return day19;
        }

        public void setDay19(String day19) {
            this.day19 = day19;
        }

        public String getDay20() {
            return day20;
        }

        public void setDay20(String day20) {
            this.day20 = day20;
        }

        public String getDay21() {
            return day21;
        }

        public void setDay21(String day21) {
            this.day21 = day21;
        }

        public String getDay22() {
            return day22;
        }

        public void setDay22(String day22) {
            this.day22 = day22;
        }

        public String getDay23() {
            return day23;
        }

        public void setDay23(String day23) {
            this.day23 = day23;
        }

        public String getDay24() {
            return day24;
        }

        public void setDay24(String day24) {
            this.day24 = day24;
        }

        public String getDay25() {
            return day25;
        }

        public void setDay25(String day25) {
            this.day25 = day25;
        }

        public String getDay26() {
            return day26;
        }

        public void setDay26(String day26) {
            this.day26 = day26;
        }

        public String getDay27() {
            return day27;
        }

        public void setDay27(String day27) {
            this.day27 = day27;
        }

        public String getDay28() {
            return day28;
        }

        public void setDay28(String day28) {
            this.day28 = day28;
        }

        public String getDay29() {
            return day29;
        }

        public void setDay29(String day29) {
            this.day29 = day29;
        }

        public String getDay30() {
            return day30;
        }

        public void setDay30(String day30) {
            this.day30 = day30;
        }

        public String getDay31() {
            return day31;
        }

        public void setDay31(String day31) {
            this.day31 = day31;
        }

    }




}
