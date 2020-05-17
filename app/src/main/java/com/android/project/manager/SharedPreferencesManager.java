package com.android.project.manager;

import android.text.TextUtils;

import com.android.project.mvp.model.bean.User;
import com.android.project.net.gson.GsonSerializer;
import com.android.project.utils.SpUtils;

/**
 * @author Finn
 * @date 2020
 */
public class SharedPreferencesManager {

    private static final String KEY_JSON_USER = "userJson";
    private static final String KEY_JSON_IS_FIRST_OPEN = "isFirstOpen";

    /**
     * KEY_JSON_USER
     */
    public static void saveUser(String userJson) {
        SpUtils.getInstance().saveValue(KEY_JSON_USER, userJson);
    }

    public static User getUser() {
        final String userJson = SpUtils.getInstance().getString(KEY_JSON_USER, "");
        if (!TextUtils.isEmpty(userJson)){
            User userBean = (User) GsonSerializer.get().fromJson(userJson, User.class);
            return userBean;
        }
        return null;
    }

    public static void clearUser() {
        SpUtils.getInstance().removeValue(KEY_JSON_USER);
    }


    /**
     * KEY_JSON_IS_FIRST_OPEN
     */
    public static void saveIsFirstOpen(Boolean isFirstOpen) {
        SpUtils.getInstance().saveValue(KEY_JSON_IS_FIRST_OPEN, isFirstOpen);
    }

    public static Boolean getIsFirstOpen() {
        return SpUtils.getInstance().getBoolean(KEY_JSON_IS_FIRST_OPEN, true);
    }

}
