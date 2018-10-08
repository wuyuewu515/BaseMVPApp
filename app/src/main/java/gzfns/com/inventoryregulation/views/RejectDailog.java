package gzfns.com.inventoryregulation.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
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
 * 驳回报告弹窗
 */

public class RejectDailog extends Dialog {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.tv_content)
    TextView tv_content;

    @BindView(R.id.confirm)
    TextView tv_confirm;


    private Activity context;
    private String title;
    private String content;

    private DialogListener dialogListener;

    public RejectDailog(Activity context) {
        super(context);
        this.context = context;
    }

    public RejectDailog(Activity context, String title, String content) {
        super(context, R.style.UrgentDialog);
        this.context = context;
        this.title = title;
        this.content = content;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reject_layout);
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


    @OnClick({R.id.confirm})
    public void viewOnclick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if (null != dialogListener)
                    dialogListener.confrom();
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
     * 设置弹窗内容
     *
     * @param text
     */
    public void setTextStr(String text) {
        if (TextUtils.isEmpty(text))
            return;

        Spannable string = new SpannableString("驳回原因:" + text);
        // 粗体
        string.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv_content.setText(string);


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

    public RejectDailog setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
        return this;
    }

    public interface DialogListener {
        void confrom();

        void cancle();
    }
}
