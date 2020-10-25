package com.android.project.ui.repos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.project.R;
import com.android.project.base.BaseViewHolder;
import com.android.project.mvp.model.bean.RepoBean;
import com.android.project.ui.repos.adapter.viewholder.ReposViewHolder;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "ReposAdapter";

    private Context context;
    private List<RepoBean> list;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public ReposAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    /**
     * 初始化适配器的数据集
     *
     * @param list String[] 包含用于填充要由RecyclerView使用的视图的数据
     */
    public void setData(List<RepoBean> list) {
        if (list != null) {
            this.list = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    // 创建新视图（由布局管理器调用）
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // 创建一个新的View
        return new ReposViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_repos, viewGroup, false));
    }

    // 替换视图的内容（由布局管理器调用）
    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, final int position) {
        // 从此位置的数据集中获取元素，然后用该元素替换视图的内容
        viewHolder.bindData(position, list.get(position));
    }
}
