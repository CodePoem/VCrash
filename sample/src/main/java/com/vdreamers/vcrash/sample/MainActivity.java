package com.vdreamers.vcrash.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vdreamers.vcrash.core.CrashHandler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * main activity
 * <p>
 * date 2019/01/25 11:04:58
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrashHandler.getInstance().init(MainActivity.this);
        findViewById(R.id.btn_crash_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crashString = null;
                int length = crashString.length();
            }
        });
        findViewById(R.id.btn_crash_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
        findViewById(R.id.btn_crash_action_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashHandler.getInstance().setCrashListener(new CustomCrashAction());
            }
        });
        findViewById(R.id.btn_crash_action_custom_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashHandler.getInstance().setCrashListener(null);
            }
        });
    }
}
