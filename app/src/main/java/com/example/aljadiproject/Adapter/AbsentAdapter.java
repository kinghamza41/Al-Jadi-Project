package com.example.aljadiproject.Adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData;
import com.example.aljadiproject.Models.PendingLeavesApiData.PendingLeavesActualData;
import com.example.aljadiproject.Models.PresentEmployeeApiData.PresentEmployeesData;
import com.example.aljadiproject.R;

import java.util.ArrayList;

public class AbsentAdapter extends RecyclerView.Adapter<AbsentAdapter.ViewHolder> {
    ArrayList<AbsentEmployeesData> data;
    Context context;

    public AbsentAdapter(ArrayList<AbsentEmployeesData> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.absent_list_items, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //  final AbsentEmployeesModel.EmployeeAttendance temp = data.get(position);

        ViewHolder rowViewHolder;
        rowViewHolder = (ViewHolder) holder;
        int rowPos = rowViewHolder.getAdapterPosition();
        if (rowPos == 0) {
            rowViewHolder.id.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.name.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.company.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.startTime.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.endTime.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.id.setText("ID");
            rowViewHolder.id.setTextColor(Color.BLACK);
            rowViewHolder.name.setText("Name");
            rowViewHolder.name.setTextColor(Color.BLACK);
            rowViewHolder.company.setText("Company");
            rowViewHolder.company.setTextColor(Color.BLACK);
            rowViewHolder.startTime.setText("Start time");
            rowViewHolder.startTime.setTextColor(Color.BLACK);
            rowViewHolder.endTime.setText("End time");
            rowViewHolder.endTime.setTextColor(Color.BLACK);
        } else {
            AbsentEmployeesData modal = data.get(rowPos - 1);
            rowViewHolder.id.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.name.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.company.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.startTime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.endTime.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.id.setText(modal.getId().toString() + "");
//            rowViewHolder.name.setText(modal.getName() + "");
//            rowViewHolder.company.setText(modal.getDesignation() + "");
//            rowViewHolder.startTime.setText(modal.getStart_time() + "");
//            rowViewHolder.endTime.setText(modal.getEnd_time() + "");
            rowViewHolder.id.setText(modal.getId().toString());
            rowViewHolder.name.setText(modal.getName());
            rowViewHolder.company.setText(modal.getDesignation());
            rowViewHolder.startTime.setText(modal.getStart_time());
            rowViewHolder.endTime.setText(modal.getEnd_time());

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, company, startTime, endTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.a_id);
            name = itemView.findViewById(R.id.a_name);
            company = itemView.findViewById(R.id.a_company);
            startTime = itemView.findViewById(R.id.a_start);
            endTime = itemView.findViewById(R.id.a_end);

        }
    }

}
