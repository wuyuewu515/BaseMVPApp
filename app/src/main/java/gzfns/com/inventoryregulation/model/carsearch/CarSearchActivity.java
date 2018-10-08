package gzfns.com.inventoryregulation.model.carsearch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.adapter.MyCarAdatper;
import gzfns.com.inventoryregulation.base.BaseActivity;
import gzfns.com.inventoryregulation.bean.CarInfo;
import gzfns.com.inventoryregulation.model.carinfo.CarInfoActivity;
import gzfns.com.inventoryregulation.utils.ToastUtils;

/**
 * Created by user on 2018/7/23.
 * 车架搜索页面
 */

public class CarSearchActivity extends BaseActivity<CarSearchPresenter, CarSearchModel> implements CarSearchContract.View {

    @BindView(R.id.search_car)
    SearchView searchCar;
    @BindView(R.id.recycle_car)
    RecyclerView recycleCar;

    private SearchView.SearchAutoComplete inputView;
    private MyCarAdatper adatper;
    private ArrayList<CarInfo> datas = new ArrayList<>();

    @Override
    protected Integer getContentId() {
        return R.layout.mycar_search_activity;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        //searchView 的下划线
        View viewById = searchCar.findViewById(android.support.v7.appcompat.R.id.search_plate);
        if (viewById != null) {
            viewById.setBackgroundColor(Color.TRANSPARENT);
        }
        //searchView 的输入框
        inputView = searchCar.findViewById(R.id.search_src_text);
        inputView.setTextColor(getResources().getColor(R.color.text_black));
        inputView.setTextSize(getResources().getDimension(R.dimen.fontSize_5));
        String digits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        inputView.setKeyListener(DigitsKeyListener.getInstance(digits));//限制输入的内容
        inputView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)}); //限制输入的数量

        //设置搜索框的叉叉按钮
        ImageView ivView = searchCar.findViewById(R.id.search_close_btn);
        ivView.setImageDrawable(getResources().getDrawable(R.mipmap.icon_delete));
        ivView.setPadding(15, 15, 15, 15);


        recycleCar.setLayoutManager(new LinearLayoutManager(this));
        adatper = new MyCarAdatper(datas);
        adatper.setEmptyView(getEmptyView());
        recycleCar.setAdapter(adatper);
    }

    @Override
    protected void initData() {
        adatper.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object viewTag = view.getTag(R.id.id_view_tag);
                if (null != viewTag && viewTag instanceof CarInfo) {
                    CarInfo carInfo = (CarInfo) viewTag;
                    CarInfoActivity.goInto(activity, carInfo);
                }
            }
        });
    }

    @Override
    protected void initListener() {
        searchCar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ToastUtils.showShort(activity, "提交了", 0);
                mPresenter.searchData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (!TextUtils.isEmpty(newText)) {
                    mPresenter.searchData(newText);
                }
                return false;
            }
        });
    }

    public static void goInto(Activity activity) {
        Intent intent = new Intent(activity, CarSearchActivity.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.tv_cancal_car)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setAdapterData(ArrayList<CarInfo> data) {
        datas.clear();
        datas.addAll(data);
        adatper.setNewData(datas);

    }
}
