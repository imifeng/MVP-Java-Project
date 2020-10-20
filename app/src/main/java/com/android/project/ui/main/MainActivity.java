package com.android.project.ui.main;

import android.content.Intent;
import android.widget.Button;

import com.android.project.R;
import com.android.project.base.BaseMvpActivity;
import com.android.project.base.BasePresenter;
import com.android.project.ui.repos.ReposActivity;
import com.android.project.ui.test.TestFragmentActivity;

import butterknife.BindView;


public class MainActivity extends BaseMvpActivity {

    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.btn_repos)
    Button btnRepos;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();

        setOnClickListener(btnTest, view -> {
            //goto Login
            Intent it = new Intent(this, TestFragmentActivity.class);
            startActivity(it);
        });
        setOnClickListener(btnRepos, view -> {
            //goto Login
            Intent it = new Intent(this, ReposActivity.class);
            startActivity(it);
        });
    }

    @Override
    protected void onBack() {
        finish();
    }

}
