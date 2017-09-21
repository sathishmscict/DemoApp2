package com.attendanceapp.org.Branch;

import com.attendanceapp.org.Verification.VerificationInteractor;
import com.attendanceapp.org.base.onFinishAPIListener;
import com.attendanceapp.org.model.BranchData;
import com.attendanceapp.org.session.SessionManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public interface BranchInteractor {


    interface  onBranchAPICalledFinish extends onFinishAPIListener
    {
        void sendBranchResultToPresenter(List<BranchData.Datum> branchData);


    }
    interface  OnBranchDataCheckFinishedListener
    {
        void onBranchnameError();

        void onAddressError();

        void onPasswordError();

       // boolean onSuccess();

    }

    interface  onInsertBranchListener extends  onFinishAPIListener
    {

        void insertSuccess();


    }

    interface onUpdateBranchListener extends  onFinishAPIListener
    {

        void updateSuccess();

    }

    interface onDeleteBranchListener extends onFinishAPIListener
    {
        void deleteSuccess(BranchData.Datum brnachdata);
    }



    boolean validation(String branchname, String address, String password,OnBranchDataCheckFinishedListener listener,SessionManager sessionManager,HashMap<String,String> userDetails);

    void getAllBranchDetailsFromServer(SessionManager sessionManager , HashMap<String,String> userDetails, onBranchAPICalledFinish listener);

    void insertBranchDataToServer(String branchname,String address,String password,SessionManager sessionManager,HashMap<String,String> userDetails,onInsertBranchListener listener);

    void updateBranchDetailsToServer(String branchid,String branchname, String address, String password, SessionManager sessionManager, HashMap<String,String> userDetails, final onUpdateBranchListener listener);

    void deleteBranchDetailsToServer(SessionManager sessionManager , HashMap<String,String> userDetails, onDeleteBranchListener listener, BranchData.Datum brnachdata);


}
