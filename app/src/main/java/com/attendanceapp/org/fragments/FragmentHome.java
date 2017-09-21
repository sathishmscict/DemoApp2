package com.attendanceapp.org.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.attendanceapp.org.DashboardActivity;
import com.attendanceapp.org.R;
import com.attendanceapp.org.SingleEmployeeActivity;
import com.attendanceapp.org.adapters.EmployeeAdapterRecyclerView;
import com.attendanceapp.org.api.ApiClient;
import com.attendanceapp.org.api.ApiInterface;
import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.model.EmployeeData;
import com.attendanceapp.org.session.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;


public class FragmentHome extends Fragment {


    private Context context = getActivity();


    private SessionManager sessionmanager;

    private HashMap<String, String> userDetails = new HashMap<String, String>();


    private String TAG = FragmentHome.class.getSimpleName();
    private SpotsDialog pDialog;

    /* private RecyclerView rv_clients;*/
    // private dbhandler db;
    // private SQLiteDatabase sd;
    //private ArrayList<ClientData> list_clients = new ArrayList<ClientData>();
    //private TextView txtnodata;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextInputLayout edtDateWrapper;
    private static EditText edtDate;
    private Button btnGo;


    private Unbinder unbinder;
    private SpotsDialog spotsDialog;

    @BindView(R.id.rvEmployee)
    RecyclerView rvEmployee;

    @BindView(R.id.tvNodata)
    TextView tvNodata;


    @BindView(R.id.tvTotalEmployee)
    TextView tvTotalEmployeel;


    @BindView(R.id.tvTotalPresent)
    TextView tvTotalPresent;

    @BindView(R.id.tvTotalAbsent)
    TextView tvTotalAbsent;

    @BindView(R.id.tvTotalLeave)
    TextView tvTotalLeave;

    @BindView(R.id.llTotal)
    LinearLayout llTotal;

    @BindView(R.id.llPresent)
    LinearLayout llPresent;

    @BindView(R.id.llAbsent)
    LinearLayout llAbsent;

    @BindView(R.id.llLeave)
    LinearLayout llLeave;


    @BindView(R.id.viewTotal)
    View viewTotal;

    @BindView(R.id.viewPresent)
    View viewPresent;

    @BindView(R.id.viewAbsent)
    View viewAbsent;

    @BindView(R.id.viewLeave)
    View viewLeave;


    private List<EmployeeData.Datum> list_employeeData = new ArrayList<EmployeeData.Datum>();
    private String DATA_TYPE = "all";


    public FragmentHome() {
        // Required empty public constructor
    }

