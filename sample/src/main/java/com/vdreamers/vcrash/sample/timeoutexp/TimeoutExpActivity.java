package com.vdreamers.vcrash.sample.timeoutexp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vdreamers.vcrash.sample.R;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;

/**
 * TimeoutExpActivity
 * Need to change code
 * {@link com.vdreamers.vcrash.core.CrashHandler#uncaughtException(Thread, Throwable)}
 * <p>
 * date 2019/02/18 10:56:12
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class TimeoutExpActivity extends AppCompatActivity {

    private TextView mStatusTextView;

    private MyHandler mHandler;

    private static class MyHandler extends Handler {
        private final WeakReference<Activity> mActivity;

        public MyHandler(Activity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final Activity activity = mActivity.get();
            if (activity != null) {
                // doSomething
            }
        }
    }

    private static class MyGcThread implements Runnable {
        @Override
        public void run() {
            fireTimeout();
            Runtime.getRuntime().gc();
            System.runFinalization();
        }
    }

    private static final Runnable mMyGcRunnable = new Runnable() {
        @Override
        public void run() {
            MyGcThread myGcThread = new MyGcThread();
            new Thread(myGcThread).start();
        }
    };

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
                if (mHandler == null) {
                    mHandler = new MyHandler(TimeoutExpActivity.this);
                }
                // 因为 stopWatchDog需要下一次循环才会生效，这里先post一下
                mHandler.postDelayed(mMyGcRunnable, 100);

                Toast.makeText(TimeoutExpActivity.this, "请等待。。。。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resetWatchDogStatus() {
        boolean alive = WatchDogKiller.checkWatchDogAlive();
        mStatusTextView = findViewById(R.id.tv_watch_dog_status);
        mStatusTextView.setText(alive ? "ON" : "OFF");
    }

    private static void fireTimeout() {
        TimeoutModel timeoutModel = new TimeoutModel();
    }
}
