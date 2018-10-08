package gzfns.com.inventoryregulation.model.home.mycar;

import java.util.ArrayList;

import gzfns.com.inventoryregulation.application.AppConfig;
import gzfns.com.inventoryregulation.bean.CarInfo;

/**
 * Created by user on 2018/7/20.
 */

public class MyCarPresenter extends MyCarContract.Persetner {
    private ArrayList<CarInfo> datas = new ArrayList<>();

    @Override
    protected void onStart() {

    }

    @Override
    public void getRukuDatas() {

        CarInfo info = null;
        for (int i = 0; i < 10; i++) {
            info = new CarInfo("aaa", i + 100 + "", AppConfig.CAR_STATUS_ZAIKU);
            datas.add(info);
        }
        mView.setRecyData(datas);
        mView.setRukuCount(datas.size() + "");
    }

    @Override
    public void getChukuDatas() {
        CarInfo info = null;
        for (int i = 0; i < 10; i++) {
            info = new CarInfo("bbb", i + 100 + "", AppConfig.CAR_STATUS_CHUKU);
            datas.add(info);
        }
        mView.setRecyData(datas);
        mView.setChukuCount(datas.size() + "");
    }

    @Override
    public void reflushData(int type) {
        datas.clear();
        CarInfo info = null;
        if (type == 0) { //刷新出库数据信息
            for (int i = 10; i < 13; i++) {
                info = new CarInfo("aaa", i + 100 + "", AppConfig.CAR_STATUS_CHUKU);
                datas.add(info);
            }
            mView.setRukuCount(datas.size() + "");
        } else if (type == 1) {//刷新入库数据信息
            for (int i = 10; i < 13; i++) {
                info = new CarInfo("bbb", i + 100 + "", AppConfig.CAR_STATUS_ZAIKU);
                datas.add(info);
            }
            mView.setChukuCount(datas.size() + "");
        }
        mView.setRecyData(datas);
    }


}
