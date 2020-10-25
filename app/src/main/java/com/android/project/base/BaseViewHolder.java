package com.android.project.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) { super(itemView); }

    private int viewType;

    public abstract void bindData(int position, Object item);

    public <T> T getItemData(Object object,Class<T> tClass){
        if(object != null && tClass.isInstance(object) ){
            return (T)object;
        }else{
            return null;
        }
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}