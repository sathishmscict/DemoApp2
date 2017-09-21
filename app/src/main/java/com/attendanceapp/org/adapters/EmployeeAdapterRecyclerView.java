package com.attendanceapp.org.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.attendanceapp.org.R;
import com.attendanceapp.org.model.EmployeeData;

import java.util.List;

/**
 * Created by SATHISH on 14-Sep-17.
 */

public class EmployeeAdapterRecyclerView extends RecyclerView.Adapter<EmployeeAdapterRecyclerView.MyViewHolder> {

    private final Context context;
    private final List<EmployeeData.Datum> list_EmployeeData;
    private final LayoutInflater inflater;
    private final String type;

    public EmployeeAdapterRecyclerView(Context context, List<EmployeeData.Datum> list_EmployeeData,String type) {
        this.context = context;
        this.list_EmployeeData = list_EmployeeData;
        this.inflater = LayoutInflater.from(context);
        this.type =type;



    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final ImageView ivUser;
        private final TextView tvemployeeName;
        private final TextView tvInTime;
        private final TextView tvOutTime;
        private final TextView tvThumbCounter;
        private final TextView tvStatus;

        public MyViewHolder(View itemView) {
            super(itemView);


            ivUser = (ImageView) itemView.findViewById(R.id.ivUser);
            tvemployeeName = (TextView) itemView.findViewById(R.id.tvEmployeeName);
            tvInTime = (TextView) itemView.findViewById(R.id.tvInTime);
            tvOutTime = (TextView) itemView.findViewById(R.id.tvOutTime);
            tvThumbCounter = (TextView) itemView.findViewById(R.id.tvThumbCounter);
            tvStatus = (TextView)itemView.findViewById(R.id.tvStatus);



        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.row_single_employee, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmployeeAdapterRecyclerView.MyViewHolder holder, int position) {


        EmployeeData.Datum ed = list_EmployeeData.get(position);


        holder.tvemployeeName.setText(ed.getEmpName());
        if(!ed.getInTime().equals(""))
        {
            holder.tvInTime.setText(ed.getInTime());
            holder.tvInTime.setVisibility(View.VISIBLE);

        }
        else
        {

            if(type.equals("all"))
            {
                if(!ed.getLeaveStatus().equals("0"))
                {
                    holder.tvStatus.setText("Leave");

                }
                else
                {
                    holder.tvStatus.setText("Absent");
                }
            }

            holder.tvInTime.setVisibility(View.INVISIBLE);

        }

        if(!ed.getOutTime().equals(""))
        {
            holder.tvOutTime.setText(ed.getOutTime());
            holder.tvOutTime.setVisibility(View.VISIBLE);
        }
        else
        {

            holder.tvOutTime.setVisibility(View.INVISIBLE);
        }


        if(ed.getTotalCount() > 0)
        {
            holder.tvThumbCounter.setText(String.valueOf(ed.getTotalCount()));
            holder.tvThumbCounter.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.tvThumbCounter.setVisibility(View.INVISIBLE);
        }




    }

    @Override
    public int getItemCount() {
        return list_EmployeeData.size();
    }
}
