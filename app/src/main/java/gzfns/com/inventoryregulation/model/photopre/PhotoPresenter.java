package gzfns.com.inventoryregulation.model.photopre;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import gzfns.com.inventoryregulation.utils.JsonUtils;

/**
 * Created by user on 2018/7/25.
 */

public class PhotoPresenter extends PhotoPreContract.Presenter {
    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void onStart() {

    }

    @Override
    void handleIntent(Intent intent) {

        datas.clear();
        String jsonStr = intent.getStringExtra("jsonStr");
        List<String> data = JsonUtils.json2arr(jsonStr);
        datas.addAll(data);
        int position = intent.getIntExtra("position", 0);
        mView.setViewData(datas, position);

    }
}
