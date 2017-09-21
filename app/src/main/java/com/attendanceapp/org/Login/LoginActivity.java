package com.attendanceapp.org.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.attendanceapp.org.R;
import com.attendanceapp.org.RegistrationActivity;
import com.attendanceapp.org.Verification.VerificationActivity;
import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.session.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private LoginPresenterImpl presenter;

    @BindView(R.id.edtMobile)
    EditText edtMobile;

    @BindView(R.id.edtMobileWrapper)
    TextInputLayout edtMobileWrapper;

    @BindView(R.id.btnLogin)
    Button btnLogin;


    @BindView(R.id.tvContact)
    TextView tvContact;

    private Context context = this;
    private SessionManager sessionManager;
    private HashMap<String, String> userDetails = new HashMap<String, String>();
    private SpotsDialog spotsDialog;
    private String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sessionManager = new SessionManager(context);
        userDetails = sessionManager.getSessionDetails();

        spotsDialog = new SpotsDialog(context);
        spotsDialog.setCancelable(false);

        presenter = new LoginPresenterImpl(this, context);
        btnLogin.setOnClickListener(this);

        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inten = new Intent(context , RegistrationActivity.class);
                startActivity(inten);
                finish();

            }
        });


    }


    @Override
    public void onClick(View view) {

        presenter.validateCredentials(edtMobile.getText().toString(), edtMobile.getText().toString());

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
    public void setUsernameError() {

        if (edtMobile.getText().toString().equals("")) {

            CommonMethods.showAlertDialog(context, "Error information", "Please enter mobile no");


        } else {
            CommonMethods.showAlertDialog(context, "Error information", "Invalid mobile no");
        }


    }


    @Override
    public void navigateToVerification() {

        Intent intent = new Intent(context, VerificationActivity.class);
        startActivity(intent);
        finish();


    }

    @Override
    public void serviceErrorCode(int errorCode) {

        CommonMethods.showErrorMessageWhenStatusNot200(context, errorCode);


    }

    @Override
    public void serviceCalledFailed(String tag, Throwable t) {


        CommonMethods.onFailure(context, TAG, t);

        CommonMethods.hideDialog(spotsDialog);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
