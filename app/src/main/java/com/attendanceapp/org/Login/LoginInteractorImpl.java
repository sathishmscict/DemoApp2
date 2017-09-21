package com.attendanceapp.org.Login;

import android.util.Log;

import com.attendanceapp.org.api.ApiClient;
import com.attendanceapp.org.api.ApiInterface;
import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.model.UserDataResponse;
import com.attendanceapp.org.session.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SATHISH on 11-Sep-17.
 */

public class LoginInteractorImpl  implements LoginInteractor {

    private String TAG = LoginInteractorImpl.class.getSimpleName();

    @Override
    public void login(String username, String password, final OnLoginFinishedListener listener, final SessionManager sessionManager) {



        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Log.d(TAG , "URL login  : "+ CommonMethods.WEBSITE+"getLoginDetails?type=login&mobile="+ username +"");

        apiInterface.getLoginDetails("login",username).enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {

                Log.d(TAG, "getLoginDetails Response Code : " + response.code());



                if (response.code() == 200)
                {


                    // Toast.makeText(context, "API Called Success" + response.body().toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "API Called Success" + response.toString());


                    String str_error = response.body().getMESSAGE();
                    String str_error_original = response.body().getORIGINALERROR();
                    boolean error_status = response.body().getERRORSTATUS();
                    boolean record_status = response.body().getRECORDS();


                    // String userMobile = null;
                    if (error_status == false)
                    {

                        if (record_status == true)
                        {


                            List<UserDataResponse.Datum> data = response.body().getData();

                            for (int i = 0; i < data.size(); i++)
                            {
                                int companyId = data.get(i).getCOMPNAYID();
                                String contactPerson = data.get(i).getCONTACTPERSON();
                                String compnayName = data.get(i).getCOMPANYNAME();
                                String address = data.get(i).getADDRESS();
                                String email = data.get(i).getEMAIL();
                                String website = data.get(i).getWEBSITE();


                                String mobileNo = data.get(i).getMOBILENO();
                                String remark = data.get(i).getREMARK();
                                Boolean isActive = data.get(i).getISACTIVE();

                                // userMobile = data.get(i).getMOBILENO();

                                //String companyId,String contactPerson,String companyName,String address, String email,String website,  String mobileno,String remark,String isactive
                                if(isActive == true)
                                {
                                    sessionManager.setUserDetails(String.valueOf(companyId), contactPerson, compnayName, address, email, website, mobileNo, remark, "1");
                                }
                                else
                                {
                                    sessionManager.setUserDetails(String.valueOf(companyId), contactPerson, compnayName, address, email, website, mobileNo, remark, "0");

                                }



                            }
                            //CommonMethods.hideDialog(spotsDialog);


                            //disable landing screen
                            //sessionManager.setFirstTimeLaunch(false);





                        }

                    } else {

                        listener.onServiceError(response.code(),str_error);

                        //Toast.makeText(context, str_error, Toast.LENGTH_SHORT).show();


                    }



                    listener.onSuccess();

                }
                else {
                    listener.onServiceError(response.code(),"Server error , try again");




                }

                //CommonMethods.hideDialog(spotsDialog);


            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {






                listener.serviceCallFailed(TAG,t);


            }
        });
    }
}
