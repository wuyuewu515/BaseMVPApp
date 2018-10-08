package gzfns.com.inventoryregulation.model.photopre;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import gzfns.com.inventoryregulation.GlideApp;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.adapter.PhotoPreAdapter;
import gzfns.com.inventoryregulation.base.BaseActivity;
import gzfns.com.inventoryregulation.views.TitleBar;

/**
 * Created by user on 2018/7/25.
 */

public class PhotoPreActivity extends BaseActivity<PhotoPresenter, PhotoPreModel>
        implements PhotoPreContract.View {

    @BindView(R.id.titlebar_photo)
    TitleBar titlebarPhoto;
    @BindView(R.id.viewPager_photo)
    ViewPager viewPagerPhoto;


    private PhotoPreAdapter adapter;

    @Override
    protected Integer getContentId() {
        return R.layout.photo_activity_layout;
    }

    @Override
    protected void initView() {
//        titlebarPhoto.setRightText("删除").setRightTextListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showShort(activity, "要删除的照片是" + viewPagerPhoto.getCurrentItem() + "", 0);
//            }
//        });
    }

    @Override
    protected void initData() {
        mPresenter.handleIntent(getIntent());
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initListener() {
        viewPagerPhoto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                titlebarPhoto.setMiddleTitleText(position + 1 + "/" + adapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setViewData(ArrayList<String> datas, int position) {
        titlebarPhoto.setMiddleTitleText(position + 1 + "/" + datas.size());
        ArrayList<View> views = new ArrayList<>();
        views.clear();
        for (int i = 0; i < datas.size(); i++) {
            views.add(getView(datas.get(i)));
        }
        if (adapter == null) {
            adapter = new PhotoPreAdapter(views, this);
        } else
            adapter.notifyDataSetChanged();

        viewPagerPhoto.setAdapter(adapter);
        viewPagerPhoto.setCurrentItem(position);
    }


    /**
     * 获取每张照片的view
     *
     * @param imageUrl 照片的url
     * @return
     */
    public View getView(String imageUrl) {
        ImageView imageView;
        imageView = (ImageView) LayoutInflater.from(this).inflate(R.layout.photo_layout, null, false);
        GlideApp.with(this).load(imageUrl).error(R.mipmap.icon_error).into(imageView);
        return imageView;
    }

    /**
     * @param activity
     * @param position 用户点击预览的照片的位置
     * @param jsonStr  json数据
     */
    public static void goTo(Activity activity, String jsonStr, int position) {
        Intent intent = new Intent(activity, PhotoPreActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("jsonStr", jsonStr);
        activity.startActivity(intent);

    }
}
