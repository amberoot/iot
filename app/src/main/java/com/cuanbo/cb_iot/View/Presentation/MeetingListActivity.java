package com.cuanbo.cb_iot.View.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cuanbo.cb_iot.MainActivity;
import com.cuanbo.cb_iot.Model.Meeting;
import com.cuanbo.cb_iot.Model.SQL.sql_meeting;
import com.cuanbo.cb_iot.R;
import com.cuanbo.cb_iot.View.activityUtil.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MeetingListActivity extends BaseActivity {

    private MeetingAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Meeting[] meetings = {new Meeting("5月例会","2018.5.8","8:10","只能会议室","10"),new Meeting("6月例会","2018.5.8","18:10","会议室","12")};

    private List<Meeting> meetingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        //获取Toolbar布局
        Toolbar toolbar = findViewById(R.id.toolbar);
        //方法将Toolbar实例传入
        setSupportActionBar(toolbar);
        //添加系统返回按钮在toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "会议已经删除", Snackbar.LENGTH_LONG)
//                        .setAction("撤销", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(MeetingListActivity.this,"撤回",Toast.LENGTH_SHORT).show();
//                            }
//                        }).show();
                Intent intent = new Intent(MeetingListActivity.this,AddAppointmentActivity.class);
                startActivity(intent);

            }
        });

        initMeetings();
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_meeting);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MeetingAdapter(meetingList);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void initMeetings() {
        meetingList.clear();
        meetingList.add(meetings[0]);
        meetingList.add(meetings[1]);
    }

    private void initSwipeRefreshLayout(){
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        //设置下拉刷新进度条的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMeeting();
            }
        });
    }

    private void refreshMeeting() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //网络读取数据
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //从子线程返回主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();
    }

    //给Toolbar绑定菜单按钮XML文件
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
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
                Intent i = new Intent(MeetingListActivity.this,MainActivity.class);
                startActivity(i);
                return true;
            case R.id.item_me:
                new Thread(new sql_meeting()).start();//跳转页面
                return true;

                default:

                    return super.onOptionsItemSelected(item);
        }

    }
}
