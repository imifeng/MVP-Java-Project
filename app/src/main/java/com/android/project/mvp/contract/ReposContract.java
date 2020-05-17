package com.android.project.mvp.contract;


import com.android.project.base.BaseView;
import com.android.project.mvp.model.bean.RepoBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Finn
 * @date 2020
 */
public interface ReposContract {

    interface Model{
        Observable<List<RepoBean>> getRepos(String username);
    }

    interface View extends BaseView {

        void onReposSuccess(List<RepoBean> response);
    }

    interface Presenter {
        /**
         * get Repos
         *
         * @param username
         */

        void getRepos(String username);
    }
}
