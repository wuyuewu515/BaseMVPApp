package gzfns.com.inventoryregulation.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.application.AppConfig;
import gzfns.com.inventoryregulation.bean.CarInfo;

/**
 * Created by user on 2018/7/20.
 * 我的车辆适配器
 */

public class MyCarAdatper extends BaseQuickAdapter<CarInfo, BaseViewHolder> {
    public MyCarAdatper(@Nullable List<CarInfo> data) {
        super(R.layout.item_mycar_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, CarInfo item) {
        holder.setText(R.id.tv_carCode_item, item.getCarCode());
        holder.setText(R.id.tv_carNum_item, item.getCarName());
        if (AppConfig.CAR_STATUS_ZAIKU == item.getCarStatus()) {
            holder.setImageDrawable(R.id.iv_carstatus_item, mContext.getResources().getDrawable(R.mipmap.icon_chuku));
        } else if (AppConfig.CAR_STATUS_CHUKU == item.getCarStatus()) {
            holder.setImageDrawable(R.id.iv_carstatus_item, mContext.getResources().getDrawable(R.mipmap.zaiku));
        } else if (AppConfig.CAR_STATUS_ZHENGCHANG == item.getCarStatus()) {
        } else if (AppConfig.CAR_STATUS_LINSHICHUKU == item.getCarStatus()) {
        }
        holder.itemView.setTag(R.id.id_view_tag, item);
    }
}
