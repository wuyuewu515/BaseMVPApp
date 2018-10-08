package gzfns.com.inventoryregulation.model.carsearch;

import java.util.ArrayList;

import gzfns.com.inventoryregulation.base.BaseModel;
import gzfns.com.inventoryregulation.base.BasePresenter;
import gzfns.com.inventoryregulation.base.BaseView;
import gzfns.com.inventoryregulation.bean.CarInfo;

/**
 * Created by user on 2018/7/23.
 */

interface CarSearchContract {
    interface View extends BaseView {
        void setAdapterData(ArrayList<CarInfo> datas);
    }

    interface Model extends BaseModel {

    }

    abstract class Persetner extends BasePresenter<View, Model> {

        /**
         * 根据关键字查询集合列表
         *
         * @param key
         */
       abstract void searchData(String key);
    }
}
