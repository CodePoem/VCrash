package com.vdreamers.vcrash.sample.timeoutexp;

import android.annotation.SuppressLint;
import android.os.Process;
import android.util.Log;

import com.vdreamers.vcrash.action.DefaultCrashAction;
import com.vdreamers.vcrash.core.CrashHandler;
import com.vdreamers.vcrash.model.CrashModel;

/**
 * TimeoutException CrashHandler
 * <p>
 * date 2019/02/20 20:31:31
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class TimeoutCrashHandler extends CrashHandler {

    protected TimeoutCrashHandler() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private static class SingleHolder {
        @SuppressWarnings("WeakerAccess")
        @SuppressLint("StaticFieldLeak")
        public static final CrashHandler INSTANCE = new TimeoutCrashHandler();
    }

    public static CrashHandler getInstance() {
        return TimeoutCrashHandler.SingleHolder.INSTANCE;
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        CrashModel crashModel = parseCrash(e);

        if (mCrashListener == null) {
            mCrashListener = new DefaultCrashAction();
        }
        mCrashListener.onCrash(mContext, crashModel);

        Log.e("TimeoutCrashHandler", "TimeoutCrashHandler uncaughtException");
        Process.killProcess(Process.myPid());
    }
}
