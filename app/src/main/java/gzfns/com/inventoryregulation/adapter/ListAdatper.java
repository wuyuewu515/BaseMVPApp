package gzfns.com.inventoryregulation.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.application.AppConfig;
import gzfns.com.inventoryregulation.bean.RukuListInfo;

/**
 * Created by user on 2018/7/20.
 * 清单列表适配器
 */

public class ListAdatper extends BaseQuickAdapter<RukuListInfo, BaseViewHolder> {
    public ListAdatper(@Nullable List<RukuListInfo> data) {
        super(R.layout.item_list_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, final RukuListInfo item) {
        holder.setVisible(R.id.iv_carStatus_item, false);
        holder.setText(R.id.tv_carCode_item, item.getCarCode());
        holder.setText(R.id.tv_carNum_item, item.getCarName());

        holder.getView(R.id.tv_carNum_item).setEnabled(false);
        holder.getView(R.id.iv_carStatus_item).setEnabled(false);

        if (AppConfig.CAR_STATUS_ZHENGCHANG == item.getCarStatus()) {
            holder.setImageDrawable(R.id.iv_carstatus_item, mContext.getResources().getDrawable(R.mipmap.icon_dairuku));
        } else if (AppConfig.CAR_STATUS_LINSHICHUKU == item.getCarStatus()) {
            holder.setImageDrawable(R.id.iv_carstatus_item, mContext.getResources().getDrawable(R.mipmap.zaiku));
        } else if (AppConfig.CAR_STATUS_BOHUI == item.getCarStatus()) {
            holder.setVisible(R.id.iv_carStatus_item, true);
            holder.setImageDrawable(R.id.iv_carstatus_item, mContext.getResources().getDrawable(R.mipmap.icon_bohui));
            holder.getView(R.id.tv_carNum_item).setEnabled(true);
            holder.getView(R.id.iv_carStatus_item).setEnabled(true);
        }

        //tv_carNum_item       iv_carStatus_item
        holder.itemView.setTag(R.id.id_view_tag, item);
        holder.setOnClickListener(R.id.tv_carNum_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onclickListener)
                    onclickListener.onClick(item);
            }
        });
        holder.setOnClickListener(R.id.iv_carStatus_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onclickListener)
                    onclickListener.onClick(item);

            }
        });
    }

    private ItemOnclickListener onclickListener;


    public ListAdatper setSelfOnclickListener(ItemOnclickListener onclickListener) {
        this.onclickListener = onclickListener;
        return this;
    }

    public interface ItemOnclickListener {
        void onClick(RukuListInfo info);
    }
}
