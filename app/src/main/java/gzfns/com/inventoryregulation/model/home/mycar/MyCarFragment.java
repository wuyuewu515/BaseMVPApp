package gzfns.com.inventoryregulation.model.home.mycar;

import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.adapter.MyCarAdatper;
import gzfns.com.inventoryregulation.base.BaseFragment;
import gzfns.com.inventoryregulation.bean.CarInfo;
import gzfns.com.inventoryregulation.model.carinfo.CarInfoActivity;
import gzfns.com.inventoryregulation.model.carsearch.CarSearchActivity;
import gzfns.com.inventoryregulation.utils.TabLayoutIndicatorUtils;
import gzfns.com.inventoryregulation.views.TitleBar;

/**
 * Created by Administrator on 2018/3/1.
 * 我的车
 */
public class MyCarFragment extends BaseFragment<MyCarPresenter, MyCarModel> implements MyCarContract.View,
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    private static String titles[] = {"出库", "在库"};
    private static String values[] = {"0", "0"};
    @BindView(R.id.titlebar_myCar)
    TitleBar titlebarMyCar;
    @BindView(R.id.card_serach_mycar)
    CardView cardSerachMycar;
    @BindView(R.id.tab_mycar)
    TabLayout tabMycar;
    @BindView(R.id.mycar_mycar)
    RecyclerView recyMycar;
    @BindView(R.id.reflesh_mycar)
    SwipeRefreshLayout refleshMycar;

    private MyCarAdatper adatper;

    @Override
    protected Integer getContentId() {
        return R.layout.mycar_fragment_layout;
    }

    @Override
    protected void initView() {
        //初始化tablayout
        tabMycar.removeAllTabs();
        for (int i = 0, length = titles.length; i < length; i++) {
            TabLayout.Tab tabAt = tabMycar.newTab();
            tabAt.setCustomView(R.layout.item_tab_mycar);
            TextView tab_title = tabAt.getCustomView().findViewById(R.id.tv_table_title);
            TextView tab_value = tabAt.getCustomView().findViewById(R.id.tv_table_value);
            tab_title.setText(titles[i]);
            tab_value.setText(values[i]);
            tabMycar.addTab(tabAt);
        }
        tabMycar.post(new Runnable() {
            @Override
            public void run() {
                TabLayoutIndicatorUtils.setIndicator(tabMycar, 13, 13);
            }
        });

        recyMycar.setLayoutManager(new LinearLayoutManager(mActivity));

    }

    @Override
    protected void initData() {
        titlebarMyCar.setLeftIcon(0);
        mPresenter.getRukuDatas();

    }

    @Override
    protected void initListener() {
        refleshMycar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.reflushData(tabMycar.getSelectedTabPosition());
                refleshMycar.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refleshMycar.setRefreshing(false);
                    }
                }, 1500);
            }
        });
        tabMycar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPresenter.reflushData(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @OnClick(R.id.card_serach_mycar)
    public void onViewClicked() {
        CarSearchActivity.goInto(mActivity);
    }

    @Override
    public void setRecyData(ArrayList<CarInfo> datas) {

        if (null == adatper) {
            adatper = new MyCarAdatper(datas);
            adatper.setOnItemClickListener(this);
            adatper.setEmptyView(getEmptyView());
            adatper.setOnLoadMoreListener(this, recyMycar);
            recyMycar.setAdapter(adatper);
        } else {
            adatper.setNewData(datas);
        }
    }

    @Override
    public void setRukuCount(String count) {
        values[0] = count;
        TabLayout.Tab tabAt = tabMycar.getTabAt(0);
        TextView tab_value = tabAt.getCustomView().findViewById(R.id.tv_table_value);
        tab_value.setText(values[0]);
    }

    @Override
    public void setChukuCount(String count) {
        values[1] = count;
        TabLayout.Tab tabAt = tabMycar.getTabAt(1);
        TextView tab_value = tabAt.getCustomView().findViewById(R.id.tv_table_value);
        tab_value.setText(values[1]);


    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Object viewTag = view.getTag(R.id.id_view_tag);
        if (null != viewTag && viewTag instanceof CarInfo) {
            CarInfo carInfo = (CarInfo) viewTag;
            CarInfoActivity.goInto(mActivity, carInfo);
        }
    }


    @Override
    public void onLoadMoreRequested() {

        if (tabMycar.getSelectedTabPosition() == 0) {
            mPresenter.getChukuDatas();
        } else {
            mPresenter.getRukuDatas();
        }


    }
}
