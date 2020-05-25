package com.android.project.mvp.model.bean;

import com.android.project.manager.SharedPreferencesManager;
import com.android.project.utils.GsonUtils;

public class User {
    private int id;

    private String login;

    private String avatar_url;

    private String token;

    public static void save(User user) {
        if (user != null) {
            SharedPreferencesManager.saveUser(GsonUtils.get().toJson(user));
        } else {
            SharedPreferencesManager.clearUser();
        }
    }

    public static User getCurrentUser() {
        return SharedPreferencesManager.getUser();
    }

    public static int getUserId(){
        User user = getCurrentUser();
        if(user != null){
            return user.getId();
        }else{
            return 0;
        }
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "id -> " + id + " login -> " + login;
    }

}
