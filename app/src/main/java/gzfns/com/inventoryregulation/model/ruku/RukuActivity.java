package gzfns.com.inventoryregulation.model.ruku;

import android.app.Activity;
import android.content.Intent;
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
import gzfns.com.inventoryregulation.adapter.ListAdatper;
import gzfns.com.inventoryregulation.base.BaseActivity;
import gzfns.com.inventoryregulation.bean.RukuListInfo;
import gzfns.com.inventoryregulation.model.carsearch.CarSearchActivity;
import gzfns.com.inventoryregulation.model.rukudan.RuKuDanActivity;
import gzfns.com.inventoryregulation.utils.TabLayoutIndicatorUtils;
import gzfns.com.inventoryregulation.views.RejectDailog;
import gzfns.com.inventoryregulation.views.TitleBar;

/**
 * Created by Administrator on 2018/3/1.
 * 我的车
 */
public class RukuActivity extends BaseActivity<RukuPresenter, RukuModel> implements RukuContract.View,
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    private static String titles[] = {"正常", "临时出库"};
    //  private static String values[] = {"0", "0"};
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

    private ListAdatper adatper;

    @Override
    protected Integer getContentId() {
        return R.layout.ruku_activity_layout;
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
            tab_value.setVisibility(View.GONE);
            tab_title.setText(titles[i]);
            // tab_value.setText(values[i]);
            tabMycar.addTab(tabAt);
        }
        tabMycar.post(new Runnable() {
            @Override
            public void run() {
                TabLayoutIndicatorUtils.setIndicator(tabMycar, 13, 13);
            }
        });

        recyMycar.setLayoutManager(new LinearLayoutManager(activity));

    }

    @Override
    protected void initData() {
        mPresenter.getNormalData();
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
        CarSearchActivity.goInto(activity);
    }

    @Override
    public void setRecyData(ArrayList<RukuListInfo> datas) {

        if (null == adatper) {
            adatper = new ListAdatper(datas);
            adatper.setOnItemClickListener(this);
            adatper.setEmptyView(getEmptyView());
            adatper.setOnLoadMoreListener(this, recyMycar);
            adatper.setSelfOnclickListener(new ListAdatper.ItemOnclickListener() {
                @Override
                public void onClick(RukuListInfo info) {
                    showDialog(info);
                }
            });
            recyMycar.setAdapter(adatper);

        } else {
            adatper.setNewData(datas);
        }
    }

    /**
     * 展示驳回的原因弹窗
     *
     * @param info
     */
    private void showDialog(RukuListInfo info) {

        RejectDailog dailog = new RejectDailog(this);
        dailog.show();
        dailog.setTextStr(info.getMessage());
        dailog.setTitleStr(info.getCarCode());
    }

//    @Override
//    public void setRukuCount(String count) {
//        values[0] = count;
//        TabLayout.Tab tabAt = tabMycar.getTabAt(0);
//        TextView tab_value = tabAt.getCustomView().findViewById(R.id.tv_table_value);
//        tab_value.setText(values[0]);
//    }

//    @Override
//    public void setChukuCount(String count) {
//        values[1] = count;
//        TabLayout.Tab tabAt = tabMycar.getTabAt(1);
//        TextView tab_value = tabAt.getCustomView().findViewById(R.id.tv_table_value);
//        tab_value.setText(values[1]);
//    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        Object viewTag = view.getTag(R.id.id_view_tag);
        if (null != viewTag && viewTag instanceof RukuListInfo) {
            RukuListInfo carInfo = (RukuListInfo) viewTag;
            RuKuDanActivity.goTo(activity,carInfo);
        }
    }


    @Override
    public void onLoadMoreRequested() {

        if (tabMycar.getSelectedTabPosition() == 0) {
            mPresenter.getTempDatas();
        } else {
            mPresenter.getNormalData();
        }
    }


    public static void goInto(Activity activity) {
        Intent intent = new Intent(activity, RukuActivity.class);

        activity.startActivity(intent);
    }
}
