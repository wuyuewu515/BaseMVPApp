package gzfns.com.inventoryregulation.model.home.personal;

import gzfns.com.inventoryregulation.base.BaseModel;
import gzfns.com.inventoryregulation.base.BasePresenter;
import gzfns.com.inventoryregulation.base.BaseView;

/**
 * Created by user on 2018/7/19.
 * 个人中心控制层
 */

public interface PersonalContract {

    interface View extends BaseView {

        /**
         * 设置租户名称
         *
         * @param userName
         */
        void setUserName(String userName);

        /**
         * 设置账户名称
         *
         * @param userCount
         */
        void setUserCount(String userCount);

        /**
         * 设置缓存的大小
         *
         * @param size
         */
        void setCacheSize(String size);

        void showToast(String msg);
    }

    interface Model extends BaseModel {
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        /**
         * 清除缓存
         */
        abstract void clearCache();

        /**
         * 获取缓存的大小
         */
        abstract void getCache();

        /**
         * 退出登录
         */
        abstract void loginOut();
    }
}
