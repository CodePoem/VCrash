package com.vdreamers.vcrash.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.vdreamers.vcrash.R;
import com.vdreamers.vcrash.model.CrashModel;
import com.vdreamers.vcrash.model.DeviceModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

/**
 * show crash info activity
 * <p>
 * date 2019/01/24 13:51:29
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class CrashInfoActivity extends AppCompatActivity {

    /**
     * crash info model
     */
    private CrashModel mCrashModel;
    /**
     * TextView - crash simple info content
     */
    private TextView mCrashSimpleInfoContent;
    /**
     * TextView - crash class name content
     */
    private TextView mCrashClassNameContent;
    /**
     * TextView - crash method name content
     */
    private TextView mCrashMethodNameContent;
    /**
     * TextView - crash line number content
     */
    private TextView mCrashLineNumberContent;
    /**
     * TextView - crash type content
     */
    private TextView mCrashTypeContent;
    /**
     * TextView - crash time content
     */
    private TextView mCrashTimeContent;
    /**
     * TextView - crash device name content
     */
    private TextView mCrashDeviceNameContent;
    /**
     * TextView - crash device brand content
     */
    private TextView mCrashDeviceBrandContent;
    /**
     * TextView - crash system version content
     */
    private TextView mCrashSystemVersionContent;
    /**
     * TextView - crash full info content
     */
    private TextView mCrashFullInfoContent;
    /**
     * key-crashModel
     */
    public static final String KEY_CRASH_MODEL = "crashModel";
    /**
     * crash time format
     */
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /**
     * CrashInfoActivity start method
     *
     * @param context    context
     * @param crashModel crash info model
     */
    public static void actionStart(Context context, CrashModel crashModel) {
        Intent intent = new Intent(context, CrashInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_CRASH_MODEL, crashModel);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        setContentView(R.layout.v_crash_activity_crash_info);
        initView();
        initData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        mCrashModel = bundle.getParcelable(KEY_CRASH_MODEL);
    }

    private void initView() {
        mCrashSimpleInfoContent = findViewById(R.id.tv_content_crash_simple_info);

        mCrashClassNameContent = findViewById(R.id.tv_content_crash_class_name);
        mCrashMethodNameContent = findViewById(R.id.tv_content_crash_method_name);
        mCrashLineNumberContent = findViewById(R.id.tv_content_crash_line_num);
        mCrashTypeContent = findViewById(R.id.tv_content_crash_type);
        mCrashTimeContent = findViewById(R.id.tv_content_crash_time);

        mCrashDeviceNameContent = findViewById(R.id.tv_content_crash_device_name);
        mCrashDeviceBrandContent = findViewById(R.id.tv_content_crash_device_brand);
        mCrashSystemVersionContent = findViewById(R.id.tv_content_crash_system_version);

        mCrashFullInfoContent = findViewById(R.id.tv_content_crash_full_info);
    }

    private void initData() {
        if (mCrashModel == null) {
            return;
        }
        mCrashSimpleInfoContent.setText(mCrashModel.getExceptionMsg());

        mCrashClassNameContent.setText(mCrashModel.getClassName());
        mCrashMethodNameContent.setText(mCrashModel.getMethodName());
        mCrashLineNumberContent.setText(String.valueOf(mCrashModel.getLineNumber()));
        mCrashTypeContent.setText(mCrashModel.getExceptionType());
        mCrashTimeContent.setText(df.format(mCrashModel.getTime()));

        DeviceModel deviceModel = mCrashModel.getDevice();
        if (deviceModel != null) {
            mCrashDeviceNameContent.setText(deviceModel.getModel());
            mCrashDeviceBrandContent.setText(deviceModel.getBrand());
            mCrashSystemVersionContent.setText(deviceModel.getVersion());
        }

        mCrashFullInfoContent.setText(mCrashModel.getFullException());
    }
}
