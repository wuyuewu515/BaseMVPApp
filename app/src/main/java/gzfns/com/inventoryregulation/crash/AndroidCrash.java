package gzfns.com.inventoryregulation.crash;

import android.content.Context;

import gzfns.com.inventoryregulation.crash.log.CrashCatcher;
import gzfns.com.inventoryregulation.crash.log.CrashListener;


/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-03
 * Time: 21:37
 * FIXME
 */
public class AndroidCrash {
    private static final AndroidCrash instance = new AndroidCrash();

    private CrashListener mReporter=null;

    private String mLogDir;

    private AndroidCrash(){}


    public static AndroidCrash getInstance() {
        return instance;
    }

    /**
     * 设置报告处理。
     * @param reporter
     * @return
     */
    public AndroidCrash setCrashReporter(CrashListener reporter) {
        mReporter = reporter;
        return this;
    }

    /**
     * 设置日志存储目录
     * @param logDir
     * @return
     */
    public AndroidCrash setLogFileDir(String logDir) {
        mLogDir = logDir;
        return this;
    }

    public void init(Context mContext) {
        if (mLogDir == null) {
            mLogDir =mContext.getFilesDir().getPath();
        }

        CrashCatcher.getInstance().init(mContext,mLogDir, mReporter,Thread.getDefaultUncaughtExceptionHandler());
        Thread.setDefaultUncaughtExceptionHandler(CrashCatcher.getInstance());
    }


}