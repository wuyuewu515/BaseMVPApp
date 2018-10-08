package gzfns.com.inventoryregulation.model.login;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import gzfns.com.inventoryregulation.R;
import gzfns.com.inventoryregulation.base.BaseActivity;
import gzfns.com.inventoryregulation.base.BasePresenter;
import gzfns.com.inventoryregulation.model.home.MainActivity;

/**
 * Created by user on 2018/7/18.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    @BindView(R.id.editText_UserName)
    EditText editText_UserName;

    @BindView(R.id.editText_PassWord)
    EditText editText_PassWord;

    @Override
    public BaseActivity getMyActivity() {
        return this;
    }

    @Override
    protected Integer getContentId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.textView_Login})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.textView_Login:
                MainActivity.goInto(this);
//                mPresenter.loginOnline();

                break;
        }
    }

    public static void into(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public String getUserName() {
        return editText_UserName.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return editText_PassWord.getText().toString().trim();
    }
}
