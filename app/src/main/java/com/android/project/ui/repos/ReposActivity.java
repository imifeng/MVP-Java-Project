package com.android.project.ui.repos;

import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.project.R;
import com.android.project.base.BaseMvpActivity;
import com.android.project.http.network.NetworkConnectedListener;
import com.android.project.http.network.NetworkManger;
import com.android.project.mvp.contract.ReposContract;
import com.android.project.mvp.model.bean.RepoBean;
import com.android.project.mvp.presenter.ReposPresenter;
import com.android.project.ui.repos.adapter.ReposAdapter;
import com.android.project.utils.DisplayCutoutUtils;
import com.android.project.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ReposActivity extends BaseMvpActivity<ReposContract.View, ReposPresenter> implements ReposContract.View, NetworkConnectedListener {

    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_username)
    EditText etName;
    @BindView(R.id.tv_repos_btn)
    TextView tvReposBtn;
    @BindView(R.id.tv_no_data)
    TextView tv_no_data;
    @BindView(R.id.rv_repos)
    RecyclerView rv_repos;

    private LinearLayoutManager layoutManager;
    private ReposAdapter reposAdapter;

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

        //注册网络监听
        NetworkManger.getInstance().registerNetworkCallback(this);

        layoutManager = new LinearLayoutManager(this);
        rv_repos.setLayoutManager(layoutManager);
        reposAdapter = new ReposAdapter(this);
        rv_repos.setAdapter(reposAdapter);
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

    /**
     * @return Username
     */
    private String getUsername() {
        return etName.getText().toString().trim();
    }

    @Override
    public void onReposSuccess(List<RepoBean> response) {
        if (response != null && response.size() > 0){
            reposAdapter.setData(response);
            tv_no_data.setVisibility(View.GONE);
        }else {
            tv_no_data.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 网络监听回调
     */
    @Override
    public void networkConnected(Boolean isConnected) {
        if (isConnected) {
            //有网络
            ToastUtils.showToast("网络连接上了");
        } else {
            //无网络
            ToastUtils.showToast("失去网络了");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销网络监听
        NetworkManger.getInstance().unregisterNetworkCallback();
    }
}
