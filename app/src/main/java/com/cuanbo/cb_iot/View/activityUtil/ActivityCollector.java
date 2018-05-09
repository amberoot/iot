package com.cuanbo.cb_iot.View.activityUtil;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xww on 18/5/4.
 */

public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {

        activities.remove(activity);
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }

    public static void finishAll() {
        for(Activity activity:activities){
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}
