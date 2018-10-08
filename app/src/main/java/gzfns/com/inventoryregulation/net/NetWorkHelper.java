package gzfns.com.inventoryregulation.net;

import android.content.Context;


import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import gzfns.com.inventoryregulation.BuildConfig;
import gzfns.com.inventoryregulation.manager.AppManager;
import gzfns.com.inventoryregulation.utils.LoadingDialogUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/1/10.
 */
public class NetWorkHelper {

    private static AsyncHttpLink netApi;
    private static OkHttpClient mOkHttpClient;


    /**
     * 初始化okHttp
     */
    private static void initOkHttpClient(Context context) {
        if (mOkHttpClient == null) {
            synchronized (NetWorkHelper.class) {
                if (mOkHttpClient == null) {
                    X509TrustManager xtm = null;
                    try {
                        xtm = new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(X509Certificate[] chain, String authType) {
                            }

                            @Override
                            public void checkServerTrusted(X509Certificate[] chain, String authType) {
                            }

                            @Override
                            public X509Certificate[] getAcceptedIssuers() {
                                X509Certificate[] x509Certificates = new X509Certificate[0];
                                return x509Certificates;
                            }
                        };
                        //SSL证书校验
//                        SSLContext sslContext = null;
//                        try {
//                            sslContext = SSLContext.getInstance("SSL");
//
//                            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
//
//                        } catch (NoSuchAlgorithmException e) {
//                            e.printStackTrace();
//                        } catch (KeyManagementException e) {
//                            e.printStackTrace();
//                        }

                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
                        SSLSocketFactory socketFactory = sslContext.getSocketFactory();


                        //接受任何证书，即使是错误证书
                        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
                            @Override
                            public boolean verify(String hostname, SSLSession session) {
                                return true;
                            }
                        };

                        mOkHttpClient = new OkHttpClient.Builder()
                                .retryOnConnectionFailure(false)//失败后不重试
                                .addInterceptor(new HttpLoggingInterceptor()
                                        .setLevel(BuildConfig.DEBUG ?
                                                HttpLoggingInterceptor.Level.BODY
                                                : HttpLoggingInterceptor.Level.NONE)) // 日志打印拦截器
                                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间
                                .sslSocketFactory(socketFactory)
                                .hostnameVerifier(DO_NOT_VERIFY)
                                .build();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 初始化网络访问帮助类
     */
    public static void initHttpHelper(Context context) {
        initOkHttpClient(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(mOkHttpClient)
                .build();
        netApi = retrofit.create(AsyncHttpLink.class);
    }

    /**
     * 获取访问Api---默认需要loading提示
     */
    public static AsyncHttpLink getNetApi() {
        return getNetApi(true);
    }

    /**
     * 获取访问Api
     *
     * @param needToast 是否需要loading提示
     * @return
     */
    public static AsyncHttpLink getNetApi(boolean needToast) {
        if (needToast) {
            LoadingDialogUtils.show(AppManager.currentActivity());
        }
        return netApi;
    }
}
