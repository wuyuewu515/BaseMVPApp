package gzfns.com.inventoryregulation.utils;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/1/20.
 */
public class GsonUtils {

    private static Gson gson;

    public synchronized static Gson getInstance(){
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }
}
