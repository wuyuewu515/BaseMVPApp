package gzfns.com.inventoryregulation.model.home.main;

import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.base.BaseFragment;
import gzfns.com.inventoryregulation.model.ruku.RukuActivity;
import gzfns.com.inventoryregulation.utils.ToastUtils;
import gzfns.com.inventoryregulation.views.TitleBar;

/**
 * Created by Administrator on 2018/3/1.
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.home_titleBar)
    TitleBar homeTitleBar;

    @Override
    protected Integer getContentId() {
        return R.layout.home_fragment_layout;
    }

    @Override
    protected void initView() {
        homeTitleBar.setLeftIcon(0);
        homeTitleBar.setRightIcon(R.mipmap.changecar);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        homeTitleBar.setRightIconListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort(mActivity, "切换停车场", 0);
            }
        });
    }


    @OnClick({R.id.card_ruKu_home, R.id.card_chuKu_home, R.id.card_qingDian_home, R.id.card_tiHuan_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_ruKu_home: //入库
                RukuActivity.goInto(mActivity);
                break;
            case R.id.card_chuKu_home://出库
                ToastUtils.showShort(mActivity, "出库", 0);

                break;
            case R.id.card_qingDian_home://清点
                ToastUtils.showShort(mActivity, "清点", 0);

                break;
            case R.id.card_tiHuan_home://替换
                ToastUtils.showShort(mActivity, "替换", 0);

                break;
        }
    }
}
