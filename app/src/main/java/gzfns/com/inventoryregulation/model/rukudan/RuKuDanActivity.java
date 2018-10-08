package gzfns.com.inventoryregulation.model.rukudan;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import gzfns.com.inventoryregulation.GlideApp;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.base.BaseActivity;
import gzfns.com.inventoryregulation.bean.CarInfo;
import gzfns.com.inventoryregulation.model.photopre.PhotoPreActivity;
import gzfns.com.inventoryregulation.utils.JsonUtils;
import gzfns.com.inventoryregulation.utils.ToastUtils;
import gzfns.com.inventoryregulation.views.TitleBar;
import me.zhouzhuo.zzimagebox.ZzImageBox;

/**
 * Created by user on 2018/7/24.
 * 入库单
 */

public class RuKuDanActivity extends BaseActivity<RuKuDanPresenter, RuKuDanModel> implements RuKuDanContract.View {


    @BindView(R.id.title_rukudan)
    TitleBar titleRukudan;
    @BindView(R.id.tv_yewu_ruku)
    TextView tvYewuRuku;
    @BindView(R.id.tv_pinpai_ruku)
    TextView tvPinpaiRuku;
    @BindView(R.id.tv_chexing_ruku)
    TextView tvChexingRuku;
    @BindView(R.id.tv_chejiahao_ruku)
    TextView tvChejiahaoRuku;
    @BindView(R.id.et_mailes_ruku)
    EditText etMailesRuku;
    @BindView(R.id.zz_image_box)
    ZzImageBox zzImageBox;
    @BindView(R.id.et_beizhu_ruku)
    EditText etBeizhuRuku;
    @BindView(R.id.rl_obd_ruku)
    RelativeLayout rlObdRuku;
    @BindView(R.id.rl_rfid_ruku)
    RelativeLayout rlRfidRuku;
    @BindView(R.id.iv_video_ruku)
    ImageView ivVideoRuku;


    private boolean flag = true;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected Integer getContentId() {
        return R.layout.rukudan_activity_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        titleRukudan.setRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.save();
            }
        });

        mPresenter.handleIntent(getIntent());
    }

    @Override
    protected void initListener() {
        zzImageBox.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String filePath, ImageView iv) {
                String jsonStr  = JsonUtils.toJson(zzImageBox.getAllImages());
                PhotoPreActivity.goTo(activity, jsonStr,position);
            }

            @Override
            public void onDeleteClick(int position, String filePath) {
                zzImageBox.removeImage(position);
            }

            @Override
            public void onAddClick() {
                zzImageBox.addImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_" +
                        "10000&sec=1532508433008&di=d35620cb11952762d8a1fab4ad2a4fcd&imgtype=0&src=http%3A%2F%2Fimgm." +
                        "cnmo-img.com.cn%2Fappimg%2Fscreenpic%2Fmiddle%2F826%2F825594.jpg");
            }
        });


        //加载网络图片必须重调用的方法
        zzImageBox.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                GlideApp.with(activity).load(url).error(R.mipmap.icon_error).into(iv);
            }
        });

        etBeizhuRuku.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etBeizhuRuku.setText("\u3000\u3000");
                } else {
                    etBeizhuRuku.setText("");
                }
            }
        });
    }


    @OnClick({R.id.tv_obd_ruku, R.id.iv_video_ruku})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_obd_ruku://obd拍照
                ToastUtils.showShort(activity, "obd拍照", 0);
                break;
            case R.id.iv_video_ruku://视频
                ToastUtils.showShort(activity, "视频拍摄", 0);
                break;

        }
    }


    public static void goTo(Activity activity, CarInfo carInfo) {
        Intent intent = new Intent(activity, RuKuDanActivity.class);
        intent.putExtra("CarInfo", carInfo);
        activity.startActivity(intent);

    }

    @Override
    public void setScanType(boolean visible) {
        rlRfidRuku.setVisibility(visible ? View.VISIBLE : View.GONE);
        rlObdRuku.setVisibility(visible ? View.GONE : View.VISIBLE);

    }

}
