package com.android.project.manager;

import android.text.TextUtils;

import com.android.project.mvp.model.bean.User;
import com.android.project.utils.GsonUtils;
import com.android.project.utils.SpUtils;

/**
 * @author Finn
 * @date 2020
 */
public class SharedPreferencesManager {

    private static final String KEY_USER = SpUtils.SP_PREFIX+".USER";
    private static final String KEY_IS_FIRST_OPEN = SpUtils.SP_PREFIX+".IS_FIRST_OPEN";

    /**
     * KEY_JSON_USER
     */
    public static void saveUser(String userJson) {
        SpUtils.getInstance().saveValue(KEY_USER, userJson);
    }

    public static User getUser() {
        final String userJson = SpUtils.getInstance().getString(KEY_USER, "");
        if (!TextUtils.isEmpty(userJson)){
            User userBean = (User) GsonUtils.get().fromJson(userJson, User.class);
            return userBean;
        }
        return null;
    }

    public static void clearUser() {
        SpUtils.getInstance().removeValue(KEY_USER);
    }


    /**
     * KEY_JSON_IS_FIRST_OPEN
     */
    public static void saveIsFirstOpen(Boolean isFirstOpen) {
        SpUtils.getInstance().saveValue(KEY_IS_FIRST_OPEN, isFirstOpen);
    }

    public static Boolean getIsFirstOpen() {
        return SpUtils.getInstance().getBoolean(KEY_IS_FIRST_OPEN, true);
    }

}
