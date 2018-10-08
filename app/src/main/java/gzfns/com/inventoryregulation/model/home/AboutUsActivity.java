package gzfns.com.inventoryregulation.model.home;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import butterknife.BindView;
import gzfns.com.inventoryregulation.BuildConfig;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.base.BaseActivity;

/**
 * Created by user on 2018/7/19.
 * 关于我们
 */

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.tv_version_aboutus)
    TextView tvVersionAboutus;

    @Override
    protected Integer getContentId() {
        return R.layout.personal_aboutus_activity_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        tvVersionAboutus.setText(BuildConfig.APP_NAME + "V " + BuildConfig.VERSION_NAME);
    }

    @Override
    protected void initListener() {

    }

    public static void goInto(Activity activity) {
        Intent intent = new Intent(activity, AboutUsActivity.class);
        activity.startActivity(intent);
    }


}
