package com.vdreamers.vcrash.sample;

import android.content.Context;

import com.vdreamers.vcrash.action.CrashListener;
import com.vdreamers.vcrash.model.CrashModel;

/**
 * custom crash action
 * <p>
 * date 2019/01/25 11:09:23
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class CustomCrashAction implements CrashListener {
    @Override
    public void onCrash(Context context, CrashModel crashModel) {
        CustomCrashInfoActivity.actionStart(context);
    }
}
