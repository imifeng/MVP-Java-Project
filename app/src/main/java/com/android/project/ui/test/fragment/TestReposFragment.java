package com.android.project.ui.test.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.project.R;
import com.android.project.base.BaseMvpFragment;
import com.android.project.common.Constant;
import com.android.project.mvp.contract.TestContract;
import com.android.project.mvp.model.bean.RepoBean;
import com.android.project.mvp.presenter.TestPresenter;
import com.android.project.utils.GsonUtils;
import com.android.project.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class TestReposFragment extends BaseMvpFragment<TestContract.View, TestPresenter> implements TestContract.View {

    @BindView(R.id.et_username)
    EditText etName;
    @BindView(R.id.tv_repos_btn)
    TextView tvReposBtn;

    @Override
    public TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_repos;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String testStr = bundle.getString(Constant.EXTRA_ACTION_TEST);
            ToastUtils.showToast(testStr);
        }
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        setOnClickListener(tvReposBtn, view -> {
            if (getUsername().isEmpty()) {
                ToastUtils.showToast(getString(R.string.repos_text_hint_username));
                return;
            }
            getPresenter().getRepos(getUsername());
        });
    }

    /**
     * @return Username
     */
    private String getUsername() {
        return etName.getText().toString().trim();
    }

    @Override
    public void onReposSuccess(List<RepoBean> response) {
        ToastUtils.showToast("getRepos Successful:"  + ":" + GsonUtils.get().toJson(response));

    }
}
