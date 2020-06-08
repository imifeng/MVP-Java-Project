package com.android.project.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.project.R;
import com.android.project.base.BaseFragment;
import com.android.project.base.BaseMvpActivity;
import com.android.project.base.BasePresenter;
import com.android.project.common.Constant;
import com.android.project.ui.fragment.TestReposFragment;
import com.android.project.utils.DisplayCutoutUtils;

import butterknife.BindView;

public class TestFragmentActivity extends BaseMvpActivity {

    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment_test;
    }

    @Override
    protected void initView() {
        super.initView();
        DisplayCutoutUtils.adaptStatusBarHeight(this, statusBar);

        Bundle bundle = new Bundle();
        bundle.putString(Constant.EXTRA_ACTION_TEST, "Test");
        TestReposFragment testReposFragment = new TestReposFragment();
        testReposFragment.setArguments(bundle);
        openFragment(testReposFragment);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        setOnClickListener(ivBack, view -> {
            finish();
        });
    }

    @Override
    protected void onBack() {
        finish();
    }

    private void openFragment(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.test_fragment, fragment).
                addToBackStack(fragment.getClass().getName()).commitAllowingStateLoss();
    }

}
