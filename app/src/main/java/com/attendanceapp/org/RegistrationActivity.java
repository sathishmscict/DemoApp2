package com.attendanceapp.org;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.attendanceapp.org.Login.LoginActivity;
import com.attendanceapp.org.Verification.VerificationActivity;
import com.attendanceapp.org.api.ApiClient;
import com.attendanceapp.org.api.ApiInterface;
import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.model.UserDataResponse;
import com.attendanceapp.org.session.SessionManager;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private Context context = this;


    @BindView(R.id.btnSaveData)
    Button btnSendRequest;

    @BindView(R.id.edtCompnayName)
    EditText edtCompanyName;

    @BindView(R.id.edtCompnayNameWrapper)
    TextInputLayout edtCompnayNameWrapper;

    @BindView(R.id.edtMobile)
    EditText edtMobile;

    @BindView(R.id.edtMobileWrapper)
    TextInputLayout edtMobileWrapper;

    @BindView(R.id.edtContactPersonName)
    EditText edtContactPersonName;

    @BindView(R.id.edtContactPersonNameWrapper)
    TextInputLayout edtContactPersonNameWrapper;

    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.edtEmailWrapper)
    TextInputLayout edtEmailWrapper;

    @BindView(R.id.edtWebsite)
    EditText edtWebsite;

    @BindView(R.id.edtWebsiteWrapper)
    TextInputLayout edtWebsiteWrapper;

    @BindView(R.id.edtAddress)
    EditText edtAddress;

    @BindView(R.id.edtAddressWrapper)
    TextInputLayout edtAddressWrapper;

    @BindView(R.id.edtRemark)
    EditText edtRemark;

    @BindView(R.id.edtRemarkWrapper)
    TextInputLayout edtRemarkWrapper;
    private String TAG = RegistrationActivity.class.getSimpleName();
    private SpotsDialog spotsDialog;
    private SessionManager sessionManager;
    private HashMap<String, String> userDetails= new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(context);
        userDetails =sessionManager.getSessionDetails();




        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        spotsDialog =  new SpotsDialog(context);
        spotsDialog.setCancelable(true);

        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean errorStatus=false;
                String errorMessage = "";
                if(edtCompanyName.getText().toString().equals(""))
                {

                    errorStatus = true;
                    errorMessage = errorMessage+" Enter Company name";

                }
                if(edtMobile.getText().toString().equals(""))
                {
                    errorStatus = true;
                    errorMessage = errorMessage+"\n Enter Mobileno";

                }
                else if(edtMobile.getText().toString().length() != 10)
                {
                    errorStatus = true;
                    errorMessage = errorMessage+"\n Invalid Mobileno";

                }

                if(edtCompanyName.getText().toString().equals(""))
                {

                    errorStatus = true;
                    errorMessage = errorMessage+"\n Enter Contact person name";

                }

                if(edtEmail.getText().toString().equals(""))
                {
                    errorStatus = true;
                    errorMessage = errorMessage+"\n Enter Email";

                }
                else
                {
                    if(CommonMethods.checkEmail(edtEmail.getText().toString()) == false)
                    {
                        errorStatus = true;
                        errorMessage = errorMessage+"\n Invalid Email";
                    }


                }

                if(edtAddress.getText().toString().equals(""))
                {
                    errorStatus = true;
                    errorMessage = errorMessage+"\n Enter Address";

                }



                if(errorStatus == true)
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Reistration Info!!!");
                    builder.setMessage(errorMessage);
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.cancel();
                            dialogInterface.dismiss();
                        }
                    });

                    builder.setCancelable(true);
                    builder.show();

                }
                else
                {
                    /**
                     * send registration details to server
                     */
                   // Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    sendRegistrationDetaislToServer();
                }



            }
        });




    }

    private void sendRegistrationDetaislToServer() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Log.d(TAG, "URL insertRegistration  : " + CommonMethods.WEBSITE + "insertRegistration?type=register&companyname="+ edtCompanyName.getText().toString() +"&contact_person_name="+ edtContactPersonName.getText().toString() +"&email="+ edtEmail.getText().toString() +"&address="+edtAddress.getText().toString() +"&website="+ edtWebsite.getText().toString() +"&mobileno="+ edtMobile.getText().toString() +"&remark="+ edtRemark.getText().toString() +"");
        apiInterface.insertRegistration("register",edtCompanyName.getText().toString(),edtContactPersonName.getText().toString(),edtEmail.getText().toString(),edtAddress.getText().toString(),edtWebsite.getText().toString(),edtMobile.getText().toString(),edtRemark.getText().toString()).enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {



                Log.d(TAG, "MonthlyAttendnaceStatus Response Code : " + response.code());

                if (response.code() == 200) {


                    String str_error = response.body().getMESSAGE();
                    String str_error_original = response.body().getORIGINALERROR();
                    boolean error_status = response.body().getERRORSTATUS();
                    boolean record_status = response.body().getRECORDS();

                    if (error_status == false) {
                        if (record_status == true) {
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

                                CommonMethods.hideDialog(spotsDialog);
                                Intent intent =new Intent(context , VerificationActivity.class);
                                startActivity(intent);
                                finish();



                            }

                        }
                    }
                }
                else {
                    CommonMethods.showErrorMessageWhenStatusNot200(context, response.code());
                }


                CommonMethods.hideDialog(spotsDialog);

            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {

                CommonMethods.onFailure(context, TAG, t);

                CommonMethods.hideDialog(spotsDialog);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
