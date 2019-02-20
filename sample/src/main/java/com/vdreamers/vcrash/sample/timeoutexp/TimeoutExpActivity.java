package com.vdreamers.vcrash.sample.timeoutexp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vdreamers.vcrash.sample.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * TimeoutExpActivity
 * <p>
 * date 2019/02/18 10:56:12
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class TimeoutExpActivity extends AppCompatActivity {

    private TextView mStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeout_exp);
        resetWatchDogStatus();
        findViewById(R.id.btn_kill_watchdog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WatchDogKiller.stopWatchDog();
                // 触发生效
                Runtime.getRuntime().gc();
                System.runFinalization();
                resetWatchDogStatus();
            }
        });
        findViewById(R.id.btn_fire_timeout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 因为 stopWatchDog需要下一次循环才会生效，这里先post一下
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                fireTimeout();
                                Runtime.getRuntime().gc();
                                System.runFinalization();
                            }
                        }).start();
                    }
                }, 100);

                Toast.makeText(TimeoutExpActivity.this, "请等待。。。。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resetWatchDogStatus() {
        boolean alive = WatchDogKiller.checkWatchDogAlive();
        mStatusTextView = findViewById(R.id.tv_watch_dog_status);
        mStatusTextView.setText(alive ? "ON" : "OFF");
    }

    private void fireTimeout() {
        TimeoutModel timeoutModel = new TimeoutModel();
    }
}
