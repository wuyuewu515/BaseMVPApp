package gzfns.com.inventoryregulation.model.login;

import gzfns.com.inventoryregulation.listener.RequestListener;

/**
 * Created by Administrator on 2018/7/20.
 */

public class LoginPresenter extends LoginContract.Presenter implements RequestListener {
    private final int requeCode = 1000;

    @Override
    protected void onStart() {

    }

    @Override
    public void loginOnline() {
        mModel.loginOnline(mView.getUserName(), mView.getPassWord(), this, requeCode);
    }

    @Override
    public void onRequestCallBack(Integer requestCode, boolean success, String msg, Object data) {
        switch (requestCode) {
            case requeCode:
                processLoginResult(success, msg, data);
                break;
        }
    }

    private void processLoginResult(boolean success, String msg, Object data) {
        if (success) {

        }
    }
}
