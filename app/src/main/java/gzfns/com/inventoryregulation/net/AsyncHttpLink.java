package gzfns.com.inventoryregulation.net;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/1/5.
 */
public interface AsyncHttpLink{
//    @FormUrlEncoded
//    @POST("{url}")
//    Call<ResponseBody> request(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> request(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @Headers("Authorization: basic dGFjY2lzdW06YWJjZGU=")
    @POST
    Call<ResponseBody> login(@Url String url, @FieldMap Map<String, String> params);
}
