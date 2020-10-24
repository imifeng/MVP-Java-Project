package com.android.project.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.project.utils.OnClickUtils;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * @author Finn
 * @date 2020
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int view = getLayoutId();
        if (view != 0) {
            setContentView(view);
        }

        ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForImageView(this, 50, null);

        initView();
        initData();
        initEvent();
    }

    public abstract int getLayoutId();


    protected void initView(){

    }

    protected void initData(){

    }

    protected void initEvent(){

    }


    protected void setOnClickListener(View view, View.OnClickListener onClickListener) {
        OnClickUtils.setOnClickListener(view, onClickListener);
    }

    protected Boolean onBack(){
        return false;
    }

    /**
     * Listen the back key click event
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (onBack()) return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Set font size, language
     * @return
     */
    @Override
    public Resources getResources() {
        //Set font size, language
        return super.getResources();
    }
}
