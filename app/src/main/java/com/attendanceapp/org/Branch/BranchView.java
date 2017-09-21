package com.attendanceapp.org.Branch;

import com.attendanceapp.org.base.MvpView;
import com.attendanceapp.org.model.BranchData;

import java.util.List;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public interface BranchView extends MvpView {



    void navigateToDashboard();


    void FillDataToRecyclerView(List<BranchData.Datum> branchData);

    void noDataFound();

    void branchNameError();

    void addressError();

    void passwordError();

    void successInsertBranch();
    void successUpdateDetails();
    void successDeleteBranchDetails(BranchData.Datum brnachdata);

    void FillEditBranchDetails(BranchData.Datum data);





}
