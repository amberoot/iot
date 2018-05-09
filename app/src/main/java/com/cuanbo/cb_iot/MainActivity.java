package com.cuanbo.cb_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cuanbo.cb_iot.View.Presentation.MeetingListActivity;
import com.cuanbo.cb_iot.View.activityUtil.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnAppointment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MeetingListActivity.class);
                startActivity(intent);
            }
        });

    }



}
