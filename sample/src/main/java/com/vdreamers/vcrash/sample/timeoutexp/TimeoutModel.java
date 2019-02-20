package com.vdreamers.vcrash.sample.timeoutexp;

import android.util.Log;

/**
 * Model which make time out
 * <p>
 * date 2019/02/18 10:43:55
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
public class TimeoutModel {

    @Override
    protected void finalize() throws Throwable {
        Log.d("TimeoutModel", "=============fire finalize============="+Thread.currentThread().getName());
        super.finalize();
        //每个手机触发 Timeout 的时长不同，比如 vivo 的某些rom 是2分钟，模拟器统一都是10秒钟，所以在模拟器上效果明显
        Thread.sleep(300000);
    }
}
