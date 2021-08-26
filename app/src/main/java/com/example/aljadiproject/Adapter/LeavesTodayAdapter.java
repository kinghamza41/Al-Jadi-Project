package com.example.aljadiproject.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aljadiproject.Models.OnLeavesApiData.OnLeavesActualData;
import com.example.aljadiproject.Models.PendingLeavesApiData.PendingLeavesActualData;
import com.example.aljadiproject.Models.PresentModel;
import com.example.aljadiproject.R;

import java.util.ArrayList;

public class LeavesTodayAdapter extends RecyclerView.Adapter<LeavesTodayAdapter.ViewHolder> {
    ArrayList<OnLeavesActualData> data;
    Context context;

    public LeavesTodayAdapter(ArrayList<OnLeavesActualData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.today_leaves_list_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //final PresentModel temp = data.get(position);
        ViewHolder rowViewHolder;
        rowViewHolder = (ViewHolder) holder;
        int rowPos = rowViewHolder.getAbsoluteAdapterPosition();
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
            rowViewHolder.company.setText("Reason for Leave");
            rowViewHolder.company.setTextColor(Color.BLACK);
            rowViewHolder.startTime.setText("Leave Duration Type");
            rowViewHolder.startTime.setTextColor(Color.BLACK);
            rowViewHolder.endTime.setText("Start Date");
            rowViewHolder.endTime.setTextColor(Color.BLACK);
        } else {
            OnLeavesActualData modal = data.get(rowPos - 1);
//           PendingLeavesActualData.user modal2 = data2.get(rowPos - 1);
//            PendingUser modal2 = data2.get(rowPos -1);

            rowViewHolder.id.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.name.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.company.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.company.setText(R.string.view_btn);
            rowViewHolder.company.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rowViewHolder.company.setPaintFlags(rowViewHolder.company.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            rowViewHolder.startTime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.endTime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.id.setText(modal.getOnLeavesUser().getEmployee_id() + "");
            rowViewHolder.name.setText(modal.getOnLeavesUser().getName() + "");
//            rowViewHolder.company.setText(modal.getReason_for_leave() + "");
            rowViewHolder.startTime.setText(modal.getLeave_duration_type() + "");
            rowViewHolder.endTime.setText(modal.getStart_date() + "");
            rowViewHolder.company.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                    View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.popup_window, null);
                    TextView textView;
                    textView = dialogView.findViewById(R.id.view_data);
                    textView.setText(modal.getReason_for_leave());
                    builder.setTitle("Reason for Leave");
                    builder.setView(dialogView);
                    builder.setCancelable(true);
                    builder.show();

                }

            });
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
            id = itemView.findViewById(R.id.lt_id);
            name = itemView.findViewById(R.id.lt_name);
            company = itemView.findViewById(R.id.lt_company);
            startTime = itemView.findViewById(R.id.lt_start);
            endTime = itemView.findViewById(R.id.lt_end);

        }
    }
}
