package gzfns.com.inventoryregulation.utils;


import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

import gzfns.com.inventoryregulation.R;

/**
 * 仿toast的弹框
 */
public class ToastDialog extends Dialog {
    private Activity context;
    private Timer timer;

    public static final int LONG_SHOW = 0x00001111;
    public static final int SHORT_SHOW = 0x00002222;

    private int showModel = SHORT_SHOW;
    private ImageView imageView_icon;
    private int layoutRes = 0;
    private View contentView;
    private TextView textView_msg;

    public ToastDialog(Activity context) {
        super(context, R.style.selectorDialog);
        this.context = context;
        timer = new Timer();
        if (layoutRes == 0) {
            //默认页面
            setContentView(R.layout.dialog_toast);
            initView();
        } else {
            //指定页面
            contentView = LayoutInflater.from(context).inflate(layoutRes, null, false);
        }
        //设置dialog不获取焦点
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

    }

    private void initView() {
        imageView_icon = (ImageView) findViewById(R.id.imageView_Icon);
        textView_msg = (TextView) findViewById(R.id.textView_msg);
    }

    /**
     * 设置图标
     *
     * @param imgRes
     */
    public void setIcon(int imgRes) {
        if (imageView_icon != null) {
            imageView_icon.setImageResource(imgRes);
        }
    }

    /**
     * 设置提示语
     * @param msg
     */
    public void setMsg(String msg){
        if(textView_msg != null){
            textView_msg.setText(msg);
        }
    }

    public void setShowModel(int showModel) {
        this.showModel = showModel;
    }

    @Override
    public void show() {
        super.show();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(context != null && !context.isFinishing() && ToastDialog.this != null && ToastDialog.this.isShowing()){
                    dismiss();
                }
                timer = null;
            }
        }, showModel == SHORT_SHOW ? 2000 : 3000);
        setCanceledOnTouchOutside(false);
    }

    public View getView() {
        return contentView;
    }

    public Activity getActivity(){
        return context;
    }

}
