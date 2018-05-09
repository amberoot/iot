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

import com.cuanbo.cb_iot.R;

/**
 * Created by xww on 2018/5/8.
 */

public class MeetingRoomAdapter extends RecyclerView.Adapter<MeetingRoomAdapter.ViewHolder>{

    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_meeting_room,parent,false);
        return new MeetingRoomAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 12;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        Button btnMeetingRoom;

        public ViewHolder(View itemView) {
            super(itemView);

            btnMeetingRoom = itemView.findViewById(R.id.btn_meetingRoom);

            //会议室
            btnMeetingRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "会议室", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });



        }

    }
}
