package com.example.aljadiproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData;
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
        View view = layoutInflater.inflate(R.layout.present_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      //  final AbsentEmployeesModel.EmployeeAttendance temp = data.get(position);
        holder.id.setText(data.get(position).getId().toString());
        holder.name.setText(data.get(position).getName());
        holder.company.setText(data.get(position).getDesignation());
        holder.startTime.setText(data.get(position).getStart_time());
        holder.endTime.setText(data.get(position).getEnd_time());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, company, startTime, endTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            company = itemView.findViewById(R.id.company);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);

        }
    }

}
