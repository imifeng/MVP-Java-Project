package com.android.project.manager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.android.project.utils.LogUtils;

/**
 * @author Finn
 * @date 2020
 */
public class ProgressDialogManager {

    private static volatile ProgressDialogManager progressDialogManager;

    private ProgressDialog progressDialog;

    public static ProgressDialogManager getInstance() {
        if (progressDialogManager == null) {
            synchronized (ProgressDialogManager.class) {
                if (progressDialogManager == null) {
                    progressDialogManager = new ProgressDialogManager();
                }
            }
        }
        return progressDialogManager;
    }

    public void showProgress(Context context) {
        if (progressDialog != null && progressDialog.isShowing() || context == null) {
            return;
        }

        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return;
        }
        try {
            progressDialog = ProgressDialog.show(context, null, "Loading...", true, true);
//            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            progressDialog.setContentView(R.layout.progress);
        } catch (Exception e) {
            LogUtils.showError(e);
        }
    }

    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                LogUtils.showError(e);
            }
            progressDialog = null;
        }
    }
}
