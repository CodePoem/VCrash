package com.vdreamers.vcrash.action;

import android.content.Context;

import com.vdreamers.vcrash.core.CrashHandler;
import com.vdreamers.vcrash.model.CrashModel;

/**
 * crash listener
 * <p>
 * date 2019/01/24 10:50:51
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public interface CrashListener {
    /**
     * when {@link CrashHandler#uncaughtException(Thread, Throwable)} parse {@link CrashModel}
     * success
     *
     * @param context    context
     * @param crashModel crash info model
     */
    void onCrash(Context context, CrashModel crashModel);
}
