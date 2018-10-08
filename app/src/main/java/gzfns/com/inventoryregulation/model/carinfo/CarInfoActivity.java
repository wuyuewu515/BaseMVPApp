package gzfns.com.inventoryregulation.model.carinfo;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.BindView;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.base.BaseActivity;
import gzfns.com.inventoryregulation.bean.CarInfo;

/**
 * Created by user on 2018/7/23.
 * 车辆信息详情界面
 */

public class CarInfoActivity extends BaseActivity {

    @BindView(R.id.tv_pingpai_info)
    TextView tvPingpaiInfo;
    @BindView(R.id.tv_chexing_info)
    TextView tvChexingInfo;
    @BindView(R.id.tv_chejiahao_info)
    TextView tvChejiahaoInfo;
    @BindView(R.id.tv_obd_info)
    TextView tvObdInfo;
    @BindView(R.id.tv_zhuangtai_info)
    TextView tvZhuangtaiInfo;
    @BindView(R.id.tv_shijian_info)
    TextView tvShijianInfo;

    @Override
    protected Integer getContentId() {
        return R.layout.mycar_detail_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Serializable value = getIntent().getSerializableExtra("value");
        if (null != value && value instanceof CarInfo) {
            CarInfo carInfo = (CarInfo) value;
            bindData2View(carInfo);
        }

    }

    private void bindData2View(CarInfo carInfo) {
        tvChejiahaoInfo.setText(carInfo.getCarCode());
        tvPingpaiInfo.setText(carInfo.getCarName());
        tvObdInfo.setText(carInfo.getObdCode());
        tvChexingInfo.setText(carInfo.getCarType());
        tvShijianInfo.setText(carInfo.getTime());
        if (1 == carInfo.getCarStatus()) {
            tvZhuangtaiInfo.setText("入库时间");
        } else {
            tvZhuangtaiInfo.setText("出库时间");
        }

    }

    @Override
    protected void initListener() {

    }

    public static void goInto(Activity activity, CarInfo carInfo) {
        Intent intent = new Intent(activity, CarInfoActivity.class);
        intent.putExtra("value", carInfo);
        activity.startActivity(intent);
    }

}
