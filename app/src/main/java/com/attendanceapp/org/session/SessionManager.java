package com.attendanceapp.org.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.attendanceapp.org.Login.LoginActivity;

import java.util.HashMap;

/**
 * Created by Lincoln on 05/05/16.
 */
public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "offersapp";


    // Check For Activation
    public static final String KEY_CODE = "code",
            KEY_RECEIVECODE = "reccode", KEY_REMARK = "Remark";


    public static final String KEY_USER_AVATAR_URL = "UserAvatarURL", KEY_USER_EMAIL = "UserEmail", KEY_USER_NAME = "UserName", KEY_COMPANY_NAME = "companyNAme", KEY_ADDRESS = "Address", KEY_WEBSITE = "Website";


    public static final String KEY_SELECETED_EMPLOYEE_ID = "SelectedEmployeeId",KEY_SELECETED_DEPT_ID="DeptId";


    public static final String KEY_SELECTED_BRANCH_ID = "BranchId", KEY_SELECTED_BRNACH_NAME = "BranchName";
    public static final String KEY_TOTAL_BRNACHES = "TotalBranches";
    public static final String KEY_COMPANY_ID = "CompanyId", KEY_IS_ACTIVE = "IsActive";
    public static final String KEY_ENODEDED_STRING = "Encoded_string";
    public static final String KEY_USER_ID = "UserId", KEY_USER_VERIFICATION_STATUS = "VerificationStatus", KEY_USER_GENDER = "Gender", KEY_USER_DOB = "DOB", KEY_USER_REFERAL_CODE = "ReferralCode", KEY_USER_DEVICE_TYPE = "DeviceType", KEY_USER_IS_FIRST_BILL = "IsFirstBill", KEY_USER_IS_ACTIVE = "IsActive", KEY_USER_IS_REFERRED = "IsReferred", KEY_USER_MOBILE = "UserMobile";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void CheckSMSVerificationActivity(String reccode, String actstatus) {

        editor.putString(KEY_RECEIVECODE, reccode);
        editor.putString(KEY_USER_VERIFICATION_STATUS, actstatus);
        editor.commit();

    }


    public void setSelecetedEmployeeData(String selecetdEmployeeId,String selectedDeptId)
    {
        editor.putString(KEY_SELECETED_EMPLOYEE_ID , selecetdEmployeeId);
        editor.putString(KEY_SELECETED_DEPT_ID , selectedDeptId);

        editor.commit();

    }
    public void setSelectedBranchDetails(String branchid, String branchName) {


        editor.putString(KEY_SELECTED_BRANCH_ID, branchid);
        editor.putString(KEY_SELECTED_BRNACH_NAME, branchName);
        editor.commit();

    }


    public void setUserImageUrl(String imgURL) {

        editor.putString(KEY_USER_AVATAR_URL, imgURL);
        editor.commit();
    }


    public void setTotalBranches(String totalBranches) {
        editor.putString(KEY_TOTAL_BRNACHES, totalBranches);
        editor.commit();

    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getSessionDetails() {


        HashMap<String, String> user = new HashMap<String, String>();


        user.put(KEY_SELECETED_EMPLOYEE_ID , pref.getString(KEY_SELECETED_EMPLOYEE_ID , "0"));
        user.put(KEY_SELECETED_DEPT_ID , pref.getString(KEY_SELECETED_DEPT_ID ,"0"));


        user.put(KEY_SELECTED_BRANCH_ID, pref.getString(KEY_SELECTED_BRANCH_ID, "0"));
        user.put(KEY_SELECTED_BRNACH_NAME, pref.getString(KEY_SELECTED_BRNACH_NAME, ""));


        user.put(KEY_TOTAL_BRNACHES, pref.getString(KEY_TOTAL_BRNACHES, "0"));
        user.put(KEY_IS_ACTIVE, pref.getString(KEY_IS_ACTIVE, "1"));


        user.put(KEY_REMARK, pref.getString(KEY_REMARK, ""));
        user.put(KEY_WEBSITE, pref.getString(KEY_WEBSITE, ""));

        user.put(KEY_ADDRESS, pref.getString(KEY_ADDRESS, ""));

        user.put(KEY_COMPANY_NAME, pref.getString(KEY_COMPANY_NAME, ""));


        user.put(KEY_COMPANY_ID, pref.getString(KEY_COMPANY_ID, "0"));

        user.put(KEY_ENODEDED_STRING, pref.getString(KEY_ENODEDED_STRING, ""));
        // user.put(KEY_ORDERID, pref.getString(KEY_ORDERID, "0"));
        user.put(KEY_RECEIVECODE, pref.getString(KEY_RECEIVECODE, "0"));
        user.put(KEY_CODE, pref.getString(KEY_CODE, "0"));


        user.put(KEY_USER_VERIFICATION_STATUS, pref.getString(KEY_USER_VERIFICATION_STATUS, "0"));
        user.put(KEY_USER_AVATAR_URL, pref.getString(KEY_USER_AVATAR_URL, ""));

        user.put(KEY_USER_NAME, pref.getString(KEY_USER_NAME, ""));
        user.put(KEY_USER_EMAIL, pref.getString(KEY_USER_EMAIL, ""));
        user.put(KEY_USER_ID, pref.getString(KEY_USER_ID, "0"));
        user.put(KEY_USER_GENDER, pref.getString(KEY_USER_GENDER, ""));
        user.put(KEY_USER_DOB, pref.getString(KEY_USER_DOB, ""));
        user.put(KEY_USER_REFERAL_CODE, pref.getString(KEY_USER_REFERAL_CODE, ""));
        user.put(KEY_USER_DEVICE_TYPE, pref.getString(KEY_USER_DEVICE_TYPE, ""));
        user.put(KEY_USER_IS_FIRST_BILL, pref.getString(KEY_USER_IS_FIRST_BILL, "0"));
        user.put(KEY_USER_IS_ACTIVE, pref.getString(KEY_USER_IS_ACTIVE, "1"));
        user.put(KEY_USER_IS_REFERRED, pref.getString(KEY_USER_IS_REFERRED, "0"));

        user.put(KEY_USER_MOBILE, pref.getString(KEY_USER_MOBILE, "0"));


        return user;
    }

    public void setEncodedImage(String encodeo_image) {


        editor.putString(KEY_ENODEDED_STRING, encodeo_image);

        editor.commit();
    }


    public void createUserSendSmsUrl(String code) {

        editor.putString(KEY_CODE, code);

        editor.commit();

    }


    public void setUserDetails(String username, String email) {

        editor.putString(KEY_USER_NAME, username);
        editor.putString(KEY_USER_EMAIL, email);
        editor.commit();


    }

    public void setUserDetails(String companyId, String contactPerson, String companyName, String address, String email, String website, String mobileno, String remark, String isactive) {


        editor.putString(KEY_COMPANY_ID, companyId);
        editor.putString(KEY_USER_NAME, contactPerson);
        editor.putString(KEY_COMPANY_NAME, companyName);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_WEBSITE, website);


        editor.putString(KEY_USER_MOBILE, mobileno);
        editor.putString(KEY_REMARK, remark);


        editor.putString(KEY_USER_ID, companyId);
        editor.putString(KEY_IS_ACTIVE, isactive);


        //editor.putString(KEY_USER_VERIFICATION_STATUS, verificationStatus);
        //editor.putString(KEY_USER_GENDER, gender);
        //editor.putString(KEY_USER_DOB, dob);
        //editor.putString(KEY_USER_AVATAR_URL, useravatar);
        //editor.putString(KEY_USER_REFERAL_CODE, referalcode);
        //editor.putString(KEY_USER_DEVICE_TYPE, devicetype);
        //editor.putString(KEY_USER_IS_FIRST_BILL, isFirstBill);
        //editor.putString(KEY_USER_IS_ACTIVE, isActive);
        //editor.putString(KEY_USER_IS_REFERRED, isreferred);


        editor.commit();


    }


    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }


}
