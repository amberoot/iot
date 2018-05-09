package com.cuanbo.cb_iot.Model;

import java.util.List;

/**
 * Created by xww on 2018/5/8.
 */

public class Meeting {
    private String meetingName;
    private String meetingDate;
    private String meetingTime;
    private String meetingRoom;
    private String attendMeetingNumber;


    public Meeting(String meetingName, String meetingDate, String meetingTime, String meetingRoom, String attendMeetingNumber) {
        this.meetingName = meetingName;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.meetingRoom = meetingRoom;
        this.attendMeetingNumber = attendMeetingNumber;

    }


    public String getAttendMeetingNumber() {

        return attendMeetingNumber;
    }

    public String getMeetingRoom() {

        return meetingRoom;
    }

    public String getMeetingDate() {

        return meetingDate;
    }

    public String getMeetingTime() {

        return meetingTime;
    }

    public String getMeetingName() {

        return meetingName;
    }
}
