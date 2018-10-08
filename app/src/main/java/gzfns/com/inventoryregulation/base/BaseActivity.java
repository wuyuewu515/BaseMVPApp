package gzfns.com.inventoryregulation.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.lypeer.fcpermission.FcPermissions;
import com.lypeer.fcpermission.impl.FcPermissionsCallbacks;

import java.util.List;

import butterknife.ButterKnife;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.manager.AppManager;
import gzfns.com.inventoryregulation.utils.StatusBarUtil;
import gzfns.com.inventoryregulation.utils.TUtils;

/**
 * Created by Administrator on 2016/9/13.
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements FcPermissionsCallbacks, BaseView {
    protected Activity activity;
    protected T mPresenter;
    protected E mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentView() != null)
            setContentView(getContentView());
        else
            setContentView(getContentId());

        this.activity = this;
        AppManager.addActivity(this);
        ButterKnife.bind(this);
        mPresenter = TUtils.getT(this, 0);
        mModel = TUtils.getT(this, 1);
        this.initPresenter();
        this.initView();
        this.initData();
        this.initListener();

        if (getFullFlag()) {
            ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
            View parentView = contentFrameLayout.getChildAt(0);
            if (parentView != null && Build.VERSION.SDK_INT >= 14) {
                parentView.setFitsSystemWindows(true);
            }
//            phoneType = StatusBarUtil.StatusBarLightMode(this);
//            StatusBarUtil.statusBarColorByPhoneType(phoneType, this);
        }

        StatusBarUtil.transparencyBar(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        if (mModel != null) {
            mModel = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //第三方申请权限库
        FcPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 返回视图
     *
     * @return
     */
    protected View getContentView() {
        return null;
    }

    /**
     * 返回视图ID
     *
     * @return
     */
    protected Integer getContentId() {
        return null;
    }

    /**
     * 获取空布局
     *
     * @return
     */
    protected View getEmptyView() {
        View view = LayoutInflater.from(activity).inflate(R.layout.empty_layout, null, false);
        return view;
    }

    /**
     * 权限申请回调接口，成功时回调
     *
     * @param i
     * @param list
     */
    @Override
    public void onPermissionsGranted(int i, List<String> list) {

    }

    /**
     * 权限申请回调接口，被拒时回调
     *
     * @param i
     * @param list
     */
    @Override
    public void onPermissionsDenied(int i, List<String> list) {
        FcPermissions.checkDeniedPermissionsNeverAskAgain(this,
                "请在设置中打开权限",
                R.string.setting, R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onCancel(dialog, which);
                    }
                }, list);
    }

    protected void onCancel(DialogInterface dialog, int which) {
    }

    protected void initPresenter() {
    }

    @Override
    public BaseActivity getMyActivity() {
        return this;
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    public boolean getFullFlag() {
        return true;
    }

    public boolean isDisMisToast() {
        return true;
    }
}
