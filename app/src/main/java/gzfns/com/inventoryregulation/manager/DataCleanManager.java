package gzfns.com.inventoryregulation.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

import gzfns.com.inventoryregulation.utils.LogUtils;

/**
 * Created by user on 2018/7/19.
 * 缓存处理操作
 */

public class DataCleanManager {

    private static final String APP_CACAHE_DIRNAME = "/webcache";

    public static String getTotalCacheSize(Context context) throws Exception {

        //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
        long cacheSize = getFolderSize(context.getCacheDir());
        //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    public static boolean clearAllCache(Context context) {
        boolean b = deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            boolean b1 = deleteDir(context.getExternalCacheDir());

//            File databasePath = context.getDatabasePath("webview.db");
//            File databasePath1 = context.getDatabasePath("webviewCache.db");
//            if (null != databasePath) {
//                long length = databasePath.length();
//                boolean delete = databasePath.delete();
//                boolean exists = databasePath.exists();
//                LogUtils.LOG_D(DataCleanManager.class, "文件长度是：" + length);
//                LogUtils.LOG_D(DataCleanManager.class, "是否删除成功：" + delete);
//                LogUtils.LOG_D(DataCleanManager.class, "是否存在：" + exists);  //----返回是false
//            }
//            if (null != databasePath1) {
//                long length2 = databasePath1.length();
//                LogUtils.LOG_D(DataCleanManager.class, "文件长度是：" + length2);
//
//            }
            boolean b2 = context.deleteDatabase("webview.db");
            boolean b3 = context.deleteDatabase("webviewCache.db");
            b = b && b1 && b2 && b3;
        }
        return b;
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "G";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "T";
    }

    /**
     * 清除WebView缓存
     */
    public static void clearWebViewCache(Activity activity) {

        //清理Webview缓存数据库
        try {
            activity.deleteDatabase("webview.db");
            activity.deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //WebView 缓存文件
        File appCacheDir = new File(activity.getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME);
        LogUtils.LOG_D(DataCleanManager.class, "appCacheDir path=" + appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(activity.getCacheDir().getAbsolutePath() + "/webviewCache");
        LogUtils.LOG_D(DataCleanManager.class, "webviewCacheDir path=" + webviewCacheDir.getAbsolutePath());

        //删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            deleteDir(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            deleteDir(appCacheDir);
        }
    }

}
