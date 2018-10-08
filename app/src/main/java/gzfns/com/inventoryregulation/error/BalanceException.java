package gzfns.com.inventoryregulation.error;

/**
 * Created by Ly on 2017/12/8.
 */
public class BalanceException extends Exception {
    public String msg;
    public BalanceException(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
