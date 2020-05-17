package com.android.project.ui.activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.project.R;
import com.android.project.base.BaseMvpActivity;
import com.android.project.mvp.contract.ReposContract;
import com.android.project.mvp.model.bean.RepoBean;
import com.android.project.mvp.presenter.ReposPresenter;
import com.android.project.net.gson.GsonSerializer;
import com.android.project.utils.DisplayCutoutUtils;
import com.android.project.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class ReposActivity extends BaseMvpActivity<ReposContract.View, ReposPresenter> implements ReposContract.View {

    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_username)
    EditText etName;
    @BindView(R.id.tv_repos_btn)
    TextView tvReposBtn;


    @Override
    public ReposPresenter createPresenter() {
        return new ReposPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repos;
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

        setOnClickListener(tvReposBtn, view -> {
            if (getUsername().isEmpty()) {
                ToastUtils.showToast(getString(R.string.repos_text_hint_username));
                return;
            }
            getPresenter().getRepos(getUsername());
        });
    }

    @Override
    protected void onBack() {
        finish();
    }

    /**
     * @return Username
     */
    private String getUsername() {
        return etName.getText().toString().trim();
    }

    @Override
    public void onReposSuccess(List<RepoBean> response) {
        ToastUtils.showToast("getRepos Successful:"  + ":" + GsonSerializer.get().toJson(response));

    }

}
