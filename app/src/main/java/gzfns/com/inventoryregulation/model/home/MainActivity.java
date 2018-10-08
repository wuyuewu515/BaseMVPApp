package gzfns.com.inventoryregulation.model.home;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.adapter.MainPagerAdapter;
import gzfns.com.inventoryregulation.base.BaseActivity;
import gzfns.com.inventoryregulation.model.home.main.HomeFragment;
import gzfns.com.inventoryregulation.model.home.mycar.MyCarFragment;
import gzfns.com.inventoryregulation.model.home.personal.PersonalFragment;

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.home)
    RadioButton home;
    @BindView(R.id.myCar)
    RadioButton myCar;
    @BindView(R.id.personal)
    RadioButton personal;
    @BindView(R.id.main_btngroup)
    RadioGroup mainBtngroup;


    private ArrayList<Fragment> fragments;

    @Override
    protected Integer getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MyCarFragment());
        fragments.add(new PersonalFragment());
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(adapter);
        vpMain.setOffscreenPageLimit(fragments.size() - 1);
        mainBtngroup.check(R.id.home);

    }


    @OnClick({R.id.home, R.id.myCar, R.id.personal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home: //首页
                vpMain.setCurrentItem(0);
                break;
            case R.id.myCar://我的车辆
                vpMain.setCurrentItem(1);
                break;
            case R.id.personal://个人中心
                vpMain.setCurrentItem(2);
                break;
        }

    }


    @Override
    protected void initListener() {
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        mainBtngroup.check(R.id.home);
                        break;
                    case 1:
                        mainBtngroup.check(R.id.myCar);
                        break;
                    case 2:
                        mainBtngroup.check(R.id.personal);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public static void goInto(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

}
