package gzfns.com.inventoryregulation.net;

/**
 * Created by user on 2018/7/17.
 * 接口数据返回成功监听
 */

import gzfns.com.inventoryregulation.manager.AppManager;
import gzfns.com.inventoryregulation.utils.LoadingDialogUtils;

/**
 * Created by user on 2018/7/27.
 * 就是为了省写一个弹窗消失的代码
 */

public abstract class AbsRequestListener extends NetCallBack {

    @Override
    public void onSuccess(HttpResponse resultJson) {
        LoadingDialogUtils.dismiss(AppManager.currentActivity());
        success(resultJson);
    }

    public abstract void success(HttpResponse result);

    @Override
    public void onError(HttpResponse result, Throwable t) {
        super.onError(result, t);
    }
}