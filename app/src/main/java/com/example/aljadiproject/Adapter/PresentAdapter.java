package com.example.aljadiproject.Adapter;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aljadiproject.Models.PendingLeavesApiData.PendingLeavesActualData;
import com.example.aljadiproject.R;
import com.example.aljadiproject.Models.PresentEmployeeApiData.PresentEmployeesData;

import java.util.ArrayList;

public class PresentAdapter extends RecyclerView.Adapter<PresentAdapter.ViewHolder> {
    ArrayList<PresentEmployeesData> data;
    Context context;

    public PresentAdapter(ArrayList<PresentEmployeesData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.present_list_items, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // final PresentEmployeesModel.Datum temp = data.get(position);
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
            rowViewHolder.name.setText("Employee Name");
            rowViewHolder.name.setTextColor(Color.BLACK);
            rowViewHolder.company.setText("Company");
            rowViewHolder.company.setTextColor(Color.BLACK);
            rowViewHolder.startTime.setText("Start time");
            rowViewHolder.startTime.setTextColor(Color.BLACK);
            rowViewHolder.endTime.setText("End time");
            rowViewHolder.endTime.setTextColor(Color.BLACK);
        } else {
           PresentEmployeesData modal = data.get(rowPos - 1);
//           PendingLeavesActualData.user modal2 = data2.get(rowPos - 1);
//            PendingUser modal2 = data2.get(rowPos -1);

            rowViewHolder.id.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.name.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.company.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.startTime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.endTime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.id.setText(modal.getUser_id().toString() + "");
            rowViewHolder.name.setText(modal.getEmployee_name()+ "");
            rowViewHolder.company.setText(modal.getCompany_name() + "");
            rowViewHolder.startTime.setText(modal.getStart_time() + "");
            rowViewHolder.endTime.setText(modal.getEnd_time() + "");
        }

    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, company, startTime, endTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.p_id);
            name = itemView.findViewById(R.id.p_name);
            company = itemView.findViewById(R.id.p_company);
            startTime = itemView.findViewById(R.id.p_start);
            endTime = itemView.findViewById(R.id.p_end);

        }
    }

}
