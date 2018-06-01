package com.example.yhadmin.threaddemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity
        extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                runIntentService();
                runThreadPool();
            }
        });


    }

    private void runThreadPool() {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Log.e("runThreadPool", ""+SystemClock.currentThreadTimeMillis());
            }
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.execute(command);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(command);

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        //2000ms 后执行 command
        scheduledThreadPool.schedule(command, 2000, TimeUnit.MILLISECONDS);
        //延迟10ms 后执行，每隔1000ms 执行一次 command
        scheduledThreadPool.scheduleAtFixedRate(command, 10, 100, TimeUnit.MILLISECONDS);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(command);
    }

    private void runIntentService() {
        Intent intent = new Intent(this, LocalIntentService.class);
        intent.putExtra("task_action", "com.example.yhadmin.threaddemo.task1");
        startService(intent);

        intent.putExtra("task_action", "com.example.yhadmin.threaddemo.task2");
        startService(intent);

        intent.putExtra("task_action", "com.example.yhadmin.threaddemo.task3");
        startService(intent);

        intent.putExtra("task_action", "com.example.yhadmin.threaddemo.task4");
        startService(intent);
    }
}
