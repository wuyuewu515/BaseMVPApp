package gzfns.com.inventoryregulation.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by user on 2018/7/20.
 * 入库清单列表信息实体类
 */

public class RukuListInfo extends CarInfo {

    /**
     * 驳回信息
     */
    @Expose
    protected String message ="啦啦啦啦啦啦啦";

    /**
     * 驳回时间
     */
    @Expose
    protected String rejectTime="2018-7-23";

    /**
     * @param carName
     * @param carCode
     * @param carStatus 1--在库  2--出库
     */
    public RukuListInfo(String carName, String carCode, int carStatus) {
        super(carName, carCode, carStatus);

    }

    public String getMessage() {
        return message;
    }

    public RukuListInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getRejectTime() {
        return rejectTime;
    }

    public RukuListInfo setRejectTime(String rejectTime) {
        this.rejectTime = rejectTime;
        return this;
    }

    public String getTime() {
        return time;
    }

    public RukuListInfo setTime(String time) {
        this.time = time;
        return this;
    }

    public String getObdCode() {
        return obdCode;
    }

    public RukuListInfo setObdCode(String obdCode) {
        this.obdCode = obdCode;
        return this;
    }

    public String getCarType() {
        return carType;
    }

    public RukuListInfo setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public String getCarName() {
        return carName;
    }

    public RukuListInfo setCarName(String carName) {
        this.carName = carName;
        return this;
    }

    public String getCarCode() {
        return carCode;
    }

    public RukuListInfo setCarCode(String carCode) {
        this.carCode = carCode;
        return this;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public RukuListInfo setCarStatus(int carStatus) {
        this.carStatus = carStatus;
        return this;
    }
}
