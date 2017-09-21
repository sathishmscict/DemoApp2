package com.attendanceapp.org.Branch;

import com.attendanceapp.org.model.BranchData;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public interface BranchPresenter {



    void getBranchData();



    void deleteBranch();

    void onDestroy();

    void validateBranchData(int branchid,String branchname,String address,String password,String type);

    void editBranchDetails(BranchData.Datum data);

    void deleteBranchDetails(BranchData.Datum data);




}
