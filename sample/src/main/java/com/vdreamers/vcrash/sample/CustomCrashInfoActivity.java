package com.vdreamers.vcrash.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * custom crash info activity
 * <p>
 * date 2019/01/25 11:18:31
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class CustomCrashInfoActivity extends AppCompatActivity {

    /**
     * CustomCrashInfoActivity start method
     *
     * @param context context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CustomCrashInfoActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_crash_info);
    }
}
