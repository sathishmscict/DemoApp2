package com.attendanceapp.org.Verification;

import com.attendanceapp.org.base.onFinishAPIListener;
import com.attendanceapp.org.session.SessionManager;

import java.util.HashMap;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public interface VerificationInteractor {


    interface onFinishVerificationCheckListener extends onFinishAPIListener
    {
        void onSuccessMatchVerificationCode();
        void onFailedMatchVerificationCode(String  errorMessage);
    }

    void  getVerificationFromServer(SessionManager sessionManager , HashMap<String,String> userDetails,onFinishAPIListener listener);

    void checkVerificationCode(String defaultVerificationCode,String receivedVerificationCode,onFinishVerificationCheckListener listener);


}
