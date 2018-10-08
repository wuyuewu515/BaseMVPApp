package gzfns.com.inventoryregulation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by user on 2018/7/25.
 */

public class PhotoPreAdapter extends PagerAdapter {
    private List<View> views;
    private Context context;

    public PhotoPreAdapter(List<View> views, Context context) {
        this.views = views;
        this.context = context;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    //展示的view
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //获得展示的view
        View view = views.get(position);
        //添加到容器
        container.addView(view);
        //返回显示的view
        return view;
    }

    //销毁view
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //从容器中移除view
        container.removeView((View) object);
    }

}
