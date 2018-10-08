package gzfns.com.inventoryregulation.model.login;

import java.util.HashMap;
import java.util.Map;

import gzfns.com.inventoryregulation.application.ApiConstant;
import gzfns.com.inventoryregulation.application.Constants;
import gzfns.com.inventoryregulation.listener.RequestListener;
import gzfns.com.inventoryregulation.net.AbsRequestListener;
import gzfns.com.inventoryregulation.net.HttpResponse;
import gzfns.com.inventoryregulation.net.NetWorkHelper;

/**
 * Created by Administrator on 2018/7/20.
 */

public class LoginModel implements LoginContract.Modle {

    @Override
    public void loginOnline(String userName, String passWord, final RequestListener listener, final int requestCode) {
        Map<String, String> map = new HashMap<>();
        map.put("grant_type ", Constants.Authorize.PASSWORD);
        map.put("username", userName);
        map.put("password", passWord);
        NetWorkHelper.getNetApi().login(ApiConstant.AUTHORIZE, map).enqueue(new AbsRequestListener() {

            @Override
            public void success(HttpResponse result) {
                listener.onRequestCallBack(requestCode, true, result.getMsg(), result.getData());
            }
        });
    }
}
