package com.attendanceapp.org.Verification;

import android.content.Context;
import android.widget.Toast;

import com.attendanceapp.org.Login.LoginView;
import com.attendanceapp.org.base.onFinishAPIListener;
import com.attendanceapp.org.model.BranchData;
import com.attendanceapp.org.session.SessionManager;

import java.util.HashMap;
import java.util.TimerTask;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public class VerificationPresenterImpl implements VerificationPresenter,VerificationInteractor.onFinishVerificationCheckListener {

    private final SessionManager sessionManager;
    private final HashMap<String, String> userDetails;
    private VerificationView verificationView;
    private VerificationInteractorImpl verificationInteractor;

    public VerificationPresenterImpl(VerificationView verificationView, Context context) {
        this.verificationView = verificationView;
        this.verificationInteractor = new VerificationInteractorImpl();

        this.sessionManager= new SessionManager(context);
        this.userDetails = sessionManager.getSessionDetails();


    }


    @Override
    public void onDestroy() {

        verificationView=null;

    }

    @Override
    public void sendVerificationCode() {

        if(verificationView!=null)
        {
            verificationView.showProgress();

        }

        verificationInteractor.getVerificationFromServer(sessionManager,userDetails,this);
    }

    @Override
    public void validationCerificationCode(String verificationCode) {

        if(verificationView!=null)
        {

            verificationView.showProgress();

            verificationInteractor.checkVerificationCode(userDetails.get(SessionManager.KEY_CODE),verificationCode,this);





        }
    }



    @Override
    public void onSuccess() {

        if(verificationView!=null)
        {
            verificationView.hideProgress();

        }

        verificationView.showMessage("SMS sent successfully");
    }

    @Override
    public void onServiceError(int errorCode, String errorMessage) {

    }

    @Override
    public void onFailureServiceCalled(String TAG, Throwable T) {

    }

    @Override
    public void onServiceErrorMessage(String message) {

    }

    @Override
    public void noDataFound() {

    }


    @Override
    public void onSuccessMatchVerificationCode() {

        if(verificationView!= null)
        {
            sessionManager.CheckSMSVerificationActivity("",
                    "1");
            verificationView.navigateToDashboard();

        }
    }

    @Override
    public void onFailedMatchVerificationCode(String errorMessage) {

        if(verificationView!= null)
        {
            verificationView.showMessage(errorMessage);


        }

    }
}
