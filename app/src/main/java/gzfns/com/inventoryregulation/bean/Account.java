package gzfns.com.inventoryregulation.bean;

import gzfns.com.inventoryregulation.base.BaseEntity;

/**
 * Created by Administrator on 2018/7/23.
 */

public class Account extends BaseEntity {

    public String username;

    public String password;

    public String access_token;

    public String refresh_token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
