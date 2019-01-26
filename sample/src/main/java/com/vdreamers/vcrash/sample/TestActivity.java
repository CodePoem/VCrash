package com.vdreamers.vcrash.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * test activity
 * <p>
 * date 2019/01/25 11:19:50
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String crashString = null;
        int length = crashString.length();
    }
}
