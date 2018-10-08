package gzfns.com.inventoryregulation.utils;

import android.app.Activity;

/**
 * Created by Administrator on 2017/10/13.
 */
public class LoadingDialogUtils {

    private static LoaddingDialog dialog;

    public static void show(Activity activity, String msg) {
        if (activity.isFinishing()) return;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new LoaddingDialog(activity, msg);
        dialog.show();
    }

    public static void show(Activity activity) {
        show(activity, "加载中...");
    }

    public static void dismiss(Activity activity) {
        if (activity.isFinishing()) return;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
