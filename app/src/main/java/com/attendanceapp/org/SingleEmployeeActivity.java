package com.attendanceapp.org;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.attendanceapp.org.session.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class SingleEmployeeActivity extends AppCompatActivity {


    @BindView(R.id.input_layout_fromdate)
     TextInputLayout input_layout_fromdate;


    @BindView(R.id.input_layout_todate)
    TextInputLayout input_layout_todate;

    @BindView(R.id.edtFromDate)
    EditText edtFromDate;


    @BindView(R.id.edtToDate)
    EditText edtToDate;

    @BindView(R.id.btnGo)
    Button     btnGo;









    private Context context=this;
    private SpotsDialog spotDialog;
    private SessionManager sessionManager;
    private HashMap<String, String> userDetails= new HashMap<String, String>();

    static final int DATE_PICKER_ID = 1111;
    static final int DATE_PICKER_ID1 = 1112;
    private int y, m, d;
    private int spm;
    private String startDate;
    private String showsdate;
    private int ty, td, tm;
    private String enddate;
    protected String endsdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_employee);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spotDialog = new SpotsDialog(context);
        spotDialog.setCancelable(true);
        //showDialog();

        sessionManager = new SessionManager(context);
        userDetails = sessionManager.getSessionDetails();


        //input_layout_fromdate = (TextInputLayout) findViewById(R.id.input_layout_fromdate);
       // input_layout_todate = (TextInputLayout) findViewById(R.id.input_layout_todate);
       // edtFromDate = (EditText) findViewById(R.id.edtFromDate);
       // edtToDate = (EditText) findViewById(R.id.edtToDate);
        //btnGo = (Button) findViewById(R.id.btnGo);

        final Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);
        d=1;

        spm = m + 1;
        if (spm <= 9) {
            String mm = "0" + spm;
            startDate = y + "-" + mm + "-" + d;
            showsdate = d + "-" + mm + "-" + y;
        } else {
            startDate = y + "-" + spm + "-" + d;
            showsdate = d + "-" + spm + "-" + y;
        }

        String str_day;
        if (d <= 9) {
            str_day = "0" + String.valueOf(d);

        } else {
            str_day = String.valueOf(d);
        }
        showsdate = str_day + "-" + spm + "-" + y;

        edtFromDate.setText(showsdate);




        ty = cal.get(Calendar.YEAR);
        tm = cal.get(Calendar.MONTH);
        td = cal.get(Calendar.DAY_OF_MONTH);

        spm = tm + 1;
        if (spm <= 9) {
            String mm = "0" + spm;
            enddate = ty + "-" + mm + "-" + td;
            endsdate = td + "-" + mm + "-" + ty;
        } else {
            enddate = ty + "-" + spm + "-" + td;
            endsdate = td + "-" + spm + "-" + ty;
        }

        if (td <= 9) {
            str_day = "0" + String.valueOf(td);

        } else {
            str_day = String.valueOf(td);
        }
        endsdate = str_day + "-" + spm + "-" + y;

        edtToDate.setText(endsdate);

        //Set From Date
        edtFromDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    showDialog(DATE_PICKER_ID);
                }
                return false;
            }
        });
        edtToDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    showDialog(DATE_PICKER_ID1);
                }
                return false;
            }
        });


    }
    //onCreate Completed



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, y, m, d);
            case DATE_PICKER_ID1:
                return new DatePickerDialog(this, pickerListener1, ty, tm, td);
        }
        return null;

    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {


        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            y = selectedYear;
            m = selectedMonth;
            d = selectedDay;

            // Show selected date

            //   String startDate;
            int spm = m + 1;
         /*   if (spm <= 9) {
                String mm = "0" + spm;
                startDate = y + "-" + mm + "-" + d;
                showsdate = d + "-" + mm + "-" + y;
            } else {
                startDate = y + "-" + spm + "-" + d;
                showsdate = d + "-" + spm + "-" + y;
            }*/



            String str_month = "";
            if (spm <= 9) {
                str_month = "0" + String.valueOf(spm);

            } else {
                str_month = String.valueOf(spm);
            }


            String str_day;
            if (d <= 9) {
                str_day = "0" + String.valueOf(d);

            } else {
                str_day = String.valueOf(d);
            }
            showsdate = str_day + "-" + str_month + "-" + y;

            edtFromDate.setText(showsdate);

        }
    };


    private DatePickerDialog.OnDateSetListener pickerListener1 = new DatePickerDialog.OnDateSetListener() {


        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            ty = selectedYear;
            tm = selectedMonth;
            td = selectedDay;

            // Show selected date




            String str_month = "";
            if (spm <= 9) {
                str_month = "0" + String.valueOf(spm);

            } else {
                str_month = String.valueOf(spm);
            }


            String str_day;
            if (d <= 9) {
                str_day = "0" + String.valueOf(d);

            } else {
                str_day = String.valueOf(d);
            }
            enddate = str_day + "-" + str_month + "-" + y;




            edtToDate.setText(endsdate);

        }
    };

}
