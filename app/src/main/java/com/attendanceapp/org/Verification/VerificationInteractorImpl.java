package com.attendanceapp.org.Verification;

import android.util.Log;
import android.widget.Toast;

import com.attendanceapp.org.api.ApiClient;
import com.attendanceapp.org.api.ApiInterface;
import com.attendanceapp.org.base.onFinishAPIListener;
import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.model.CommonReponse;
import com.attendanceapp.org.session.SessionManager;

import java.util.HashMap;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public class VerificationInteractorImpl implements VerificationInteractor {

    private String TAG = VerificationInteractorImpl.class.getSimpleName();

    @Override
    public void getVerificationFromServer(SessionManager sessionManager, HashMap<String, String> userDetails, final onFinishAPIListener listener) {


        String sendsms = "";

        if (userDetails.get(SessionManager.KEY_CODE).equals("0")) {
            Random r = new Random();
            int code = r.nextInt(9999 - 1000) + 1000;
            Log.d(TAG, "Verification Code : " + code);

            Log.d(TAG, "URL sendSMS : " + sendsms);

            sessionManager.createUserSendSmsUrl("" + code);
            userDetails = sessionManager.getSessionDetails();
        } else {

            userDetails = sessionManager.getSessionDetails();
            Log.d(TAG, "URL sendSMS : " + sendsms);

        }


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Log.d(TAG, "URL getVerificatonCode  : " + CommonMethods.WEBSITE + "getVerificationCode?type=verification&code=" + userDetails.get(SessionManager.KEY_CODE) + "&mobile=" + userDetails.get(SessionManager.KEY_USER_MOBILE) + "");


        apiInterface.getVerificatonCode("verification", userDetails.get(SessionManager.KEY_CODE), userDetails.get(SessionManager.KEY_USER_MOBILE)).enqueue(new Callback<CommonReponse>() {
            @Override
            public void onResponse(Call<CommonReponse> call, Response<CommonReponse> response) {

                Log.d(TAG, "getVerificationCode Response Code : " + response.code());

                if (response.code() == 200) {
                    if (response.body().getERRORSTATUS() == false) {

                        listener.onSuccess();


                    } else {
                        listener.onServiceError(response.code(), "Error in message sending,try again...\n" + response.body().getORIGINALERROR());

                    }


                } else {
                    listener.onServiceError(response.code(), "Server error, try again...");

                }


            }

            @Override
            public void onFailure(Call<CommonReponse> call, Throwable t) {


                listener.onFailureServiceCalled(TAG, t);

            }
        });

    }

    @Override
    public void checkVerificationCode(String verificationCode, String receivedVerificationCode, final onFinishVerificationCheckListener listener) {

        if (verificationCode.equals(receivedVerificationCode)) {


            listener.onSuccessMatchVerificationCode();


        } else {

            listener.onFailedMatchVerificationCode("Invalid verification code");



        }


    }


}
