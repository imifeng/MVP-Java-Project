package com.android.project.mvp.model.service;

import com.android.project.mvp.model.bean.RepoBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Finn
 * @date 2020
 */
public interface TestService {

    /**
     * 网络请求方法:@GET、@POST、@PUT、@DELETE、@HEAD(常用)
     * 网络请求标记: @FormUrlEncoded、@Multipart、@Streaming
     * 网络请求参数: @Header &、@Headers、 @Body、@Field 、 @FieldMap、@Part 、 @PartMap、@Query、@QueryMap、@Path、@Url
     */


    @GET("users/{user}/repos")
    Observable<List<RepoBean>> getRepos(@Path("user") String user);

}
