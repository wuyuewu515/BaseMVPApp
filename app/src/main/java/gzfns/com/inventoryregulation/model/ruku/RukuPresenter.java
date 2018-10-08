package gzfns.com.inventoryregulation.model.ruku;

import java.util.ArrayList;

import gzfns.com.inventoryregulation.application.AppConfig;
import gzfns.com.inventoryregulation.bean.CarInfo;
import gzfns.com.inventoryregulation.bean.RukuListInfo;
import gzfns.com.inventoryregulation.model.ruku.RukuContract;

/**
 * Created by user on 2018/7/20.
 */

public class RukuPresenter extends RukuContract.Persetner {
    private ArrayList<RukuListInfo> datas = new ArrayList<>();

    @Override
    protected void onStart() {

    }

    @Override
    public void getNormalData() {

        RukuListInfo info = null;
        for (int i = 0; i < 10; i++) {
            info = new RukuListInfo("正常入库", i + 100 + "", AppConfig.CAR_STATUS_ZHENGCHANG);
            datas.add(info);
        }
        mView.setRecyData(datas);
        //  mView.setRukuCount(datas.size() + "");
    }

    @Override
    public void getTempDatas() {
        RukuListInfo info = null;
        for (int i = 0; i < 10; i++) {
            info = new RukuListInfo("临时出库", i + 100 + "", AppConfig.CAR_STATUS_LINSHICHUKU);
            datas.add(info);
        }
        mView.setRecyData(datas);
        // mView.setChukuCount(datas.size() + "");
    }

    @Override
    public void reflushData(int type) {
        datas.clear();
        RukuListInfo info = null;
        if (type == 0) { //刷新出库数据信息
            for (int i = 10; i < 13; i++) {
                info = new RukuListInfo("驳回", i + 100 + "", AppConfig.CAR_STATUS_BOHUI);
                datas.add(info);
            }
            //   mView.setRukuCount(datas.size() + "");
        } else if (type == 1) {//刷新入库数据信息
            for (int i = 10; i < 13; i++) {
                info = new RukuListInfo("临时出库", i + 100 + "", AppConfig.CAR_STATUS_ZHENGCHANG);
                datas.add(info);
            }
            // mView.setChukuCount(datas.size() + "");
        }
        mView.setRecyData(datas);
    }


}
