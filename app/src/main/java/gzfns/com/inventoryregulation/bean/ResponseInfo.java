package gzfns.com.inventoryregulation.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by user on 2018/7/17.
 * 回调返回的总结果
 */

public class ResponseInfo implements Serializable {
//              "state": 1,
//             "code": "1001",
//             "msg": "user authorize error",
//             "friendlyMsg": "nullIncorrect authorization method.",
//             "data": null,
//             "stackTrace": "check log for details"

    @Expose
    protected int state;
    @Expose
    protected String code;
    @Expose
    protected String msg;
    @Expose
    protected String friendlyMsg;
    @Expose
    protected String stackTrace;

    public int getState() {
        return state;
    }

    public ResponseInfo setState(int state) {
        this.state = state;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResponseInfo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getFriendlyMsg() {
        return friendlyMsg;
    }

    public ResponseInfo setFriendlyMsg(String friendlyMsg) {
        this.friendlyMsg = friendlyMsg;
        return this;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public ResponseInfo setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }
}
