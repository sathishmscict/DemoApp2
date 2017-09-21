package com.attendanceapp.org.Login;

import android.content.Context;

import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.session.SessionManager;

/**
 * Created by SATHISH on 11-Sep-17.
 */

public class LoginPresenterImpl implements LoginPresenter ,LoginInteractor.OnLoginFinishedListener{

    private final SessionManager sessionManager;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();

        this.sessionManager= new SessionManager(context);

    }

    @Override
    public void validateCredentials(String username, String password) {

        if(loginView!=null)
        {
            loginView.showProgress();

        }

        loginInteractor.login(username,password,this,sessionManager);

    }

    @Override
    public void onDestroy() {

        loginView =null;
    }

    @Override
    public void onUsernameError() {
        if(loginView!=null)
        {

            loginView.setUsernameError();

        }

    }

    @Override
    public void onPasswordError() {

    }

    @Override
    public void onSuccess() {

        if(loginView!=null)
        {
            loginView.hideProgress();

            loginView.navigateToVerification();

        }

    }

    @Override
    public void onServiceError(int errorCode,String message) {
        if(loginView!=null)
        {
            loginView.hideProgress();
            loginView.serviceErrorCode(errorCode);

        }




    }

    @Override
    public void serviceCallFailed(String tag , Throwable T) {

        if(loginView!=null)
        {
            loginView.hideProgress();
            loginView.serviceCalledFailed(tag,T);

        }

    }
}

