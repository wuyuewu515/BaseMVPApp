package gzfns.com.inventoryregulation.model.home.personal;

import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.base.BaseFragment;
import gzfns.com.inventoryregulation.model.home.AboutUsActivity;
import gzfns.com.inventoryregulation.utils.ToastUtils;
import gzfns.com.inventoryregulation.views.CommonDailog;
import gzfns.com.inventoryregulation.views.TitleBar;

/**
 * Created by Administrator on 2018/3/1.
 * 个人中心
 */
public class PersonalFragment extends BaseFragment<PersonalPresenter, PersonalModel> implements PersonalContract.View {


    @BindView(R.id.presonal_title)
    TitleBar presonalTitle;
    @BindView(R.id.card_about_personal)
    CardView cardAboutPersonal;
    @BindView(R.id.card_clear_personal)
    CardView cardClearPersonal;
    @BindView(R.id.card_loginout_personal)
    CardView cardLoginoutPersonal;
    @BindView(R.id.tv_userName_personal)
    TextView tvUserNamePersonal;
    @BindView(R.id.tv_countName_personal)
    TextView tvCountNamePersonal;
    @BindView(R.id.tv_cache_personal)
    TextView tvCachePersonal;

    @Override
    protected Integer getContentId() {
        return R.layout.personal_fragment_layout;
    }

    @Override
    protected void initView() {
        mPresenter.setVM(this, mModel);

    }

    @Override
    protected void initData() {
        mPresenter.getCache();
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.card_about_personal, R.id.card_clear_personal, R.id.card_loginout_personal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_about_personal://关于我们
                AboutUsActivity.goInto(mActivity);
                break;
            case R.id.card_clear_personal://清除缓存
                CommonDailog dailog = new CommonDailog(mActivity, "", "清除缓存?");
                dailog.setDialogListener(new CommonDailog.DialogListener() {
                    @Override
                    public void confrom() {
                        mPresenter.clearCache();

                    }

                    @Override
                    public void cancle() {

                    }
                });
                dailog.show();
                break;
            case R.id.card_loginout_personal://退出登录
                mPresenter.loginOut();
                break;
        }
    }

    @Override
    public void setUserName(String userName) {
        userName = TextUtils.isEmpty(userName) ? "" : userName;
        tvUserNamePersonal.setText(userName);
    }

    @Override
    public void setUserCount(String userCount) {
        userCount = TextUtils.isEmpty(userCount) ? "" : userCount;
        tvCountNamePersonal.setText(userCount);
    }

    @Override
    public void setCacheSize(String size) {
        size = TextUtils.isEmpty(size) ? "0k" : size;
        tvCachePersonal.setText(size);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(mActivity, msg, 0);
    }


}
