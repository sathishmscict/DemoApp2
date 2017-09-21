package com.attendanceapp.org.Branch;

import android.text.TextUtils;
import android.util.Log;

import com.attendanceapp.org.api.ApiClient;
import com.attendanceapp.org.api.ApiInterface;
import com.attendanceapp.org.base.onFinishAPIListener;
import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.model.BranchData;
import com.attendanceapp.org.model.CommonReponse;
import com.attendanceapp.org.session.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public class BranchInteractorImpl implements BranchInteractor {


    private String TAG = BranchInteractorImpl.class.getSimpleName();


    @Override
    public boolean validation(String branchname, String address, String password,OnBranchDataCheckFinishedListener listener,SessionManager sessionManager,HashMap<String,String> userDetails) {

        boolean error = false;
        if (TextUtils.isEmpty(branchname)){
            listener.onBranchnameError();
            error = true;
            return error;
        }
        if (TextUtils.isEmpty(address)){
            listener.onAddressError();
            error = true;
            return error;
        }

        if (TextUtils.isEmpty(password)){
            listener.onPasswordError();
            error = true;
            return error;
        }




        return  error;
    }

    @Override
    public void getAllBranchDetailsFromServer(final SessionManager sessionManager, HashMap<String, String> userDetails, final onBranchAPICalledFinish listener ) {


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Log.d(TAG, "URL getAllBranchDetails  : " + CommonMethods.WEBSITE + "getAllBranchDetailsByCompnayID?type=allbranches&companyid=" + userDetails.get(SessionManager.KEY_COMPANY_ID) + "");


        apiInterface.getAllBranchDetails("allbranches", userDetails.get(SessionManager.KEY_COMPANY_ID)).enqueue(new Callback<BranchData>() {
            @Override
            public void onResponse(Call<BranchData> call, Response<BranchData> response) {

                Log.d(TAG, "getAllBranchDetails Response Code : " + response.code());

                if (response.code() == 200)
                {

                    if (response.body().getERRORSTATUS() == false)
                    {

                        if (response.body().getRECORDS() == true) {

                            //listener.onSuccess();


                            listener.sendBranchResultToPresenter(response.body().getData());


                            //Return data to presenter

                        } else {
                            //listener.showMessage("No records found");
                            listener.noDataFound();
                        }




                    } else {
                        listener.onServiceError(response.code(), "Server error,try again...\n" + response.body().getORIGINALERROR());

                    }


                } else {
                    listener.onServiceError(response.code(), "Server error, try again...");

                }


            }

            @Override
            public void onFailure(Call<BranchData> call, Throwable t) {


                listener.onFailureServiceCalled(TAG, t);

            }
        });

    }

    @Override
    public void insertBranchDataToServer(String branchname, String address, String password, SessionManager sessionManager, HashMap<String,String> userDetails, final onInsertBranchListener listener) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Log.d(TAG, "URL insertBranch  : " + CommonMethods.WEBSITE + "insertBranch?type=insertbranch&branchname="+ branchname +"&address="+ address +"&companyid="+ userDetails.get(SessionManager.KEY_COMPANY_ID) +"&password="+ password +"");


        apiInterface.insertBranchToServer("insertbranch",branchname,address,password, userDetails.get(SessionManager.KEY_COMPANY_ID)).enqueue(new Callback<BranchData>() {
            @Override
            public void onResponse(Call<BranchData> call, Response<BranchData> response) {

                Log.d(TAG, "insertBranch Response Code : " + response.code());

                if (response.code() == 200)
                {

                    if (response.body().getERRORSTATUS() == false)
                    {

                        if (response.body().getRECORDS() == true) {

                            //listener.onSuccess();


                            listener.insertSuccess();



                            //Return data to presenter

                        } else {
                            //listener.showMessage("No records found");
                            listener.noDataFound();
                        }




                    } else {
                        listener.onServiceError(response.code(), "Server error,try again...\n" + response.body().getORIGINALERROR());

                    }


                } else {
                    listener.onServiceError(response.code(), "Server error, try again...");

                }


            }

            @Override
            public void onFailure(Call<BranchData> call, Throwable t) {


                listener.onFailureServiceCalled(TAG, t);

            }
        });


    }

    @Override
    public void updateBranchDetailsToServer(String branchid, String branchname, String address, String password, SessionManager sessionManager, HashMap<String, String> userDetails, final onUpdateBranchListener listener) {



        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Log.d(TAG, "URL updatetBranch  : " + CommonMethods.WEBSITE + "updateBranch?type=updatebranch&branchid="+ branchid +"&branchname="+ branchname +"&address="+ address +"&companyid="+ userDetails.get(SessionManager.KEY_COMPANY_ID) +"&password="+ password +"");


        apiInterface.updateBranchToServer("updatebranch",branchid,branchname,address,password, userDetails.get(SessionManager.KEY_COMPANY_ID)).enqueue(new Callback<BranchData>() {
            @Override
            public void onResponse(Call<BranchData> call, Response<BranchData> response) {

                Log.d(TAG, "insertBranch Response Code : " + response.code());

                if (response.code() == 200)
                {

                    if (response.body().getERRORSTATUS() == false)
                    {

                        if (response.body().getRECORDS() == true) {

                            //listener.onSuccess();


                            listener.updateSuccess();



                            //Return data to presenter

                        } else {
                            //listener.showMessage("No records found");
                            listener.noDataFound();
                        }




                    } else {
                        listener.onServiceError(response.code(), "Server error,try again...\n" + response.body().getORIGINALERROR());

                    }


                } else {
                    listener.onServiceError(response.code(), "Server error, try again...");

                }


            }

            @Override
            public void onFailure(Call<BranchData> call, Throwable t) {


                listener.onFailureServiceCalled(TAG, t);

            }
        });

    }

    @Override
    public void deleteBranchDetailsToServer(SessionManager sessionManager, HashMap<String, String> userDetails, final onDeleteBranchListener listener, final BranchData.Datum brnachdata) {





        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Log.d(TAG, "URL deleteBranch  : " + CommonMethods.WEBSITE + "deleteBranch?type=deletebranch&branchid="+ brnachdata.getBRANCHID() +"&companyid="+ brnachdata.getCOMPANYID() +"");


        apiInterface.deleteBranchToServer("deletebranch",String.valueOf(brnachdata.getBRANCHID()),String.valueOf(brnachdata.getCOMPANYID())).enqueue(new Callback<BranchData>() {
            @Override
            public void onResponse(Call<BranchData> call, Response<BranchData> response) {

                Log.d(TAG, "deleteBranch Response Code : " + response.code());

                if (response.code() == 200)
                {

                    if (response.body().getERRORSTATUS() == false)
                    {

                        if (response.body().getRECORDS() == true) {

                            //listener.onSuccess();


                            listener.deleteSuccess(brnachdata);




                            //Return data to presenter

                        } else {
                            //listener.showMessage("No records found");
                            listener.noDataFound();

                        }




                    } else {
                        listener.onServiceError(response.code(), "Server error,try again...\n" + response.body().getORIGINALERROR());

                    }


                } else {
                    listener.onServiceError(response.code(), "Server error, try again...");

                }


            }

            @Override
            public void onFailure(Call<BranchData> call, Throwable t) {


                listener.onFailureServiceCalled(TAG, t);

            }
        });



    }


}
