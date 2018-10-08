package gzfns.com.inventoryregulation.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gzfns.com.inventoryregulation.R;

/**
 * Created by user on 2018/7/19.
 */

public class CommonDailog extends Dialog {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.tv_content)
    TextView tv_content;

    @BindView(R.id.confirm)
    TextView tv_confirm;

    @BindView(R.id.cancel)
    TextView tv_cancel;


    private Activity context;
    private String title;
    private String content;

    private DialogListener dialogListener;


    public CommonDailog(Activity context, String title, String content) {
        super(context, R.style.UrgentDialog);
        this.context = context;
        this.title = title;
        this.content = content;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        setCanceledOnTouchOutside(false);// 按空白不能隐藏dialog
        ButterKnife.bind(this);
        title_tv.setText(title);
        initView();
        initData();
    }

    private void initView() {
        WindowManager wm = context.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        Window win = this.getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = (int) (width * 0.85);

        win.setGravity(Gravity.CENTER);

    }

    private void initData() {

        title_tv.setText(title);
        tv_content.setText(content);

    }


    @OnClick({R.id.confirm, R.id.cancel})
    public void viewOnclick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if (null != dialogListener)
                    dialogListener.confrom();
                break;
            case R.id.cancel:
                if (null != dialogListener)
                    dialogListener.cancle();
                break;
        }
        dismiss();
    }

    /**
     * 确定文字描述
     *
     * @param str
     */
    public void setConfirmStr(String str) {
        tv_confirm.setText(str);
    }

    /**
     * 取消文字描述
     *
     * @param str
     */
    public void setCancelStr(String str) {
        tv_cancel.setText(str);
    }

    /**
     * 设置弹窗内容
     *
     * @param text
     */
    public void setTextStr(String text) {
        if (TextUtils.isEmpty(text))
            return;
        tv_content.setText(text);
    }

    /**
     * 设置弹窗标题的内容
     *
     * @param title
     */
    public void setTitleStr(String title) {
        if (TextUtils.isEmpty(title)) {
            title_tv.setVisibility(View.GONE);
        } else
            title_tv.setText(title);
    }

    public CommonDailog setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
        return this;
    }

    public interface DialogListener {
        void confrom();

        void cancle();
    }
}
