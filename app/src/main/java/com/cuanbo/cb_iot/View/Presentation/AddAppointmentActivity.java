package com.cuanbo.cb_iot.View.Presentation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;

import com.cuanbo.cb_iot.MainActivity;
import com.cuanbo.cb_iot.R;
import com.cuanbo.cb_iot.Tool.LogUtil;

import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity {

    private MeetingRoomAdapter meetingRoomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //添加系统返回按钮在toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        initRecyclerView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSetDateDialog();
            }
        });
    }



    /**
     * 选择日期框
     */
    public void creatCustomDialog() {
        Dialog dialog = null;

        dialog = new android.app.AlertDialog.Builder(this).create();
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(R.layout.layout_time_picker);
        window.setGravity(Gravity.CENTER);//设置dialog显示的位置居中
        //window.setWindowAnimations(R.style.alpha_anim);//添加动画效果
        //设置对话框背景透明，AlertDialog无效，才有效
        window.setBackgroundDrawableResource(R.color.FullTransparent);

        //设置对话框的宽度和高度

        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = 300;
        p.height = 300;
        dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
        dialog.getWindow().setAttributes(p);     //设置生效

    }
    //获取当前日期
    private void showSetDateDialog() {
        //获取当前时间
        Calendar cal= Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        //
        final DatePickerDialog pickerDialog = new DatePickerDialog(AddAppointmentActivity.this,null,year,month,day);
        pickerDialog.setCancelable(true);
        pickerDialog.setCanceledOnTouchOutside(true);

        pickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatePicker picker = pickerDialog.getDatePicker();
                int year_set = picker.getYear();
                int month_set = picker.getMonth();
                int day_set = picker.getDayOfMonth();
            }
        });

        pickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        pickerDialog.show();
    }

    //处理Toolbar上按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //系统返回按钮ID
            case android.R.id.home:
                finish();
                return true;
            case R.id.item_home:
                //MainActivity设置为singleTask启动方法。当activity为singleTask的时候跳转会清空当前activity任务栈上面所有的activity。
                Intent i = new Intent(AddAppointmentActivity.this,MainActivity.class);
                startActivity(i);
                return true;
            case R.id.item_me:
                //跳转页面
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_meetingRoom);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        meetingRoomAdapter = new MeetingRoomAdapter();
        recyclerView.setAdapter(meetingRoomAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }


}
