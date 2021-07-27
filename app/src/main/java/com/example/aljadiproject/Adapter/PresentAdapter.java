package com.example.aljadiproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aljadiproject.Models.PresentModel;
import com.example.aljadiproject.R;

import java.util.ArrayList;

public class PresentAdapter extends RecyclerView.Adapter<PresentAdapter.ViewHolder> {
    ArrayList<PresentModel> data;
    Context context;

    public PresentAdapter(ArrayList<PresentModel> data, Context context) {
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
        final PresentModel temp = data.get(position);
        holder.id.setText(data.get(position).getId());
        holder.name.setText(data.get(position).getName());
        holder.company.setText(data.get(position).getCompany());
        holder.startTime.setText(data.get(position).getStartTime());
        holder.endTime.setText(data.get(position).getEndTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, company, startTime, endTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(com.example.aljadiproject.R.id.id);
            name = itemView.findViewById(com.example.aljadiproject.R.id.name);
            company = itemView.findViewById(com.example.aljadiproject.R.id.company);
            startTime = itemView.findViewById(com.example.aljadiproject.R.id.startTime);
            endTime = itemView.findViewById(com.example.aljadiproject.R.id.endTime);

        }
    }

}
