package gzfns.com.inventoryregulation.utils;

import android.app.Activity;


/**
 * 自定义弹窗工具
 */
public class ToastUtils {

    private static ToastDialog dialog;

    /**
     * 短时间弹出
     *
     * @param activity
     * @param msg
     * @param imgRes
     */
    public static void showShort(Activity activity, String msg, int imgRes) {
        if (dialog != null && dialog.isShowing()) {
            dissToast();
        }
        if(activity != null && !activity.isFinishing()){
            dialog = new ToastDialog(activity);
            dialog.setMsg(msg);
            dialog.setIcon(imgRes);
            dialog.setShowModel(ToastDialog.SHORT_SHOW);
            try{
                dialog.show();
            }catch (Error e){
            }
        }
    }

    /**
     * 长时间弹出
     *
     * @param activity
     * @param msg
     * @param imgRes
     */
    public static void showLong(Activity activity, String msg, int imgRes) {
        if (dialog != null && dialog.isShowing()) {
            dissToast();
        }
        if(activity != null && !activity.isFinishing()){
            dialog = new ToastDialog(activity);
            dialog.setMsg(msg);
            dialog.setIcon(imgRes);
            dialog.setShowModel(ToastDialog.LONG_SHOW);
            try{
                dialog.show();
            }catch (Error e){
            }
        }
    }

    public static void dissToast() {
        if (dialog != null && dialog.isShowing() && dialog.getActivity() != null && !dialog.getActivity().isFinishing()) {
            try{
                dialog.dismiss();
            }catch (Error e){
            }finally {
                dialog = null;
            }
        }else{
            dialog = null;
        }
    }
}