	/*
     * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState); setHasOptionsMenu(true);
	 * 
	 * 
	 * }
	 */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container,
                false);

        unbinder = ButterKnife.bind(this, rootView);

        if (Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        pDialog = new SpotsDialog(getActivity());
        pDialog.setCancelable(false);


        sessionmanager = new SessionManager(getActivity());
        userDetails = sessionmanager.getSessionDetails();


        spotsDialog = new SpotsDialog(getActivity());
        spotsDialog.setCancelable(false);

        //  db = new dbhandler(getActivity());
        // sd = db.getWritableDatabase();
        // sd = db.getReadableDatabase();


        edtDateWrapper = (TextInputLayout) rootView.findViewById(R.id.edtDateWrapper);
        edtDate = (EditText) rootView.findViewById(R.id.edtDate);
        btnGo = (Button) rootView.findViewById(R.id.btnGo);


        setCurrentDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        edtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {


                    DialogFragment newFragment = new SelectDateFragment();
                    newFragment.show(getFragmentManager(), "DatePicker");


                }
                return false;
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //FillDataOnRecyclerView(edtDate.getText().toString());

                getAllEmployeeDetailsFromServer();

            }
        });


        LinearLayoutManager lManager = new LinearLayoutManager(getActivity());
        rvEmployee.setLayoutManager(lManager);


        btnGo.performClick();

        rvEmployee.addOnItemTouchListener(new CommonMethods.RecyclerTouchListener(getActivity(), rvEmployee, new CommonMethods.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                try {

                    Intent intent = new Intent(getActivity(),
                            SingleEmployeeActivity.class);

                    sessionmanager.setSelecetedEmployeeData(list_employeeData.get(position).getEmpId().toString(),list_employeeData.get(position).getDeptId().toString());

                    startActivity(intent);
                    getActivity().finish();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        llTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DATA_TYPE = "all";
                btnGo.performClick();

                viewTotal.setVisibility(View.VISIBLE);
                viewPresent.setVisibility(View.INVISIBLE);
                viewAbsent.setVisibility(View.INVISIBLE);
                viewLeave.setVisibility(View.INVISIBLE);
            }
        });

        llPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DATA_TYPE = "present";
                btnGo.performClick();


                viewTotal.setVisibility(View.INVISIBLE);
                viewPresent.setVisibility(View.VISIBLE);
                viewAbsent.setVisibility(View.INVISIBLE);
                viewLeave.setVisibility(View.INVISIBLE);

            }
        });

        llAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DATA_TYPE = "absent";
                btnGo.performClick();


                viewTotal.setVisibility(View.INVISIBLE);
                viewPresent.setVisibility(View.INVISIBLE);
                viewAbsent.setVisibility(View.VISIBLE);
                viewLeave.setVisibility(View.INVISIBLE);

            }
        });

        llLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DATA_TYPE = "leave";
                btnGo.performClick();


                viewTotal.setVisibility(View.INVISIBLE);
                viewPresent.setVisibility(View.INVISIBLE);
                viewAbsent.setVisibility(View.INVISIBLE);
                viewLeave.setVisibility(View.VISIBLE);

            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    private void getAllEmployeeDetailsFromServer() {


        CommonMethods.showDialog(spotsDialog);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.d(TAG, "URL GetAllEmployeeData  " + CommonMethods.WEBSITE + "GetAllEmployeeData?type=GetEmpDetails&CompanyId=" + userDetails.get(SessionManager.KEY_COMPANY_ID) + "&CheckDate=" + CommonMethods.convertToJsonDateFormat(edtDate.getText().toString()) + "&BranchId=" + 8 + "");
        /*apiService.getAllReferralsDetailsFromServer("referal", userDetails.get(SessionManager.KEY_USER_ID)).enqueue(new Callback<ReferralData>() {*/
        apiService.getAllEmployeeData("GetEmpDetails", "8", userDetails.get(SessionManager.KEY_COMPANY_ID), CommonMethods.convertToJsonDateFormat(edtDate.getText().toString())).enqueue(new Callback<EmployeeData>() {

            @Override
            public void onResponse(Call<EmployeeData> call, retrofit2.Response<EmployeeData> response) {

                Log.d(TAG, "GetAllEmployeeData Response Code : " + response.code());
                Log.d(TAG, "API Called Success" + response.raw().body().toString());


                if (response.code() == 200) {

                    String str_error = response.body().getMESSAGE();
                    String str_error_original = response.body().getORIGINALERROR();
                    boolean error_status = response.body().getERRORSTATUS();
                    boolean record_status = response.body().getRECORDS();


                    tvTotalEmployeel.setText(response.body().getTOTALEMPLOYEES().toString());
                    tvTotalPresent.setText(response.body().getTOTALPRESENT().toString());
                    tvTotalAbsent.setText(response.body().getTOTALABSENT().toString());
                    tvTotalLeave.setText(response.body().getTOTALLEAVE().toString());

                    list_employeeData.clear();

                    if (error_status == false) {

                        if (record_status == true) {


                            List<EmployeeData.Datum> temp_list_employeeData = response.body().getData();


                            for (int i = 0; i < temp_list_employeeData.size(); i++) {


                                if (DATA_TYPE.toLowerCase().equals("leave")) {
                                    if (!temp_list_employeeData.get(i).getLeaveStatus().equals("0")) {

                                        list_employeeData.add(temp_list_employeeData.get(i));
                                    }

                                } else if (DATA_TYPE.toLowerCase().equals("present")) {
                                    if (temp_list_employeeData.get(i).getTotalCount() > 0) {
                                        list_employeeData.add(temp_list_employeeData.get(i));
                                    }

                                } else if (DATA_TYPE.toLowerCase().equals("absent")) {
                                    if (temp_list_employeeData.get(i).getTotalCount() == 0 && temp_list_employeeData.get(i).getLeaveStatus().equals("0")) {
                                        list_employeeData.add(temp_list_employeeData.get(i));
                                    }

                                } else {
                                    list_employeeData.add(temp_list_employeeData.get(i));
                                }


                            }


                            EmployeeAdapterRecyclerView adapter = new EmployeeAdapterRecyclerView(getActivity(), list_employeeData,DATA_TYPE);
                            rvEmployee.setAdapter(adapter);


                            rvEmployee.setVisibility(View.VISIBLE);
                            tvNodata.setVisibility(View.GONE);

                        } else {
                            rvEmployee.setVisibility(View.GONE);
                            tvNodata.setVisibility(View.VISIBLE);
                            tvNodata.setText(getString(R.string.str_no_employee_records_found));


                        }
                    }


                } else {

                    Toast.makeText(context, "Something is wrong,try again  Error code :" + response.code(), Toast.LENGTH_SHORT).show();
                }


                CommonMethods.hideDialog(spotsDialog);


            }

            @Override
            public void onFailure(Call<EmployeeData> call, Throwable t) {

                Toast.makeText(context, "Unable to submit post to API.", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Unable to submit post to API." + t.getMessage());
                CommonMethods.hideDialog(spotsDialog);

            }
        });


    }


    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm, dd);
        }

        public void populateSetDate(int year, int month, int day) {

            setCurrentDate(year, month, day);
            //edtDate.setText(day+"-"+month+"-"+year);

        }

    }

    private static void setCurrentDate(int year, int month, int day) {

        ++month;
        String str_month = "";
        if (month <= 9) {
            str_month = "0" + String.valueOf(month);

        } else {
            str_month = String.valueOf(month);
        }

        String str_day;
        if (day <= 9) {
            str_day = "0" + String.valueOf(day);

        } else {
            str_day = String.valueOf(day);
        }

        edtDate.setText(str_day + "-" + str_month + "-" + year);

    }

  /*  private void FillDataOnRecyclerView()
    {
        String query = "select * from " + dbhandler.TABLE_CLIENTMASTER + "";
        Log.d(TAG, " Query : " + query);

        Cursor c = sd.rawQuery(query, null);

        Log.d(TAG, "Client Records : " + c.getCount() + "  found");

        list_clients.clear();
        if (c.getCount() > 0) {
            while (c.moveToNext()) {

                //ClientData(String clientid, String clientname, String moibleno, String bussiness, String address, String note,String email) {
                ClientData cd = new ClientData(c.getString(c.getColumnIndex(dbhandler.CLIENT_ID)), c.getString(c.getColumnIndex(dbhandler.CLIENT_NAME)), c.getString(c.getColumnIndex(dbhandler.CLIENT_MOBILE1)), c.getString(c.getColumnIndex(dbhandler.CLIENT_BUSSINESS)), c.getString(c.getColumnIndex(dbhandler.CLIENT_ADDRESS)), c.getString(c.getColumnIndex(dbhandler.CLIENT_NOTE)), c.getString(c.getColumnIndex(dbhandler.CLIENT_EMAIL)), c.getString(c.getColumnIndex(dbhandler.CLIENT_MOBILE2)), c.getString(c.getColumnIndex(dbhandler.CLIENT_LANDLINE)), c.getString(c.getColumnIndex(dbhandler.CLIENT_COMPANYNAME)), c.getString(c.getColumnIndex(dbhandler.CLIENT_DEVICE_TYPE)), c.getString(c.getColumnIndex(dbhandler.CLIENT_TYPE)), c.getString(c.getColumnIndex(dbhandler.CLIENT_LATTITUDE)), c.getString(c.getColumnIndex(dbhandler.CLIENT_LONGTITUDE)));
                list_clients.add(cd);


            }


            txtnodata.setVisibility(View.GONE);
            rv_clients.setVisibility(View.VISIBLE);

            ClientsDataAdapterRecyclerView adapter = new ClientsDataAdapterRecyclerView(getActivity(),list_clients,getActivity());
            rv_clients.setAdapter(adapter);


        }
        else
        {
            Toast.makeText(getActivity(), "No client records found", Toast.LENGTH_SHORT).show();

            txtnodata.setVisibility(View.VISIBLE);
            rv_clients.setVisibility(View.GONE);
        }


    }*/
    //onCreateView Completed


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        // pDialog.dismiss();
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
    }


}