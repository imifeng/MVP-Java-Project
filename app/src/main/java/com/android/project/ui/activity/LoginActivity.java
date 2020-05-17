package com.android.project.ui.activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.project.R;
import com.android.project.base.BaseMvpActivity;
import com.android.project.mvp.contract.LoginContract;
import com.android.project.mvp.model.bean.User;
import com.android.project.mvp.presenter.LoginPresenter;
import com.android.project.utils.DisplayCutoutUtils;
import com.android.project.utils.ToastUtils;

import butterknife.BindView;

/**
 * this is a example
 */
public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_username)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login_btn)
    TextView tvLoginBtn;


    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();

        DisplayCutoutUtils.adaptStatusBarHeight(this, statusBar);
    }


    @Override
    protected void initEvent() {
        super.initEvent();
        setOnClickListener(ivBack, view -> {
            finish();
        });


        setOnClickListener(tvLoginBtn, view -> {
            if (getUsername().isEmpty() || getPassword().isEmpty()) {
                ToastUtils.showToast("帐号密码不能为空");
                return;
            }
            getPresenter().login(getUsername(), getPassword());
        });
    }

    @Override
    protected void onBack() {
        finish();
    }

    /**
     * @return 帐号
     */
    private String getUsername() {
        return etName.getText().toString().trim();
    }

    /**
     * @return 密码
     */
    private String getPassword() {
        return etPassword.getText().toString().trim();
    }


    @Override
    public void onLoginSuccess(User bean) {

        ToastUtils.showToast("Login Successful:" + bean.getId());
    }

}
