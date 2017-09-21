package com.attendanceapp.org.Branch;

import android.content.Context;
import android.content.Intent;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.attendanceapp.org.DashboardActivity;
import com.attendanceapp.org.Login.LoginActivity;
import com.attendanceapp.org.R;
import com.attendanceapp.org.Verification.VerificationActivity;
import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.model.BranchData;
import com.attendanceapp.org.session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class BranchActivity extends AppCompatActivity implements BranchView {

    private Menu menu;
    private MenuItem menu_show;
    private Context context = this;
    private SessionManager sessionManager;
    private HashMap<String, String> userDetails = new HashMap<String, String>();
    private SpotsDialog spotsDialog;
    private BranchPresenterImpl presenter;


    @BindView(R.id.llAdddData)
    LinearLayout llAdddData;

    @BindView(R.id.llShowData)
    LinearLayout llShowData;

    @BindView(R.id.tvNodata)
    TextView tvNoData;

    @BindView(R.id.rvBranches)
    RecyclerView rvBranches;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.btnSaveData)
    Button btnSaveData;

    @BindView(R.id.edtBranchName)
    EditText edtBranchName;

    @BindView(R.id.edtBranchNameWrapper)
    TextInputLayout edtBranchNameWrapper;

    @BindView(R.id.edtAddress)
    EditText edtAddress;

    @BindView(R.id.edtAddressWrapper)
    TextInputLayout edtAddressWrapper;

    @BindView(R.id.edtPassword)
    EditText edtPassword;

    @BindView(R.id.edtPasswordWrapper)
    TextInputLayout edtPasswordWrapper;


    private Integer BRANCHID = 0;
    private List<BranchData.Datum> list_branchData = new ArrayList<BranchData.Datum>();
    private BranchAdapterRecyclerView adapter;
    private String DEFAULT = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }


        setTitle("Select Branch");

        sessionManager = new SessionManager(context);
        userDetails = sessionManager.getSessionDetails();

        spotsDialog = new SpotsDialog(context);
        spotsDialog.setCancelable(true);

        try {
            DEFAULT = getIntent().getStringExtra("DEFAULT");
        } catch (Exception e) {
            DEFAULT = "0";
            e.printStackTrace();
        }

        if (userDetails.get(SessionManager.KEY_USER_ID).equals("0")) {

            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
            finish();

        } else if (userDetails.get(SessionManager.KEY_USER_VERIFICATION_STATUS).equals("0") && !userDetails.get(SessionManager.KEY_USER_ID).equals("0")) {
            Intent intent = new Intent(context, VerificationActivity.class);
            startActivity(intent);
            finish();
        } else if (userDetails.get(SessionManager.KEY_TOTAL_BRNACHES).equals("0")) {

       /*     Intent intent = new Intent(context , BranchActivity.class);
            startActivity(intent);
            finish();*/
        }


        presenter = new BranchPresenterImpl(this, context);

        LinearLayoutManager lManager = new LinearLayoutManager(context);
        rvBranches.setLayoutManager(lManager);

        fab.setVisibility(View.GONE);


        llAdddData.setVisibility(View.VISIBLE);
        llShowData.setVisibility(View.GONE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fab.setVisibility(View.GONE);
                llShowData.setVisibility(View.GONE);
                llAdddData.setVisibility(View.VISIBLE);
                menu_show.setVisible(true);

            }
        });


        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                edtBranchNameWrapper.setErrorEnabled(false);
                edtAddressWrapper.setErrorEnabled(false);
                edtPasswordWrapper.setErrorEnabled(false);


                presenter.validateBranchData(BRANCHID, edtBranchName.getText().toString(), edtAddress.getText().toString(), edtPassword.getText().toString(), btnSaveData.getText().toString());
                BRANCHID = 0;

                /*if(BRANCHID == 0)
                {

                }
                else
                {
                    presenter.validateBranchData(BRANCHID,edtBranchName.getText().toString(),edtAddress.getText().toString(),edtPassword.getText().toString(),btnSaveData.getText().toString());
                }*/


                // presenter.insertBranch();


            }
        });


        rvBranches.addOnItemTouchListener(new CommonMethods.RecyclerTouchListener(context, rvBranches, new CommonMethods.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                try {

                    Intent intent = new Intent(context,
                            DashboardActivity.class);
                    sessionManager.setSelectedBranchDetails(list_branchData.get(position).getBRANCHID().toString(), list_branchData.get(position).getBRANCHNAME());

                    startActivity(intent);
                    finish();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        presenter.getBranchData();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_branch, menu);


        this.menu = menu;
        menu_show = (MenuItem) menu.findItem(R.id.menu_show);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigateToDashboard();
        } else if (item.getItemId() == R.id.menu_show) {

            menu_show.setVisible(false);
            llAdddData.setVisibility(View.GONE);
            llShowData.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);

            presenter.getBranchData();


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

        CommonMethods.showDialog(spotsDialog);

    }

    @Override
    public void hideProgress() {

        CommonMethods.hideDialog(spotsDialog);

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDashboard() {
        Intent intent = new Intent(context, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void serviceErrorCode(int errorCode) {
        CommonMethods.showErrorMessageWhenStatusNot200(context, errorCode);
    }

    @Override
    public void serviceCalledFailed(String tag, Throwable t) {
        CommonMethods.onFailure(context, tag, t);

        CommonMethods.hideDialog(spotsDialog);
    }

    @Override
    public void FillDataToRecyclerView(List<BranchData.Datum> branchData) {

        sessionManager.setTotalBranches(String.valueOf(list_branchData.size()));
        this.list_branchData = branchData;

        //Check if activity has been staeted from splashs creen then display brnaches
        if (DEFAULT.equals("1")) {

            if (!userDetails.get(SessionManager.KEY_SELECTED_BRANCH_ID).equals("0") && branchData.size() == 1) {
                Intent intent = new Intent(context, DashboardActivity.class);
                startActivity(intent);

            } else {
                llAdddData.setVisibility(View.GONE);
                llShowData.setVisibility(View.VISIBLE);
                fab.setVisibility(View.VISIBLE);
                try {
                    menu_show.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter = new BranchAdapterRecyclerView(context, list_branchData, presenter);
                rvBranches.setAdapter(adapter);
            }

        } else {

            adapter = new BranchAdapterRecyclerView(context, list_branchData, presenter);
            rvBranches.setAdapter(adapter);
        }


    }

    @Override
    public void noDataFound() {

        rvBranches.setVisibility(View.GONE);
        tvNoData.setVisibility(View.VISIBLE);

        fab.setVisibility(View.VISIBLE);


    }

    @Override
    public void branchNameError() {


        edtAddressWrapper.setErrorEnabled(true);
        edtAddressWrapper.setError("Please enter address");


    }

    @Override
    public void addressError() {

        edtAddressWrapper.setErrorEnabled(true);
        edtAddressWrapper.setError("Please enter address");


    }

    @Override
    public void passwordError() {

        edtPasswordWrapper.setErrorEnabled(true);
        edtPasswordWrapper.setError("Please enter password");

    }

    @Override
    public void successInsertBranch() {
//Get all details from server.
        edtAddress.setText("");
        edtPassword.setText("");
        edtBranchName.setText("");
        Toast.makeText(context, "Branch has been added", Toast.LENGTH_SHORT).show();
        //  presenter.getBranchData();

    }

    @Override
    public void successUpdateDetails() {

        edtAddress.setText("");
        edtPassword.setText("");
        edtBranchName.setText("");
        Toast.makeText(context, "Branch details has been updated", Toast.LENGTH_SHORT).show();
        btnSaveData.setText("Save Sata");

        llAdddData.setVisibility(View.GONE);
        llShowData.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);

        presenter.getBranchData();


    }

    @Override
    public void successDeleteBranchDetails(BranchData.Datum brnachdata) {

        list_branchData.remove(brnachdata);
        adapter.notifyDataSetChanged();

        //edtAddress.setText("");
        //edtPassword.setText("");
        //edtBranchName.setText("");
        Toast.makeText(context, "Branch details has been deleted", Toast.LENGTH_SHORT).show();
        btnSaveData.setText("Save Sata");


        llAdddData.setVisibility(View.GONE);
        llShowData.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);

        //presenter.getBranchData();

    }

    @Override
    public void FillEditBranchDetails(BranchData.Datum data) {

        edtPassword.setText(data.getBRANCHPASSWORD());
        edtAddress.setText(data.getBRANCHADDRESS());
        edtBranchName.setText(data.getBRANCHNAME());

        BRANCHID = data.getBRANCHID();

        btnSaveData.setText("Update Data");

        llAdddData.setVisibility(View.VISIBLE);
        llShowData.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(context, DashboardActivity.class);
        startActivity(intent);
        finish();

    }
}
