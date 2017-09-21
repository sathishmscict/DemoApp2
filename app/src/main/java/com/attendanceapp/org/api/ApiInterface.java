package com.attendanceapp.org.api;

import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.model.BranchData;
import com.attendanceapp.org.model.CommonReponse;
import com.attendanceapp.org.model.EmployeeAttendance;
import com.attendanceapp.org.model.EmployeeData;
import com.attendanceapp.org.model.UserDataResponse;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface {




    @POST("getLoginDetails")
    @FormUrlEncoded
    Call<UserDataResponse> getLoginDetails(@Field("type") String type, @Field("mobileno") String mobileNo);



    @POST("getVerificationCode")
    @FormUrlEncoded
    Call<CommonReponse> getVerificatonCode(@Field("type") String type, @Field("code") String verificationCode, @Field("mobile") String mobile);

    @POST("getAllBranchDetailsByCompnayID")
    @FormUrlEncoded
    Call<BranchData> getAllBranchDetails(@Field("type")String type,@Field("companyid")String companyid);

    @POST("insertBranch")
    @FormUrlEncoded
    Call<BranchData> insertBranchToServer(@Field("type")String type,@Field("branchname")String branchName,@Field("address")String address,@Field("password")String password,@Field("companyid")String companyid);



    @POST("updateBranch")
    @FormUrlEncoded
    Call<BranchData> updateBranchToServer(@Field("type")String type,@Field("branchid")String branchid,@Field("branchname")String branchName,@Field("address")String address,@Field("password")String password,@Field("companyid")String companyid);


    @POST("deleteBranch")
    @FormUrlEncoded
    Call<BranchData> deleteBranchToServer(@Field("type")String type,@Field("branchid")String branchid,@Field("companyid")String companyid);



    @POST("GetAllEmployeeData")
    @FormUrlEncoded
    Call<EmployeeData> getAllEmployeeData(@Field("type")String type, @Field("branchid")String branchid, @Field("companyid")String companyid,@Field("checkdate")String checkDate);

    @POST("GetEmployeMothlyAttendanceStatusDetails")
    @FormUrlEncoded
    Call<EmployeeAttendance> getEmployeMothlyAttendanceStatusDetails(@Field("type")String type, @Field("companyid")String companyid, @Field("empid")String empid, @Field("deptid")String deptid, @Field("branchid")String branchid);


    @POST("insertRegistration")
    @FormUrlEncoded
    Call<UserDataResponse> insertRegistration(@Field("type")String type, @Field("companyname")String companyName, @Field("contact_person_name")String contactPersonName,@Field("email")String email,@Field("address")String address,@Field("website")String website,@Field("mobileno")String mobileno,@Field("remark")String remark);










}
