package com.attendanceapp.org.Branch;

import android.content.Context;

import com.attendanceapp.org.base.onFinishAPIListener;
import com.attendanceapp.org.model.BranchData;
import com.attendanceapp.org.session.SessionManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public class BranchPresenterImpl implements BranchPresenter, BranchInteractor.onBranchAPICalledFinish,BranchInteractor.OnBranchDataCheckFinishedListener,BranchInteractor.onInsertBranchListener,BranchInteractor.onUpdateBranchListener,BranchInteractor.onDeleteBranchListener {

    private final BranchView branchView;
    private final BranchInteractorImpl branchInteractor;
    private final SessionManager sessionManager;
    private final HashMap<String, String> userDetails;

    public BranchPresenterImpl(BranchView view, Context context) {
        this.branchView = view;
        this.branchInteractor = new BranchInteractorImpl();
        this.sessionManager = new SessionManager(context);
        this.userDetails = sessionManager.getSessionDetails();


    }

    @Override
    public void getBranchData() {

        if (branchView != null) {

            branchView.showProgress();

        }

        branchInteractor.getAllBranchDetailsFromServer(sessionManager, userDetails, this);

    }



    @Override
    public void deleteBranch() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void validateBranchData(int branchid,String branchname, String address, String password,String type) {

        if (branchView != null)
        {

           // branchView.showProgress();

        }

       boolean response =  branchInteractor.validation(branchname,address,password,this,sessionManager,userDetails);

        if(response == false)
        {
            if(type.toLowerCase().equals("save data"))
            {
                branchInteractor.insertBranchDataToServer(branchname,address,password,sessionManager,userDetails,this);
            }
            else
            {
                branchInteractor.updateBranchDetailsToServer(String.valueOf(branchid),branchname,address,password,sessionManager,userDetails,this);

            }

        }


    }

    @Override
    public void editBranchDetails(BranchData.Datum data) {

        if(branchView!=null)
        {
            branchView.FillEditBranchDetails(data);

        }
    }

    @Override
    public void deleteBranchDetails(BranchData.Datum data) {

        if(branchView!= null)
        {

            branchInteractor.deleteBranchDetailsToServer(sessionManager,userDetails,this,data);

        }
    }


    @Override
    public void sendBranchResultToPresenter(List<BranchData.Datum> branchData) {

        if(branchView != null)
        {
            branchView.hideProgress();
            branchView.FillDataToRecyclerView(branchData);

        }

    }

    @Override
    public void onBranchnameError() {

        if(branchView!= null)
        {
            branchView.branchNameError();

        }


    }

    @Override
    public void onAddressError() {

        if(branchView!= null)
        {
            branchView.addressError();

        }

    }

    @Override
    public void onPasswordError() {

        if(branchView!= null)
        {
            branchView.passwordError();

        }

    }

 /*   @Override
    public void onSuccess() {

        if(branchView!=null)
        {
            branchView.showProgress();
            insertBranch();

        }



    }*/

    @Override
    public void onSuccess() {
        if(branchView!=null)
        {

        }
    }

    @Override
    public void onServiceError(int errorCode, String errorMessage) {

        if(branchView!=null)
        {
            branchView.hideProgress();
            branchView.serviceErrorCode(errorCode);

        }
    }

    @Override
    public void onFailureServiceCalled(String TAG, Throwable T) {
        if(branchView!=null)
        {
            branchView.hideProgress();
            branchView.serviceCalledFailed(TAG,T);

        }

    }

    @Override
    public void onServiceErrorMessage(String message) {

        if (branchView != null) {

            branchView.showMessage(message);
        }

    }

    @Override
    public void noDataFound() {

        if(branchView!= null)
        {

       branchView.noDataFound();

        }

    }

    @Override
    public void insertSuccess() {

        if(branchView!= null)
        {
            branchView.hideProgress();

            branchView.successInsertBranch();

        }
    }

    @Override
    public void updateSuccess() {

        if(branchView!= null)
        {

            branchView.hideProgress();

            branchView.successUpdateDetails();

        }
    }


    @Override
    public void deleteSuccess(BranchData.Datum brnachdata) {

        if(branchView!= null)
        {
            branchView.successDeleteBranchDetails(brnachdata);

        }
    }
}
