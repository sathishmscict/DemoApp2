package com.attendanceapp.org.Login;

/**
 * Created by SATHISH on 11-Sep-17.
 */

public interface LoginPresenter {


    void validateCredentials(String username, String password);

    void onDestroy();

}
