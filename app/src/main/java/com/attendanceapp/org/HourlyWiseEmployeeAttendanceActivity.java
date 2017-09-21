package com.attendanceapp.org;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.attendanceapp.org.api.ApiClient;
import com.attendanceapp.org.api.ApiInterface;
import com.attendanceapp.org.helper.CommonMethods;
import com.attendanceapp.org.helper.ExpandableHeightGridView;
import com.attendanceapp.org.model.EmployeeAttendance;
import com.attendanceapp.org.session.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HourlyWiseEmployeeAttendanceActivity extends AppCompatActivity {


    Activity a;
    private TextView currentMonth;
    // private Button selectedDayMonthYearButton;
    private ImageView prevMonth;
    private ImageView nextMonth;
    private ExpandableHeightGridView calendarView;
    private GridCellAdapter adapter;
    private Calendar _calendar;
    @SuppressLint("NewApi")
    private String deptid;
    private String empid;
    HashMap<String, String> userDetails;
    SessionManager session;
    private int month, year;
    //dbhandler db;
    //SQLiteDatabase sd;
    private ArrayList<String> lstpresent = new ArrayList<String>();
    private ArrayList<String> lstabsent = new ArrayList<String>();
    private ArrayList<String> lsthalf = new ArrayList<String>();
    private ArrayList<String> lstleave = new ArrayList<String>();
    private ArrayList<String> lsttest = new ArrayList<String>();
    private ArrayList<String> lstall = new ArrayList<String>();

    private SessionManager sessionmanager;

    private final DateFormat dateFormatter = new DateFormat();

    private GridView gridday;
    private static final String dateTemplate = "MMMM yyyy";
    String[] days = {"M", "T", "W", "T", "F", "S", "S"};

    private TextView presentdays;

    private TextView absentdays;
    private TextView holidays;
    private TextView testdays;

    private TextView totaldays;

    private TextView percentage;
    private Context context = this;
    private CoordinatorLayout coordinatorLayout;
    private TextView targetdays;
    private String currenSelectedtdate = "";
    //private TextView SubGoalName;
   // private TextView txtdate;
    /*private SwipeRefreshLayout swipeRefreshLayout;*/

    // ArrayList<HolidayItem> holidaylist=new ArrayList<HolidayItem>();

    ArrayList<String> SUBTASK_SID = new ArrayList<String>();
    private MenuItem acmenu;
    private String TAG = HourlyWiseEmployeeAttendanceActivity.class.getSimpleName();
    private SpotsDialog spotsDialog;


    final HashMap<String, String> map = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_wise_employee_attendance);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinateLayout);
        spotsDialog = new SpotsDialog(context);
        spotsDialog.setCancelable(false);

        sessionmanager = new SessionManager(context);
        userDetails = sessionmanager.getSessionDetails();

        try {
            prevMonth = (ImageView) findViewById(R.id.prevMonth);
            // prevMonth.setOnClickListener(this);
            session = new SessionManager(getApplicationContext());

            userDetails = new HashMap<String, String>();
            userDetails = session.getSessionDetails();
            currentMonth = (TextView) findViewById(R.id.currentMonths);
            presentdays = (TextView) findViewById(R.id.presentdays);
            absentdays = (TextView) findViewById(R.id.absentdays);
            holidays = (TextView) findViewById(R.id.lblholidays);
            testdays = (TextView) findViewById(R.id.lbltestDays);
            totaldays = (TextView) findViewById(R.id.totaldays);
            targetdays = (TextView) findViewById(R.id.lbltarget);

         //   txtdate = (TextView) findViewById(R.id.txtdate);

            percentage = (TextView) findViewById(R.id.percentage);
           // SubGoalName = (TextView) findViewById(R.id.txtsubgoalname);
            // currentMonth.setText(DateFormat.format(dateTemplate,
            // _calendar.getTime()));
            //Commented by sathish
            //db = new dbhandler(getApplicationContext());
            // sd = db.getReadableDatabase();
            nextMonth = (ImageView) findViewById(R.id.nextMonth);
            calendarView = (ExpandableHeightGridView) findViewById(R.id.calendar);
            gridday = (GridView) findViewById(R.id.gridView1);


            _calendar = Calendar.getInstance(Locale.getDefault());
            month = _calendar.get(Calendar.MONTH) + 1;
            year = _calendar.get(Calendar.YEAR);

            // selectedDayMonthYearButton =
            // (Button)getActivity().findViewById(R.id.selectedDayMonthYears);
            // selectedDayMonthYearButton.setText("Selected: ");

            prevMonth = (ImageView) findViewById(R.id.prevMonth);
            prevMonth.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (month <= 1) {
                        month = 12;
                        year--;
                    } else {
                        month--;
                    }


                    setGridCellAdapterToDate(month, year);
                }
            });

            currentMonth = (TextView) findViewById(R.id.currentMonths);
            currentMonth.setText(DateFormat.format(dateTemplate,
                    _calendar.getTime()));

            nextMonth = (ImageView) findViewById(R.id.nextMonth);
            nextMonth.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (month > 11) {
                        month = 1;
                        year++;
                    } else {
                        month++;
                    }

                    setGridCellAdapterToDate(month, year);
                }
            });


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        getDataFromServer();

       // FillDataOnCalendar();
    }

    private void getDataFromServer() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Log.d(TAG, "URL GetEmployeMothlyAttendanceStatusDetails  : " + CommonMethods.WEBSITE + "GetEmployeMothlyAttendanceStatusDetails?type=MonthlyAttendnaceStatus&companyid=" + userDetails.get(SessionManager.KEY_COMPANY_ID) + "&empid=" + userDetails.get(SessionManager.KEY_SELECETED_EMPLOYEE_ID) + "&deptid=" + userDetails.get(SessionManager.KEY_SELECETED_DEPT_ID) + "&branchid=" + userDetails.get(SessionManager.KEY_SELECTED_BRANCH_ID) + "");
        apiInterface.getEmployeMothlyAttendanceStatusDetails("MonthlyAttendnaceStatus", userDetails.get(SessionManager.KEY_COMPANY_ID), userDetails.get(SessionManager.KEY_SELECETED_EMPLOYEE_ID), userDetails.get(SessionManager.KEY_SELECETED_DEPT_ID), userDetails.get(SessionManager.KEY_SELECTED_BRANCH_ID)).enqueue(new Callback<EmployeeAttendance>() {
            @Override
            public void onResponse(Call<EmployeeAttendance> call, Response<EmployeeAttendance> response) {


                Log.d(TAG, "MonthlyAttendnaceStatus Response Code : " + response.code());

                if (response.code() == 200)
                {

                    lstabsent.clear();
                    lstpresent.clear();
                    lsthalf.clear();
                    lsttest.clear();
                    lstall.clear();
                    lstleave.clear();
                    map.clear();


                    String str_error = response.body().getMESSAGE();
                    String str_error_original = response.body().getORIGINALERROR();
                    boolean error_status = response.body().getERRORSTATUS();
                    boolean record_status = response.body().getRECORDS();

                    if (error_status == false)
                    {
                        if (record_status == true)
                        {

                            List<EmployeeAttendance.Datum> arr = response.body().getData();

                            int currenttotalMinutes = 0;



                            for (int i = 0; i < arr.size(); i++)
                            {

                                String workingHours[] = arr.get(i).getWorkingHours().split(":");
                                Log.d(TAG, "Total Hours : " + workingHours[0] + " Total Minutes : " + workingHours[1]);
                                int totalMinutes = (Integer.parseInt(workingHours[0]) * 60) + Integer.parseInt(workingHours[1]);
                                Log.d(TAG, "Total Minutes : " + totalMinutes);


                                String d;
                                if (arr.get(i).getDay1().equals("L")) {


                                    d="1";



                                    lstleave.add("L");
                                    lstall.add("Y");
                                    map.put("" + d, "L");

                                } else if (TextUtils.isEmpty(arr.get(i).getDay1().toLowerCase())) {
                                    d="1";
                                    lstabsent.add("A");
                                    lstall.add("S");
                                    map.put("" + d, "A");


                                } else {


                                    d="1";

                                    String selected_date = arr.get(i).getYear().toString() + "-" + arr.get(i).getMonth().toString() + "-" + 1;


                                    Log.d("Goal Track Date : ", selected_date);

                                    String days[] = selected_date.split("-");
                                    int day = Integer.parseInt(days[2]);
                                    if (day < 10) {
                                        d = "0" + day;
                                    } else {
                                        d = "" + day;
                                    }


                                    String currentWorkingHours[] = arr.get(i).getDay1().split(":");
                                    Log.d(TAG, selected_date + " Total Hours : " + currentWorkingHours[0] + " Total Minutes : " + currentWorkingHours[1]);
                                    currenttotalMinutes = (Integer.parseInt(currentWorkingHours[0]) * 60) + Integer.parseInt(currentWorkingHours[1]);
                                    Log.d(TAG, selected_date + " Total Minutes : " + currenttotalMinutes);


                                    if (totalMinutes >= currenttotalMinutes) {
                                        lstpresent.add("P");
                                        lstall.add("Y");
                                        map.put("" + d, "P");
                                    } else if ((totalMinutes / 2) >= currenttotalMinutes) {


                                        lsthalf.add("H");
                                        lstall.add("S");
                                        map.put("" + d, "H");

                                    } else if (totalMinutes == 0) {

                                        lstabsent.add("A");
                                        lstall.add("S");
                                        map.put("" + d, "A");

                                    }


                                }


                            }


                            CommonMethods.hideDialog(spotsDialog);
                            FillDataOnCalendar();


                        }
                    } else {

                        CommonMethods.hideDialog(spotsDialog);
                        Toast.makeText(context, "" + str_error, Toast.LENGTH_SHORT).show();
                    }


                } else {
                    CommonMethods.showErrorMessageWhenStatusNot200(context, response.code());
                }


                CommonMethods.hideDialog(spotsDialog);


            }

            @Override
            public void onFailure(Call<EmployeeAttendance> call, Throwable t) {
                CommonMethods.onFailure(context, TAG, t);

                CommonMethods.hideDialog(spotsDialog);
            }
        });

    }
    //onCreate completed


    public class CustomGrid extends BaseAdapter {
        private Context mContext;
        private final String[] web;

        public CustomGrid(Context c, String[] web) {
            mContext = c;

            this.web = web;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return web.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View grid;
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                grid = new View(mContext);
                grid = inflater.inflate(R.layout.grid_item, null);
                TextView textView = (TextView) grid
                        .findViewById(R.id.textView1);

                textView.setText(web[position]);
            } else {
                grid = (View) convertView;
                TextView textView = (TextView) grid
                        .findViewById(R.id.textView1);

                textView.setText(web[position]);
            }
            return grid;
        }
    }

    private void FillDataOnCalendar() {
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        CustomGrid adapterq = new CustomGrid(getApplicationContext(), days);
        gridday.setAdapter(adapterq);

        // Initialised
        // gridday.setAdapter(new
        // ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_2,days));
        // gridday.setAdapter(new MyAdapter(getActivity(), days));
        adapter = new GridCellAdapter(getApplicationContext(),
                R.id.calendar_day_gridcell, month, year);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
        calendarView.setExpanded(true);
    }

    private void setGridCellAdapterToDate(int month, int year)
    {



        adapter = new GridCellAdapter(getApplicationContext(),
                R.id.calendar_day_gridcell, month, year);


        _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(DateFormat.format(dateTemplate,
                _calendar.getTime()));
        adapter.notifyDataSetChanged();


        calendarView.setAdapter(adapter);
        calendarView.setExpanded(true);
        getDataFromServer();
    }

    // Inner Class
    public class GridCellAdapter extends BaseAdapter implements View.OnClickListener {
        private static final String tag = "GridCellAdapter";
        private final Context _context;

        private final List<String> list;
        private static final int DAY_OFFSET = 1;
        private final String[] weekdays = new String[]{"Sun", "Mon", "Tue",
                "Wed", "Thu", "Fri", "Sat"};
        private final String[] months = {"January", "February", "March",
                "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30,
                31, 30, 31};
        private int daysInMonth;
        private int currentDayOfMonth;
        private int currentWeekDay;
        private Button gridcell;
        private TextView num_events_per_day;
        private HashMap<String, String> eventsPerMonthMap = new HashMap<String, String>();
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
                "dd-MMM-yyyy");
        private String d;
        private char dc;
        private String m;
        private String thistheday;

        // Days in Current Month

        public GridCellAdapter(Context context, int textViewResourceId,
                               int month, int year) {
            super();
            this._context = getApplicationContext();
            this.list = new ArrayList<String>();

            Calendar calendar = Calendar.getInstance();

            setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));

            setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));

            // Print Month
            printMonth(month, year);
            // Find Number of Events

            eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
            Log.d("Present Days : ", "" + lstpresent.size());
            Log.d("Absent Days : ", "" + lstabsent.size());
            Log.d("All Days : ", "" + lstall.size());
            Log.d("Half days : ", "" + lsthalf.size());
            Log.d("Test Days : ", "" + lsttest.size());
            Log.d("Target Days : ", "" + lstleave.size() + "Sum : + GetTargetDaysSum(lstleave)");


            try {
                double per = (Double.parseDouble("" + lstpresent.size()) * 100)
                        / Double.parseDouble("" + lstall.size());
                Log.d("Percentage :", "" + per);
                String result = String.format("%.2f", per);

                Log.d("Percentage Result :", "" + result);

                presentdays.setText("" + lstpresent.size());
                absentdays.setText("" + lstabsent.size());
                totaldays.setText("" + lstall.size());
                holidays.setText("" + lsthalf.size());
                testdays.setText("" + lsttest.size());
                targetdays.setText("" + lstleave.size());
                //targetdays.setText("" + GetTargetDaysSum(lsttarget));


                if (!result.equals("NaN")) {
                    percentage.setText("" + result + " %");
                } else {
                    percentage.setText("0");
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("Error ", e.getMessage());
                e.printStackTrace();
            }

            // System.out.print("Present Days : "+lstpresent.size());
            // System.out.print("Absent Days : "+lstabsent.size());
            // System.out.print("All Days : "+lstall.size());

        }

        private String getMonthAsString(int i) {
            return months[i];
        }

        private String getWeekDayAsString(int i) {
            return weekdays[i];
        }

        private int getNumberOfDaysOfMonth(int i) {
            return daysOfMonth[i];
        }

        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        private void printMonth(int mm, int yy) {

            Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
            int trailingSpaces = 0;
            int daysInPrevMonth = 0;
            int prevMonth = 0;
            int prevYear = 0;
            int nextMonth = 0;
            int nextYear = 0;

            int currentMonth = mm - 1;
            String currentMonthName = getMonthAsString(currentMonth);
            daysInMonth = getNumberOfDaysOfMonth(currentMonth);

            Log.d(tag, "Current Month: " + " " + currentMonthName + " having "
                    + daysInMonth + " days.");

            GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
            Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

            if (currentMonth == 11) {
                prevMonth = currentMonth - 1;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 0;
                prevYear = yy;
                nextYear = yy + 1;
                Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:"
                        + prevMonth + " NextMonth: " + nextMonth
                        + " NextYear: " + nextYear);
            } else if (currentMonth == 0) {
                prevMonth = 11;
                prevYear = yy - 1;
                nextYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 1;
                Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:"
                        + prevMonth + " NextMonth: " + nextMonth
                        + " NextYear: " + nextYear);
            } else {
                prevMonth = currentMonth - 1;
                nextMonth = currentMonth + 1;
                nextYear = yy;
                prevYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);

            }

            int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
            trailingSpaces = currentWeekDay;

            if (cal.isLeapYear(cal.get(Calendar.YEAR)))
                if (mm == 2)
                    ++daysInMonth;
                else if (mm == 3)
                    ++daysInPrevMonth;

            // Trailing Month days
            for (int i = 0; i < trailingSpaces; i++) {
                Log.d(tag,
                        "PREV MONTH:= "
                                + prevMonth
                                + " => "
                                + getMonthAsString(prevMonth)
                                + " "
                                + String.valueOf((daysInPrevMonth
                                - trailingSpaces + DAY_OFFSET)
                                + i));
                list.add(String
                        .valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET)
                                + i)
                        + "-GREY"
                        + "-"
                        + getMonthAsString(prevMonth)
                        + "-"
                        + prevYear);
            }

            // Current Month Days
            for (int i = 1; i <= daysInMonth; i++) {
                Log.d(currentMonthName, String.valueOf(i) + " "
                        + getMonthAsString(currentMonth) + " " + yy);
                if (i == getCurrentDayOfMonth()) {
                    list.add(String.valueOf(i) + "-BLUE" + "-"
                            + getMonthAsString(currentMonth) + "-" + yy);
                } else {
                    list.add(String.valueOf(i) + "-WHITE" + "-"
                            + getMonthAsString(currentMonth) + "-" + yy);
                }
            }

            // Leading Month days
            for (int i = 0; i < list.size() % 7; i++) {
                Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
                list.add(String.valueOf(i + 1) + "-GREY" + "-"
                        + getMonthAsString(nextMonth) + "-" + nextYear);
            }
        }

        /**
         * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
         * ALL entries from a SQLite database for that month. Iterate over the
         * List of All entries, and get the dateCreated, which is converted into
         * day.
         *
         * @param year
         * @param month
         * @return
         */
        private HashMap<String, String> findNumberOfEventsPerMonth(int year,
                                                                   int month) {
          //  final HashMap<String, String> map = new HashMap<String, String>();
            /*dbhandler db = new dbhandler(getApplicationContext());
			SQLiteDatabase sd = db.getReadableDatabase();*/
            if (month < 10) {
                m = "0" + month;
            } else
                m = "" + month;

          /*  Cursor c = sd.rawQuery(
                    "select * from studattendence where date like '%" + year+ "-" + m + "%'", null);


*/


      /*      String query = "select * from " + dbhandler.TABLE_GOAL_TRACK + " where " + dbhandler.GOAL_TRACK_SUBTASK_ID + "=" + userDetails.get(SessionManager.KEY_SUBTASKID) + " and  " + dbhandler.GOAL_TRACK_DATE + " like '___" + m + "-" + year + "%'";
            Log.d("Query  :  ", query);
            final Cursor c = sd.rawQuery(query, null);*/


      /*      lstabsent.clear();
            lstpresent.clear();
            lsthalf.clear();
            lsttest.clear();
            lstall.clear();
            lsttarget.clear();*/


           /* while (c.moveToNext())
            {
                String selected_date = dbhandler.convertToJsonDateFormat(c.getString(c.getColumnIndex(dbhandler.GOAL_TRACK_DATE)));
                Log.d("Goal Track Date : ", selected_date);

                String days[] = selected_date.split("-");
                int day = Integer.parseInt(days[2]);
                if (day < 10) {
                    d = "0" + day;
                } else {
                    d = "" + day;
                }
                if (c.getString(c.getColumnIndex(dbhandler.GOAL_TRACK_STATUS)).equals("YES")) {
                    lstpresent.add("Y");
                    lstall.add("Y");
                } else if (c.getString(c.getColumnIndex(dbhandler.GOAL_TRACK_STATUS)).equals("NO")) {
                    lstabsent.add("N");
                    lstall.add("N");

                } else if (c.getString(c.getColumnIndex(dbhandler.GOAL_TRACK_STATUS)).equals("SKIP")) {

                    lstskip.add("S");
                    lstall.add("S");

                } else {
                    lsttarget.add(c.getInt(c.getColumnIndex(dbhandler.GOAL_TRACK_STATUS)));
                    lstall.add("" + c.getInt(c.getColumnIndex(dbhandler.GOAL_TRACK_STATUS)));

                }
                map.put("" + d, c.getString(c.getColumnIndex(dbhandler.GOAL_TRACK_STATUS)));
            }
*/

   /*         ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Log.d(TAG, "URL GetEmployeMothlyAttendanceStatusDetails  : " + CommonMethods.WEBSITE + "GetEmployeMothlyAttendanceStatusDetails?type=MonthlyAttendnaceStatus&companyid=" + userDetails.get(SessionManager.KEY_COMPANY_ID) + "&empid=" + userDetails.get(SessionManager.KEY_SELECETED_EMPLOYEE_ID) + "&deptid=" + userDetails.get(SessionManager.KEY_SELECETED_DEPT_ID) + "&branchid=" + userDetails.get(SessionManager.KEY_SELECTED_BRANCH_ID) + "");
            apiInterface.getEmployeMothlyAttendanceStatusDetails("MonthlyAttendnaceStatus", userDetails.get(SessionManager.KEY_COMPANY_ID), userDetails.get(SessionManager.KEY_SELECETED_EMPLOYEE_ID), userDetails.get(SessionManager.KEY_SELECETED_DEPT_ID), userDetails.get(SessionManager.KEY_SELECTED_BRANCH_ID)).enqueue(new Callback<EmployeeAttendance>() {
                @Override
                public void onResponse(Call<EmployeeAttendance> call, Response<EmployeeAttendance> response) {


                    Log.d(TAG, "MonthlyAttendnaceStatus Response Code : " + response.code());

                    if (response.code() == 200) {

                        lstabsent.clear();
                        lstpresent.clear();
                        lsthalf.clear();
                        lsttest.clear();
                        lstall.clear();
                        lstleave.clear();


                        String str_error = response.body().getMESSAGE();
                        String str_error_original = response.body().getORIGINALERROR();
                        boolean error_status = response.body().getERRORSTATUS();
                        boolean record_status = response.body().getRECORDS();

                        if (error_status == false) {
                            if (record_status == true) {

                                List<EmployeeAttendance.Datum> arr = response.body().getData();

                                int currenttotalMinutes = 0;


                                for (int i = 0; i < arr.size(); i++) {

                                    String workingHours[] = arr.get(i).getWorkingHours().split(":");
                                    Log.d(TAG, "Total Hours : " + workingHours[0] + " Total Minutes : " + workingHours[1]);
                                    int totalMinutes = (Integer.parseInt(workingHours[0]) * 60) + Integer.parseInt(workingHours[1]);
                                    Log.d(TAG, "Total Minutes : " + totalMinutes);


                                    if (arr.get(i).getDay1().equals("L")) {


                                        lstleave.add("L");
                                        lstall.add("Y");
                                        map.put("" + d, "L");

                                    } else if (TextUtils.isEmpty(arr.get(i).getDay1().toLowerCase())) {
                                        lstabsent.add("A");
                                        lstall.add("S");
                                        map.put("" + d, "A");


                                    } else {


                                        String selected_date = arr.get(i).getYear().toString() + "-" + arr.get(i).getMonth().toString() + "-" + 1;


                                        Log.d("Goal Track Date : ", selected_date);

                                        String days[] = selected_date.split("-");
                                        int day = Integer.parseInt(days[2]);
                                        if (day < 10) {
                                            d = "0" + day;
                                        } else {
                                            d = "" + day;
                                        }


                                        String currentWorkingHours[] = arr.get(i).getDay1().split(":");
                                        Log.d(TAG, selected_date + " Total Hours : " + currentWorkingHours[0] + " Total Minutes : " + currentWorkingHours[1]);
                                        currenttotalMinutes = (Integer.parseInt(currentWorkingHours[0]) * 60) + Integer.parseInt(currentWorkingHours[1]);
                                        Log.d(TAG, selected_date + " Total Minutes : " + currenttotalMinutes);


                                        if (totalMinutes >= currenttotalMinutes) {
                                            lstpresent.add("P");
                                            lstall.add("Y");
                                            map.put("" + d, "P");
                                        } else if ((totalMinutes / 2) >= currenttotalMinutes) {


                                            lsthalf.add("H");
                                            lstall.add("S");
                                            map.put("" + d, "H");

                                        } else if (totalMinutes == 0) {

                                            lstabsent.add("A");
                                            lstall.add("S");
                                            map.put("" + d, "A");

                                        }


                                    }


                                }


                                CommonMethods.hideDialog(spotsDialog);


                            }
                        } else {

                            CommonMethods.hideDialog(spotsDialog);
                            Toast.makeText(context, "" + str_error, Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        CommonMethods.showErrorMessageWhenStatusNot200(context, response.code());
                    }


                    CommonMethods.hideDialog(spotsDialog);


                }

                @Override
                public void onFailure(Call<EmployeeAttendance> call, Throwable t) {
                    CommonMethods.onFailure(context, TAG, t);

                    CommonMethods.hideDialog(spotsDialog);
                }
            });*/


            return map;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("NewApi")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) _context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.screen_gridcell, parent, false);
            }
            // Get a reference to the Day gridcell

            gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
            gridcell.setOnClickListener(this);
            num_events_per_day = (TextView) row
                    .findViewById(R.id.num_events_per_day);
            // num_events_per_day.setText(" A ");
            // ACCOUNT FOR SPACING

            Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
            String[] day_color = list.get(position).split("-");
            String theday = day_color[0];
            int dayofdate = Integer.parseInt(theday);
            if (dayofdate <= 9) {
                thistheday = "0" + theday;
            } else
                thistheday = theday;
            String themonth = day_color[2];
            String theyear = day_color[3];
            if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null)) {
                if (eventsPerMonthMap.containsKey(thistheday)) {
                    num_events_per_day = (TextView) row
                            .findViewById(R.id.num_events_per_day);
                    Typeface boldTypeface = Typeface
                            .defaultFromStyle(Typeface.BOLD);
                    String numEvents = eventsPerMonthMap.get(thistheday);

                    final int sdk = android.os.Build.VERSION.SDK_INT;


                    if (numEvents.equals("YES")) {
                        num_events_per_day.setTextColor(Color.rgb(37, 105, 35));
                        //num_events_per_day.setText(numEvents.toString());
                        num_events_per_day.setTextSize(18);
                        num_events_per_day.setTypeface(boldTypeface);
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            num_events_per_day.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_task_yes));
                            // num_events_per_day.setBackgroundDrawable( getResources().getDrawable(R.drawable.icon_oval) );
                        } else {
                            num_events_per_day.setBackground(getResources().getDrawable(R.drawable.icon_task_yes));
                            // num_events_per_day.setBackground( getResources().getDrawable(R.drawable.icon_oval));
                        }


                    } else if (numEvents.equals("NO")) {
                        num_events_per_day.setTextColor(Color.rgb(20, 158, 228));
                        //num_events_per_day.setText(numEvents.toString());
                        num_events_per_day.setTextSize(18);
                        num_events_per_day.setTypeface(boldTypeface);

                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            num_events_per_day.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_task_no));
                        } else {
                            num_events_per_day.setBackground(getResources().getDrawable(R.drawable.icon_task_no));
                        }


                    } else if (numEvents.equals("SKIP")) {
                        num_events_per_day.setTextColor(Color.rgb(204, 102, 0));
                        // num_events_per_day.setText(numEvents.toString());
                        num_events_per_day.setTextSize(18);
                        num_events_per_day.setTypeface(boldTypeface);

                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            num_events_per_day.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_task_skip));
                        } else {
                            num_events_per_day.setBackground(getResources().getDrawable(R.drawable.icon_task_skip));
                        }


                    } else {
                        num_events_per_day.setTextColor(getResources().getColor(R.color.colorAccent));
                        num_events_per_day.setText(numEvents.toString());

                        num_events_per_day.setTextSize(18);
                        num_events_per_day.setTypeface(boldTypeface);


                        //num_events_per_day.setText(numEvents.toString());
                        //num_events_per_day.setTextSize(18);
//                        num_events_per_day.setTypeface(boldTypeface);


                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            // num_events_per_day.setBackgroundDrawable( getResources().getDrawable(R.drawable.icon_oval) );
                        } else {
                            //num_events_per_day.setBackground( getResources().getDrawable(R.drawable.icon_oval));
                        }


                    }

                    if (numEvents.equals("T")) {
                        num_events_per_day.setTextColor(Color.rgb(58, 48, 148));
                        num_events_per_day.setText(numEvents.toString());
                        num_events_per_day.setTextSize(18);
                        num_events_per_day.setTypeface(boldTypeface);
                    }
                    // num_events_per_day.setText("P");
                }
            }

            // Set the Day GridCell

            gridcell.setText(theday);
            gridcell.setTag(theday + "-" + themonth + "-" + theyear);

            if (day_color[1].equals("GREY")) {
                gridcell.setTextColor(getResources()
                        .getColor(R.color.lightgray));
                num_events_per_day.setText("");
            }
            if (day_color[1].equals("WHITE")) {
                gridcell.setTextColor(getResources().getColor(
                        R.color.lightgray02));
            }
            if (day_color[1].equals("BLUE")) {

                Calendar calendar = Calendar.getInstance();
                int mmm = calendar.get(Calendar.MONTH) + 1;


                Log.d("mmm : ", "" + mmm);
                Log.d("month : ", "" + month);

                if (mmm == month) {
                    gridcell.setTextColor(getResources().getColor(R.color.orrange));
                } else {
                    gridcell.setTextColor(getResources().getColor(R.color.lightgray02));
                }
                //gridcell.setTextColor(getResources().getColor(R.color.orrange));
            }

            return row;
        }

        @Override
        public void onClick(View view) {
            String date_month_year = (String) view.getTag();
            // selectedDayMonthYearButton.setText("Selected: " +
            // date_month_year);
            Log.e("Selected date", date_month_year);
            try {
                Date parsedDate = dateFormatter.parse(date_month_year);
                Log.d(tag, "Parsed Date: " + parsedDate.toString());

                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "dd-MM-yyyy", Locale.getDefault());

                Date date = new Date();
                currenSelectedtdate = dateFormat.format(parsedDate);
                Date ddd = dateFormat.parse(currenSelectedtdate);


//                Toast.makeText(getApplicationContext() , "Date : "+parsedDate.toString() , Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext() , "Date : "+dbhandler.convertToJsonDateFormat(parsedDate.toString()) , Toast.LENGTH_SHORT).show();
                Log.d("Date : ", parsedDate.toString());

               /* Intent intent = new Intent(context, SingleGoalActivity.class);

                intent.putExtra("CALENDARDATE", currenSelectedtdate);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);*/

                /**
                 * Display current date multiple entries
                 */
                //  SetCurrentGoalTrackSettingsUsingCustomDialog();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public int getCurrentDayOfMonth() {
            return currentDayOfMonth;
        }

        private void setCurrentDayOfMonth(int currentDayOfMonth) {
            this.currentDayOfMonth = currentDayOfMonth;
        }

        public void setCurrentWeekDay(int currentWeekDay) {
            this.currentWeekDay = currentWeekDay;
        }

        public int getCurrentWeekDay() {
            return currentWeekDay;
        }
    }

    public int GetTargetDaysSum(ArrayList<Integer> tar) {
        Log.d("Array : ", "" + tar.toString());
        int i;
        int sum = 0;
        for (i = 0; i < tar.size(); i++)
            sum += tar.get(i);
        Log.d("Sum : ", "" + sum);
        return sum;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home) {


            Intent intent = new Intent(context, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(context, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
