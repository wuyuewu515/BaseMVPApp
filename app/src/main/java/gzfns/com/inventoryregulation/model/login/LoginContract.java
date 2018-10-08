package gzfns.com.inventoryregulation.model.login;

import gzfns.com.inventoryregulation.base.BaseModel;
import gzfns.com.inventoryregulation.base.BasePresenter;
import gzfns.com.inventoryregulation.base.BaseView;
import gzfns.com.inventoryregulation.listener.RequestListener;

/**
 * Created by user on 2018/7/18.
 */

public interface LoginContract {

    interface View extends BaseView {
        String getUserName();

        String getPassWord();
    }

    interface Modle extends BaseModel {
        void loginOnline(String userName, String passWord, RequestListener listener,int requestCode);
    }

    abstract class Presenter extends BasePresenter<View, Modle> {
        public abstract void loginOnline();
    }
}
