package gzfns.com.inventoryregulation.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lypeer.fcpermission.FcPermissions;
import com.lypeer.fcpermission.impl.FcPermissionsCallbacks;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.utils.TUtils;

/**
 * Created by Administrator on 2016/9/13.
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment implements FcPermissionsCallbacks, BaseView {


    protected T mPresenter;
    protected E mModel;
    protected View rootView;
    protected Activity mActivity;
    protected Object data;
    protected boolean isCreate = false;
    protected Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtils.getT(this, 0);
        mModel = TUtils.getT(this, 1);
        initPresenter();
        mActivity = getMyActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getContentId() == null ? getContentView() : inflater.inflate(getContentId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        isCreate = false;
        bind = ButterKnife.bind(this, view);
        initView();
        initData();
        initListener();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //第三方权限申请库
        FcPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    protected Integer getContentId() {
        return null;
    }

    protected View getContentView() {
        return null;
    }

    protected void initPresenter() {
    }

    public BaseActivity getMyActivity() {
        return (BaseActivity) getActivity();
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            initView();
        }
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
                R.string.setting, R.string.cancel, null, list);
    }

    protected View getEmptyView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.empty_layout, null, false);
        return view;
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != bind)
            bind.unbind();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        if (mModel != null) {
            mModel = null;
        }
    }
}
