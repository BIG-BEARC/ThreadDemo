package com.example.yhadmin.threaddemo;

/*
 *  @项目名：  ThreadDemo 
 *  @包名：    com.example.yhadmin.threaddemo
 *  @文件名:   LocalIntentService
 *  @创建者:   YHAdmin
 *  @创建时间:  2018/6/1 14:02
 *  @描述：    TODO
 */

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

public class LocalIntentService extends IntentService {

    private static final String TAG = "LocalIntentService";

    public LocalIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra("task_action");
        Log.e(TAG, "receive task :" +  action);
        SystemClock.sleep(3000);
        if ("com.example.yhadmin.threaddemo.task1".equals(action)) {
            Log.e(TAG, "handle task: " + action);
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "service destroyed.");
        super.onDestroy();
    }
}
