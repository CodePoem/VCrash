package com.vdreamers.vcrash.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Process;

import com.vdreamers.vcrash.action.CrashListener;
import com.vdreamers.vcrash.action.DefaultCrashAction;
import com.vdreamers.vcrash.model.CrashModel;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * custom crash handler
 * <p>
 * date 2019/01/24 01:06:04
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /**
     * context better be application context
     */
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    /**
     * system default UncaughtExceptionHandler
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /**
     * crash listener
     */
    private CrashListener mCrashListener;

    public CrashHandler init(Context context) {
        if (context != null) {
            mContext = context.getApplicationContext();
        }
        return getInstance();
    }

    public CrashHandler init(Context context, CrashListener crashListener) {
        mCrashListener = crashListener;
        init(context);
        return getInstance();
    }

    public CrashHandler setCrashListener(CrashListener crashListener) {
        mCrashListener = crashListener;
        return getInstance();
    }

    private CrashHandler() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private static class SingleHolder {
        @SuppressWarnings("WeakerAccess")
        @SuppressLint("StaticFieldLeak")
        public static final CrashHandler INSTANCE = new CrashHandler();
    }

    public static CrashHandler getInstance() {
        return SingleHolder.INSTANCE;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        CrashModel crashModel = parseCrash(e);

        if (mCrashListener == null) {
            mCrashListener = new DefaultCrashAction();
        }
        mCrashListener.onCrash(mContext, crashModel);

//        Process.killProcess(Process.myPid());
        if (mDefaultHandler == null) {
            Process.killProcess(Process.myPid());
        } else {
            mDefaultHandler.uncaughtException(t, e);
        }
    }

    private CrashModel parseCrash(Throwable throwable) {
        CrashModel crashModel = new CrashModel();
        if (throwable == null) {
            return crashModel;
        }
        try {
            crashModel.setEx(throwable);
            crashModel.setTime(System.currentTimeMillis());
            if (throwable.getCause() != null) {
                throwable = throwable.getCause();
            }
            crashModel.setExceptionMsg(throwable.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            pw.flush();
            String exceptionType = throwable.getClass().getName();

            if (throwable.getStackTrace() != null && throwable.getStackTrace().length > 0) {
                StackTraceElement element = throwable.getStackTrace()[0];

                crashModel.setLineNumber(element.getLineNumber());
                crashModel.setClassName(element.getClassName());
                crashModel.setFileName(element.getFileName());
                crashModel.setMethodName(element.getMethodName());
                crashModel.setExceptionType(exceptionType);
            }

            crashModel.setFullException(sw.toString());
        } catch (Exception e) {
            return crashModel;
        }
        return crashModel;
    }
}
