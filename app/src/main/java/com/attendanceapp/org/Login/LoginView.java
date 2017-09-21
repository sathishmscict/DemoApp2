package com.attendanceapp.org.Login;

import com.attendanceapp.org.base.MvpView;

/**
 * Created by SATHISH on 11-Sep-17.
 */

public interface LoginView  extends MvpView{



    void setUsernameError();
    void navigateToVerification();


}
