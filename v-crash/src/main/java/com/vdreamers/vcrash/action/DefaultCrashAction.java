package com.vdreamers.vcrash.action;

import android.content.Context;

import com.vdreamers.vcrash.model.CrashModel;

/**
 * default crash action
 * <p>
 * date 2019/01/24 11:28:44
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class DefaultCrashAction implements CrashListener {

    @Override
    public void onCrash(Context context, CrashModel crashModel) {
        CrashInfoActivity.actionStart(context, crashModel);
    }
}
