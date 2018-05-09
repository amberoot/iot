package com.cuanbo.cb_iot.Model;

/**
 * Created by xww on 2018/5/8.
 */

public class MeetingRoom {
    private String meetingRoomName;

    public MeetingRoom (String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;

    }


}
