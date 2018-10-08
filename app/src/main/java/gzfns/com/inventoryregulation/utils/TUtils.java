package gzfns.com.inventoryregulation.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Administrator on 2016/9/5.
 */
public class TUtils {
    /**
     * 获取对象中设置的泛型并根据索引创建对象
     *
     * @param o
     * @param i
     * @param <T>
     * @return
     */
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass()))
                    .getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据类名创建对象
     *
     * @param className
     * @return
     */
    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
