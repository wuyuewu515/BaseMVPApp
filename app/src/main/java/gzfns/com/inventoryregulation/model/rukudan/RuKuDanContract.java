package gzfns.com.inventoryregulation.model.rukudan;

import android.content.Intent;

import gzfns.com.inventoryregulation.base.BaseModel;
import gzfns.com.inventoryregulation.base.BasePresenter;
import gzfns.com.inventoryregulation.base.BaseView;

/**
 * Created by user on 2018/7/24.
 */

public interface RuKuDanContract {

    interface View extends BaseView {

        /**
         * 扫描的类型
         * @param visible true ---临时入库  rfid   false --obd
         */
        void setScanType(boolean visible);
    }

    interface Model extends BaseModel {
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        /**
         * 保存操作
         */
        abstract void save();

        abstract void handleIntent(Intent intent);
    }
}
