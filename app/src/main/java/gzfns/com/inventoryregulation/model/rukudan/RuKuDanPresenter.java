package gzfns.com.inventoryregulation.model.rukudan;

import android.content.Intent;

import gzfns.com.inventoryregulation.application.AppConfig;
import gzfns.com.inventoryregulation.bean.CarInfo;

/**
 * Created by user on 2018/7/24.
 */

public class RuKuDanPresenter extends RuKuDanContract.Presenter {

    private CarInfo carInfo;

    @Override
    protected void onStart() {

    }

    @Override
    void save() {
        //先检查输入的合法性，再检验是否需要地理围栏
        if (needCheckWeiLan()) {
        } else {
        }
    }

    @Override
    void handleIntent(Intent intent) {
        carInfo = (CarInfo) intent.getSerializableExtra("CarInfo");
        if (null == carInfo)
            return;

        mView.setScanType(needCheckWeiLan());
    }


    /**
     * 围栏检验
     *
     * @return
     */
    public boolean needCheckWeiLan() {
        return AppConfig.CAR_STATUS_LINSHICHUKU == carInfo.getCarStatus();
    }
}
