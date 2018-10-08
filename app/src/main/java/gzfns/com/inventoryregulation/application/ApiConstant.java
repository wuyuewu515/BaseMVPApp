package gzfns.com.inventoryregulation.application;

import gzfns.com.inventoryregulation.BuildConfig;

/**
 * Created by Administrator on 2017/2/6.
 */
public interface ApiConstant {
    /**
     * 认证、鉴权中心
     */
    String AUTH_CENTER = "auth-center/";

    /**
     * 用户认证
     */
    String AUTHORIZE = BuildConfig.BASE_URL + AUTH_CENTER + "authorize";

}
