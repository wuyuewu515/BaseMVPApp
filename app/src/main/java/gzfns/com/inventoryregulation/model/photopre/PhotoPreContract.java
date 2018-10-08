package gzfns.com.inventoryregulation.model.photopre;

import android.content.Intent;

import java.util.ArrayList;

import gzfns.com.inventoryregulation.base.BaseModel;
import gzfns.com.inventoryregulation.base.BasePresenter;
import gzfns.com.inventoryregulation.base.BaseView;

/**
 * Created by user on 2018/7/25.
 */

public interface PhotoPreContract {

    interface View extends BaseView {
        /**
         * 设置并且展示选中的照片
         * @param datas
         * @param position 位置
         */
       void setViewData(ArrayList<String> datas,int position);
    }

    interface Model extends BaseModel {
    }

     abstract class Presenter extends BasePresenter<View, Model> {
        abstract void handleIntent(Intent intent);
    }
}
