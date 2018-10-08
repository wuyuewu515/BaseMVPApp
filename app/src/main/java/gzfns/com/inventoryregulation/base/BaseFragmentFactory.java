package gzfns.com.inventoryregulation.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/18.
 */
public abstract class BaseFragmentFactory {

    protected static Map<Integer,BaseFragment> map = new HashMap<>();
    /**
     * 创建所有fragment
     *
     * @param list
     */
    public void createAll(List list) {
        for (int i = 0; i < list.size(); i++) {
            createOrGetPictureFragment(i);
        }
    }
    /**
     * 创建所有fragment
     *
     * @param count
     */
    public void createAll(Integer count) {
        for (int i = 0; i < count ; i++) {
            createOrGetPictureFragment(i);
        }
    }

    /**
     * 根据传入的class获取到指定PictureFragment的索引
     */
    public Integer getFragmentIndex(Class cls) {
        Integer index = -1;
        for (Map.Entry entry : map.entrySet()) {
            if (cls.equals(entry.getValue().getClass())) {
                index = (Integer) entry.getKey();
            }
        }
        return index;
    }

    public abstract BaseFragment createOrGetPictureFragment(Integer position);
}
