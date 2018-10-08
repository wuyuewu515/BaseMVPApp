package gzfns.com.inventoryregulation.model.home.mycar;

import java.util.ArrayList;
import java.util.List;

import gzfns.com.inventoryregulation.base.BaseModel;
import gzfns.com.inventoryregulation.base.BasePresenter;
import gzfns.com.inventoryregulation.base.BaseView;
import gzfns.com.inventoryregulation.bean.CarInfo;

/**
 * Created by user on 2018/7/20.
 * 我的车辆
 */

public interface MyCarContract {

    interface View extends BaseView {

        void setRecyData(ArrayList<CarInfo> datas);

        /**
         * 设置入库数量
         *
         * @param count
         */
        void setRukuCount(String count);

        /**
         * 设置出库数量
         *
         * @param count
         */
        void setChukuCount(String count);
    }

    interface Model extends BaseModel {
        /**
         * 获取数据
         */
        void getData();
    }

    abstract class Persetner extends BasePresenter<View, Model> {

        /**
         * 获取入库数据
         */
        public abstract void getRukuDatas();

        /**
         * 获取出库数据
         */
        public abstract void getChukuDatas();

        /**
         * 刷新数据
         *
         * @param type 类型
         */
        public abstract void reflushData(int type);
    }
}
