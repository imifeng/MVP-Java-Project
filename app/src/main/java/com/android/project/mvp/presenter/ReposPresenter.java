package com.android.project.mvp.presenter;

import com.android.project.base.BasePresenter;
import com.android.project.mvp.model.bean.RepoBean;
import com.android.project.mvp.contract.ReposContract;
import com.android.project.mvp.model.ReposModel;
import com.android.project.net.NetRequestListener;

import java.util.List;

/**
 * @author Finn
 * @date 2020
 */
public class ReposPresenter extends BasePresenter<ReposContract.View> implements ReposContract.Presenter {

    private ReposContract.Model model;
    public ReposPresenter() {
        model = new ReposModel();
    }

    @Override
    public void getRepos(String username) {
        getPView().showLoading();
        requestNetwork(model.getRepos(username), new NetRequestListener<List<RepoBean>>() {
            @Override
            public void onSuccess(List<RepoBean> response) {
                getPView().onReposSuccess(response);
            }

            @Override
            public void onFailed(Throwable e) {
                getPView().onError(e);
            }
        });

    }


}
