package gzfns.com.inventoryregulation.application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/10.
 */
public class AppConfig {

    public static String THIRD_ID;
    public static String FIRST_ID;
    public static String SECOND_ID;
    public static String FUll_NAME;

    private static Context mContext;

    /**
     * 当版本号无法获取时的默认版本号
     */
    public final static String version_defult = "";

    /**
     * 缓存文件后缀
     */
    public final static String tempFileSuffex = ".temp";
    /**
     * 存储卡根目录
     */
    public static String EXTERNALSTORAGEDIR = Environment.getExternalStorageDirectory().getPath();

    /**
     * 程序使用的目录
     */
    public final static String DIR_APP = "cardealer";

    /**
     * 数据库名称
     */
    public static final String DBNAME = "clear.db";

    /**
     * 日志保存目录
     */
    private final static String DIR_LOG = "log";

    /**
     * 订单文件目录
     */
    private final static String CARTRADE_DIR = "trade";

    /**
     * 图片下载目录
     */
    private static final String DOWNLOAD_PICTURE_DIR = "download";

    public static final String WECHAT_APP_ID = "wxca297cd5d1dd2795";

    // 商户号
    public static final String PARENT_ID = "1492870212";

    public static String dversion = "2.0.0";
    public static String VIEW_DVERSION = "2.0.0.I321";

    public static void initConfig(Context context) {
        mContext = context;
        createAppDir();
    }

    /**
     * 订单文件保存目录
     *
     * @return
     */
    public static String getCarTradeFileDir(String tradeGid) {
        return String.format("%s%s", getAppStorageRootDir() + CARTRADE_DIR + File.separator,
                tradeGid + File.separator);
    }

    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return version_defult;
        }
    }

    /**
     * 获取程序存储文件的根目录
     *
     * @return
     */
    public static String getAppStorageRootDir() {
        return EXTERNALSTORAGEDIR + File.separator + DIR_APP + File.separator;
    }

    private static void createAppDir() {
        //检查日志文件目录是否存在
        File logDir = new File(getLogDir());
        if (!logDir.exists()) {
            boolean mkdirs = logDir.mkdirs();
            System.out.println("create----->" + mkdirs);
        }
        File nomedia = new File(EXTERNALSTORAGEDIR + File.separator + DIR_APP, ".nomedia");
        try {
            nomedia.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取日志文件存取目录
     */
    public static String getLogDir() {
        return EXTERNALSTORAGEDIR + File.separator + DIR_APP + File.separator + DIR_LOG;
    }

    public static String getDownloadDir() {
        return getAppStorageRootDir() + DOWNLOAD_PICTURE_DIR + File.separator;
    }

    public static String getDownloadPictureDir(String sysno) {
        return getDownloadDir() + sysno + File.separator;
    }

    /**
     * 获取文件名
     *
     * @param type 类型 1图片 2视频
     * @param sn   文件序号
     * @return
     */
    public static String getFilePathNotTemp(String gid, int type, int sn) {
        String fileName = null;
        if (type == 2)
            fileName = String.format("%s_%s.mp4", gid, "v");
        else
            fileName = String.format("%s_%s.jpg", gid, sn);
        if (type != 2) {
            return String.format("%s%s", AppConfig.getCarTradeFileDir(gid), fileName);
        } else {
            return String.format("%s%s%s", AppConfig.getCarTradeFileDir(gid), fileName, tempFileSuffex);
        }
    }

    /**
     * 获取文件路径名
     *
     * @param type 类型 1图片 2视频
     * @param sn   文件序号
     * @return
     */
    public static String getFilePathTemp(String gid, int type, int sn) {
        String fileName = null;
        if (type == 2)
            fileName = String.format("%s_%s.mp4", gid, sn);
        else
            fileName = String.format("%s_%s.jpg", gid, sn);

        return String.format("%s%s%s", AppConfig.getCarTradeFileDir(gid), fileName, tempFileSuffex);
    }


    public static String getFileUploadFileName(String tid, String gid, int sn, int fileType) {
        if (fileType == 1) {// 图片
            return String.format("%s/%s_%s", tid, gid, sn) + (".jpg");
        } else {
            return String.format("%s/%s_%s", tid, gid, "v") + (".mp4");
        }
    }

    /**
     * 获取文件在阿里云的储存路径
     *
     * @param tradeId
     * @param aliyunPath
     * @return
     */
    public static String getAliyunFileAssessUrl(String tradeId, String aliyunPath, String imageAcessUrl) {
        return String.format("%s/%s", imageAcessUrl, aliyunPath);
    }


    /**
     * 在库
     */
    public static int CAR_STATUS_ZAIKU = 1;
    /**
     * 出库
     */
    public static int CAR_STATUS_CHUKU = 2;
    /**
     * 正常
     */
    public static int CAR_STATUS_ZHENGCHANG = 3;
    /**
     * 临时出库
     */
    public static int CAR_STATUS_LINSHICHUKU = 4;
    /**
     * 驳回
     */
    public static int CAR_STATUS_BOHUI = 5;
}
