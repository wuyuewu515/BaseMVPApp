package gzfns.com.inventoryregulation.model.splash;

import android.os.Handler;

import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.base.BaseActivity;
import gzfns.com.inventoryregulation.model.home.MainActivity;
import gzfns.com.inventoryregulation.model.login.LoginActivity;

/**
 * Created by user on 2018/7/18.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected Integer getContentId() {
        return R.layout.splash_activity_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginActivity.into(activity);
                finish();
            }
        }, 1500);
    }

    @Override
    protected void initListener() {

    }
}
