package gzfns.com.inventoryregulation.listener;

/**
 * 请求回调
 */
public interface RequestListener {
    /**
     * 请求回调
     *
     * @param requestCode 请求code码
     * @param success
     * @param msg
     * @param data
     */
    void onRequestCallBack(Integer requestCode, boolean success, String msg, Object data);


}
