package com.android.project.base;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.project.utils.ClickShadowUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Finn
 * @date 2020
 */
public abstract class BaseFragment extends Fragment {

    public View rootView;
    private Unbinder unBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(getLayoutId(), container, false);
            initView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initEvent();
    }

    protected abstract int getLayoutId();


    /**
     * 初始化视图
     *
     * @param view
     */
    protected void initView(View view){
        unBinder = ButterKnife.bind(this, view);
    }

    protected void initData(){

    }

    protected void initEvent(){

    }

    protected void setOnClickListener(View view, View.OnClickListener onClickListener) {
        ClickShadowUtils.setOnClickListener(view, onClickListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unBinder != null) {
            try {
                unBinder.unbind();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
