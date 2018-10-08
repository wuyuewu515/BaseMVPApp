package gzfns.com.inventoryregulation.application;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Date;

import de.mindpipe.android.logging.log4j.LogConfigurator;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.bean.Account;
import gzfns.com.inventoryregulation.crash.AndroidCrash;
import gzfns.com.inventoryregulation.crash.log.CrashListener;
import gzfns.com.inventoryregulation.manager.AppManager;
import gzfns.com.inventoryregulation.net.NetWorkHelper;
import gzfns.com.inventoryregulation.utils.DateUtils;


/**
 * Created by Administrator on 2016/10/25.
 */
public class AppApplication extends BaseApplication {
    private static Context instance;
    private Account account;
    public LogConfigurator logConfigurator;
    public Logger logger = null;
    public static boolean isAutoLogin = true;// 是否自动登录

    public String userAgr;// 用户服务协议
    public String payAgr;// 充值协议
    public String payIntro;// 充值说明

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.white, R.color.text_black);//全局设置主题颜色
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化网络访问框架
        NetWorkHelper.initHttpHelper(this);

        if(Build.VERSION.SDK_INT >= 24) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }

    }

    public void initLogConfig() {
        //初始化日志信息
        initLoginConfig();
        //初始化系统配置
        AppConfig.initConfig(this);
        //logger.info("程序启动");
        AndroidCrash.getInstance().setLogFileDir(AppConfig.getLogDir()).setCrashReporter
                (myCrashListener).init(this);
    }

    private void initLoginConfig() {
        //初始化日志设置
        this.logConfigurator = new LogConfigurator();
        //设置日志文件名称
        this.logConfigurator.setFileName(AppConfig.getLogDir() + File.separator + DateUtils
                .date2String(new Date(), "yyyyMMdd") + ".log");
        this.logConfigurator.setFilePattern("%d [%p]-[%c.%M(%L)] %m %n");
        //设置日志级别
        this.logConfigurator.setRootLevel(Level.DEBUG);
        this.logConfigurator.setLevel("org.apache", Level.ERROR);
        //设置最大文件大小
        this.logConfigurator.setMaxFileSize(1024 * 1024 * 5);
        this.logConfigurator.setImmediateFlush(true);
        //应用日志设置
        this.logConfigurator.configure();
        //初始化日志
        this.logger = Logger.getLogger(AppApplication.class);
    }

    public static Context getInstance() {
        return instance;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    private CrashListener myCrashListener = new CrashListener() {
        @Override
        public void sendFile(File file, final Throwable ex) {
        }

        @Override
        public void closeApp(Thread thread, Throwable ex) {
            Toast.makeText(AppManager.currentActivity(), "程序出现异常,即将退出,请稍后重试。", Toast.LENGTH_LONG).show();
            logger.error("系统异常退出", ex);
            new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppManager.AppExit(AppApplication.this);
                }
            }, 2000);
        }
    };

    public void stopUploadService() {
    }

    public void startUploadService() {
    }

    @Override
    public void onTerminate() {
        account = null;
        stopUploadService();
        //退出时结束所有的activity
        AppManager.finishAllActivity();
        System.exit(0);
        super.onTerminate();
    }
}
