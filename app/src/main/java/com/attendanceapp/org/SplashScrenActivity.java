package com.attendanceapp.org;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.attendanceapp.org.Branch.BranchActivity;
import com.attendanceapp.org.Login.LoginActivity;
import com.attendanceapp.org.animation.PulseAnimation;
import com.attendanceapp.org.animation.RotateAnimation;
import com.attendanceapp.org.animation.ShakeAnimation;
import com.attendanceapp.org.session.SessionManager;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashScrenActivity extends AppCompatActivity {

    @BindView(R.id.tvSignUp)
    TextView tvSignUp;


    private Context context=this;
    private SessionManager sessionManager;
    private HashMap<String, String> userDetails= new HashMap<String, String>();
    private LinearLayout llSingup;
    private ImageView ivAppLogo;
    private long AUTO_HIDE_DELAY_MILLIS=1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        //Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_scren);

        ButterKnife.bind(this);

        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sessionManager = new SessionManager(context);
        userDetails = sessionManager.getSessionDetails();





        ivAppLogo =(ImageView)findViewById(R.id.ivAppLogo);

        //initPulse(ivAppLogo);


        tvSignUp.setVisibility(View.GONE);



        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity



                if(userDetails.get(SessionManager.KEY_USER_ID).equals("0"))
                {

                    //tvSignUp.setVisibility(View.VISIBLE);
                    //Animation Types
                    // initRotation();
                    // initFlip(llSingup);
                    //initPulse(tvSignUp);
                    //initShake();

                    Intent intent = new Intent(context , LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
                else
                {

                    Intent inten = new Intent(context,BranchActivity.class);
                    inten.putExtra("DEFAULT","1");
                    startActivity(inten);
                    finish();



                }






            }
        }, AUTO_HIDE_DELAY_MILLIS);







    }
    //onCreate Completed


    public void SignInUser(View view)
    {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        finish();




    }
/*
    public void SignUpUser(View view)
    {
        Intent intent = new Intent(context, SignUpUserActivity.class);
        startActivity(intent);
        finish();



    }*/

    private void initShake(TextView viewName) {

        //final ImageView shakeImage = (ImageView)findViewById(R.id.shake);

        ShakeAnimation.create().with(viewName)
                .setDuration(2000)
                .setRepeatMode(ShakeAnimation.RESTART)
                .setRepeatCount(ShakeAnimation.INFINITE)
                .start();
    }

    private void initRotation(TextView viewName) {


        RotateAnimation.create().with(viewName)
                .setRepeatCount(RotateAnimation.INFINITE)
                .setRepeatMode(RotateAnimation.RESTART)
                .setDuration(2000)
                .start();
    }

    private void initPulse(ImageView viewName) {

        PulseAnimation.create().with(viewName)
                .setDuration(600)
                .setRepeatCount(1)
                .setRepeatMode(PulseAnimation.REVERSE)
                .start();


    }

    private void initPulse(TextView viewName) {

        PulseAnimation.create().with(viewName)
                .setDuration(600)
                .setRepeatCount(1)
                .setRepeatMode(PulseAnimation.REVERSE)
                .start();


    }




}
