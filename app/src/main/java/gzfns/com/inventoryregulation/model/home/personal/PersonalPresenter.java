package gzfns.com.inventoryregulation.model.home.personal;

import gzfns.com.inventoryregulation.manager.DataCleanManager;

/**
 * Created by user on 2018/7/19.
 */

public class PersonalPresenter extends PersonalContract.Presenter {
    @Override
    protected void onStart() {

    }

    @Override
    void clearCache() {

        boolean flag = DataCleanManager.clearAllCache(mView.getMyActivity());
        DataCleanManager.clearWebViewCache(mView.getMyActivity());
        if (flag) {
            mView.showToast("缓存已清除");
            getCache();
        }
    }

    @Override
    void getCache() {
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(mView.getMyActivity());
            mView.setCacheSize(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void loginOut() {
        mView.showToast("成功退出登录");
    }
}
