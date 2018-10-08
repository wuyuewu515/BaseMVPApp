package gzfns.com.inventoryregulation.model.ruku;

import java.util.ArrayList;

import gzfns.com.inventoryregulation.base.BaseModel;
import gzfns.com.inventoryregulation.base.BasePresenter;
import gzfns.com.inventoryregulation.base.BaseView;
import gzfns.com.inventoryregulation.bean.CarInfo;
import gzfns.com.inventoryregulation.bean.RukuListInfo;

/**
 * Created by user on 2018/7/20.
 * 我的车辆
 */

public interface RukuContract {

    interface View extends BaseView {

        void setRecyData(ArrayList<RukuListInfo> datas);

        /**
         * 设置入库数量
         *
         * @param count
         */
   //     void setRukuCount(String count);

        /**
         * 设置出库数量
         *
         * @param count
         */
   //     void setChukuCount(String count);
    }

    interface Model extends BaseModel {
        /**
         * 获取数据
         */
        void getData();
    }

    abstract class Persetner extends BasePresenter<View, Model> {

        /**
         * 获取正常数据
         */
        public abstract void getNormalData();

        /**
         * 获取临时出库数据
         */
        public abstract void getTempDatas();

        /**
         * 刷新数据
         *
         * @param type 类型
         */
        public abstract void reflushData(int type);
    }
}
