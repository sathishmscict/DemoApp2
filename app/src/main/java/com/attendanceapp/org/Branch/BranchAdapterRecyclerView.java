package com.attendanceapp.org.Branch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.attendanceapp.org.Login.LoginInteractor;
import com.attendanceapp.org.R;
import com.attendanceapp.org.model.BranchData;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by SATHISH on 12-Sep-17.
 */

public class BranchAdapterRecyclerView extends RecyclerView.Adapter<BranchAdapterRecyclerView.MyViewHolder> {


    private final Context context;
    private final List<BranchData.Datum> list_branchData;
    private final BranchPresenterImpl presenter;
    private String TAG = BranchAdapterRecyclerView.class.getSimpleName();

    public BranchAdapterRecyclerView(Context context, List<BranchData.Datum> branchData,BranchPresenterImpl presenter) {
        this.context = context;
        this.list_branchData = branchData;
        this.presenter  = presenter;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvBranchName;
        private final ImageView ivEdit;
        private final ImageView ivDelete;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvBranchName = (TextView) itemView.findViewById(R.id.tvBranch);
            ivEdit = (ImageView) itemView.findViewById(R.id.ivEdit);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);


        }
    }

    @Override
    public BranchAdapterRecyclerView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_single_branch, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BranchAdapterRecyclerView.MyViewHolder holder, int position) {

        final BranchData.Datum bd = list_branchData.get(position);

        holder.tvBranchName.setText(bd.getBRANCHNAME());


        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.editBranchDetails(bd);



            }
        });


        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                presenter.deleteBranchDetails(bd);




            }
        });




    }

    @Override
    public int getItemCount() {
        return list_branchData.size();
    }
}
