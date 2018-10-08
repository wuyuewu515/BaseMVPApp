package gzfns.com.inventoryregulation.net;


import android.app.Activity;
import android.content.Intent;
import android.view.View;

import org.apache.commons.lang3a.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.application.AppApplication;
import gzfns.com.inventoryregulation.bean.Account;
import gzfns.com.inventoryregulation.bean.ResponseInfo;
import gzfns.com.inventoryregulation.error.BalanceException;
import gzfns.com.inventoryregulation.manager.AppManager;
import gzfns.com.inventoryregulation.model.login.LoginActivity;
import gzfns.com.inventoryregulation.utils.JsonUtils;
import gzfns.com.inventoryregulation.utils.LoadingDialogUtils;
import gzfns.com.inventoryregulation.utils.LogUtils;
import gzfns.com.inventoryregulation.utils.ToastUtils;
import gzfns.com.inventoryregulation.views.EcarDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2017/1/10.
 */
public abstract class NetCallBack implements Callback<ResponseBody> {

    public NetCallBack() {
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            JSONObject jsonObject = new JSONObject(response.body().string());
            String state = jsonObject.getString("state");// 请求状态码
            String success = jsonObject.getString("code");
            int i = Integer.parseInt(success);

            if (!state.equals("0")) {// 请求状态异常
                String tip = "";
                String msg = jsonObject.getString("friendlyMsg");
                if (msg != null) {
                    tip = msg;
                }
                onError(tip, new NullPointerException());
                // 错误提示
                if (i == 101) {// 登录过期
                    outLogin(jsonObject);
                }
                return;
            }

            if (i <= 99 && i >= 0) {//访问成功
                HttpResponse httpResponse = new HttpResponse();
                httpResponse.setData(jsonObject.getString("data"));
                httpResponse.setCode(1);
                httpResponse.setMsg(jsonObject.getString("friendlyMsg"));
                onSuccess(httpResponse);

            } else if (i == -1) {// 系统异常
                //访问失败
                String msg = jsonObject.getString("friendlyMsg");
                onError(jsonObject.getString("friendlyMsg"), new NullPointerException());

            } else if (i == 101) {// 登录过期
                outLogin(jsonObject);

            } else if (i > 99) {// 业务异常
                onError(jsonObject.getString("friendlyMsg"), new NullPointerException());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onError("数据解析异常", e);
        } catch (IOException e) {
            e.printStackTrace();
            onError("I/O 异常", e);
        } catch (NullPointerException e) {
            e.printStackTrace();
            onError("返回值为空，请重试!", e);
        }
    }


    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        t.printStackTrace();
        onError("网络异常，请检查网络设置！", t);
    }

    public abstract void onSuccess(HttpResponse resultJson);

    public void onError(String msg, Throwable t) {
        ToastUtils.showShort(AppManager.currentActivity(), msg, R.mipmap.icon_fail);
    }

    public void onError(HttpResponse result, Throwable t) {
        LoadingDialogUtils.dismiss(AppManager.currentActivity());
        ToastUtils.showShort(AppManager.currentActivity(), result.getMsg(), R.mipmap.icon_fail);
    }

    private void outLogin(JSONObject jsonObject) throws JSONException {
        final Activity activity = AppManager.currentActivity();
        AppApplication.isAutoLogin = false;
        if (activity != null && !activity.isFinishing()) {
            String msg = jsonObject.getString("message");

        } else {
            AppApplication application = (AppApplication) activity.getApplication();
            application.stopUploadService();
            AppManager.finishAllActivity();
            Intent intent = new Intent(application, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            application.startActivity(intent);
        }

    }
}
