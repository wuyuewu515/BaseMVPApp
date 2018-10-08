package gzfns.com.inventoryregulation.model.carsearch;

import java.util.ArrayList;

import gzfns.com.inventoryregulation.bean.CarInfo;
import gzfns.com.inventoryregulation.utils.ToastUtils;

/**
 * Created by user on 2018/7/23.
 */

public class CarSearchPresenter extends CarSearchContract.Persetner {
    private ArrayList<CarInfo> datas = new ArrayList<>();

    @Override
    protected void onStart() {

    }

    @Override
    void searchData(String key) {
        ToastUtils.showShort(mView.getMyActivity(), key, 0);
        mView.setAdapterData(datas);
    }
}
