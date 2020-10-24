package com.android.project.ui.repos.adapter.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.project.R;
import com.android.project.base.BaseViewHolder;
import com.android.project.mvp.model.bean.RepoBean;
import com.android.project.utils.GlideImageUtils;

public class ReposViewHolder extends BaseViewHolder {
    private static final String TAG = "ReposViewHolder";

    private ImageView iv_avatar;
    private TextView tv_name;
    private TextView tv_description;

    public ReposViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        tv_description = (TextView) itemView.findViewById(R.id.tv_description);

        // 为ViewHolder的视图定义点击监听器
        itemView.setOnClickListener(view -> {
            Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
        });
    }

    @Override
    public void bindData(int position, Object item) {
        if (item != null && item instanceof RepoBean) {
            RepoBean bean = (RepoBean) item;
            GlideImageUtils.getInstance().loadRound(itemView.getContext(), iv_avatar, bean.getAvatar(), true);
            tv_name.setText(bean.getName());
            tv_description.setText(bean.getDescription());
        }
    }
}
