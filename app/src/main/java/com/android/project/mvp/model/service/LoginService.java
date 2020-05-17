package com.android.project.mvp.model.service;

import com.android.project.mvp.model.bean.BaseResponse;
import com.android.project.mvp.model.bean.User;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Finn
 * @date 2020
 */
public interface LoginService {

    /**
     * 网络请求方法:@GET、@POST、@PUT、@DELETE、@HEAD(常用)
     * 网络请求标记: @FormUrlEncoded、@Multipart、@Streaming
     * 网络请求参数: @Header &、@Headers、 @Body、@Field 、 @FieldMap、@Part 、 @PartMap、@Query、@QueryMap、@Path、@Url
     */


    @POST("api/auth/login")
    Observable<BaseResponse<User>> login(@Body Map<String, String> body);

}
