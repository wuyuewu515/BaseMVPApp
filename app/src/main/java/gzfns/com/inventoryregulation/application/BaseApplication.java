package gzfns.com.inventoryregulation.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/10/25.
 */
public class BaseApplication extends Application {

    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }

    public static Context getInstance() {
        return instance;
    }
}
