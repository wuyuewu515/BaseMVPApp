package gzfns.com.inventoryregulation.net;

/**
 * Created by Administrator on 2017/1/10.
 */
public class HttpResponse {
    public int success;//标志码
    private String data;//数据
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return success;
    }

    public void setCode(int code) {
        this.success = code;
    }
}
