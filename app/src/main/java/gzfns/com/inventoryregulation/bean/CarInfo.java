package gzfns.com.inventoryregulation.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by user on 2018/7/20.
 * 我的车辆实体类
 */

public class CarInfo implements Serializable {

    @Expose
    protected String carName; //品牌
    @Expose
    protected String carCode;//车架号
    /**
     * 出入库状态  2--出库  1 --入库
     */
    @Expose
    protected int carStatus;//状态

    @Expose
    protected String obdCode = "赣10000";

    @Expose
    protected String carType = "拖拉机A系列";

    @Expose
    protected  String time ="2018-8-8";

    /**
     *
     * @param carName
     * @param carCode
     * @param carStatus  1--在库  2--出库
     */
    public CarInfo(String carName, String carCode, int carStatus) {
        this.carName = carName;
        this.carCode = carCode;
        this.carStatus = carStatus;
    }

    public String getTime() {
        return time;
    }

    public CarInfo setTime(String time) {
        this.time = time;
        return this;
    }

    public String getObdCode() {
        return obdCode;
    }

    public CarInfo setObdCode(String obdCode) {
        this.obdCode = obdCode;
        return this;
    }

    public String getCarType() {
        return carType;
    }

    public CarInfo setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public String getCarName() {
        return carName;
    }

    public CarInfo setCarName(String carName) {
        this.carName = carName;
        return this;
    }

    public String getCarCode() {
        return carCode;
    }

    public CarInfo setCarCode(String carCode) {
        this.carCode = carCode;
        return this;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public CarInfo setCarStatus(int carStatus) {
        this.carStatus = carStatus;
        return this;
    }
}
