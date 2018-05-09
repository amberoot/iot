package com.cuanbo.cb_iot.View.Presentation;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cuanbo.cb_iot.Model.Meeting;
import com.cuanbo.cb_iot.R;
import com.cuanbo.cb_iot.View.activityUtil.MyApplication;

import java.util.List;

/**
 * Created by xww on 18/5/4.
 */

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder>{

    private Context context;
    private List<Meeting> meetingList;

    public MeetingAdapter(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_meeting,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Meeting meeting = meetingList.get(position);
        holder.textMeetingName.setText(meeting.getMeetingName());

    }

    @Override
    public int getItemCount() {
        return meetingList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textMeetingName;
        TextView textMeetingTime;
        TextView textMeetingRoom;
        TextView textStartTime;
        TextView textAttendNumber;
        Button btnChangeAppointment;
        Button btnCancelAppointment;
        Button btnAddPerson;
        Button btnAttendList;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            textMeetingName = itemView.findViewById(R.id.text_meetingName);
            textMeetingTime = itemView.findViewById(R.id.text_meetingTime);
            textMeetingRoom = itemView.findViewById(R.id.text_meetingRoom);
            textStartTime = itemView.findViewById(R.id.text_meetingStartTime);
            textAttendNumber = itemView.findViewById(R.id.text_number);
            btnChangeAppointment = itemView.findViewById(R.id.btn_changeAppointment);
            btnCancelAppointment = itemView.findViewById(R.id.btn_cancelMeeting);
            btnAddPerson = itemView.findViewById(R.id.btnAdd);
            btnAttendList = itemView.findViewById(R.id.btn_attendList);

            //与会人员名单
            btnAttendList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "与会人员名单", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });

            //修改预约
            btnChangeAppointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "修改预约", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            //取消预约
            btnCancelAppointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "取消预约", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            //添加人员
            btnAddPerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "添加人员", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

        }

    }


}
