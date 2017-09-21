package com.attendanceapp.org.Login;

import com.attendanceapp.org.session.SessionManager;

/**
 * Created by SATHISH on 11-Sep-17.
 */

public interface LoginInteractor  {

    interface OnLoginFinishedListener {

        void onUsernameError();

        void onPasswordError();

        void onSuccess();


        void onServiceError(int errorCode,String errorMessage);

        void serviceCallFailed(String TAG,Throwable T);


    }

    void login(String username, String password, OnLoginFinishedListener listener, SessionManager sessionManager);

}
