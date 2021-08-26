package com.example.aljadiproject.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aljadiproject.Models.PendingLeavesApiData.PendingLeavesActualData;
import com.example.aljadiproject.PendingActivity;
import com.example.aljadiproject.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder> {
    ArrayList<PendingLeavesActualData> data;
    boolean tmp = false;

    Context context;

    public PendingAdapter(ArrayList<PendingLeavesActualData> data, Context context) {
        this.data = data;

        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pending_list_items, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //  final PresentModel temp = data.get(position);
        ViewHolder rowViewHolder;
        rowViewHolder = (ViewHolder) holder;
        int rowPos = rowViewHolder.getAdapterPosition();
        if (rowPos == 0) {
            rowViewHolder.id.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.name.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.company.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.startTime.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.endTime.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.linearLayout.setBackgroundResource(R.drawable.table_header_cell_bg);

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
            rowViewHolder.accept.setText("Accept");
            rowViewHolder.accept.setTextColor(Color.BLACK);
            rowViewHolder.reject.setText("Reject");
            rowViewHolder.reject.setTextColor(Color.BLACK);
            rowViewHolder.slash.setText("/");
            rowViewHolder.slash.setTextColor(Color.BLACK);

        } else {
            PendingLeavesActualData modal = data.get(rowPos - 1);
//           PendingLeavesActualData.user modal2 = data2.get(rowPos - 1);
//            PendingUser modal2 = data2.get(rowPos -1);

            rowViewHolder.id.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.name.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.company.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.startTime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.endTime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.linearLayout.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.id.setText(modal.getPendingUser().getEmployee_id() + "");
            rowViewHolder.name.setText(modal.getPendingUser().getEmployee_name() + "");
            rowViewHolder.company.setText(R.string.view_btn);
            rowViewHolder.company.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //rowViewHolder.company.setTextColor(Color.BLUE);
            //rowViewHolder.company.setTextColor(R.drawable.view_btn_color_state);
            rowViewHolder.company.setPaintFlags(rowViewHolder.company.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            rowViewHolder.startTime.setText(modal.getLeave_duration_type() + "");
            rowViewHolder.endTime.setText(modal.getStart_date() + "");
            rowViewHolder.accept.setText("Accept");
            rowViewHolder.accept.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rowViewHolder.reject.setText("Reject");
            rowViewHolder.reject.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rowViewHolder.slash.setText("/");
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

            rowViewHolder.accept.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    // PendingActivity.acceptOnClick();
                    new AlertDialog.Builder(v.getRootView().getContext())
                            .setTitle("Accept Request")
                            .setMessage("Are you sure you want to accept request?")
//                            .setView(R.layout.popup_window)

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation

                                    PendingActivity pendingActivity = new PendingActivity();
                                    pendingActivity.sendLeaveID(modal.getId(), context);
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
            rowViewHolder.reject.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    // PendingActivity.acceptOnClick();
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                    View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.rejection_popup_window, null);
                    EditText editText;
                    AppCompatButton button;
                    editText = dialogView.findViewById(R.id.et_rejection);
                    button = dialogView.findViewById(R.id.reject_btn);
                    builder.setTitle("Reason for Rejection");
                    builder.setView(dialogView);
                    builder.setCancelable(true);
                    final AlertDialog dialog = builder.create();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String reason_for_rejection = editText.getText().toString();
                            if (reason_for_rejection.equals("")) {
                                editText.setError("Field is required");
                                editText.requestFocus();
                            } else {
                                PendingActivity pendingActivity = new PendingActivity();
                                pendingActivity.sendLeaveIDForRejection(modal.getId(), reason_for_rejection, context);
                                dialog.cancel();
                            }
                        }
                    });


                    dialog.show();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, company, startTime, endTime, accept, reject, slash;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.ids);
            name = itemView.findViewById(R.id.nam);
            company = itemView.findViewById(R.id.com);
            //view = itemView.findViewById(R.id.view_btn);
            accept = itemView.findViewById(R.id.accept);
            reject = itemView.findViewById(R.id.reject);
            startTime = itemView.findViewById(R.id.start);
            endTime = itemView.findViewById(R.id.end);
            linearLayout = itemView.findViewById(R.id.layout_accept_reject);
            slash = itemView.findViewById(R.id.slash);

        }
    }

}
