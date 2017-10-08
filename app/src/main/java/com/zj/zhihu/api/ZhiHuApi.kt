package com.zj.zhihu.api

import com.zj.zhihu.bean.PageBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by zhengjiong
 * date: 2017/10/8 11:42
 */
interface ZhiHuApi{
    //知乎api
    @GET("api/4/news/latest")
    fun getLastestPager(): Observable<PageBean>

    @GET("/api/4/news/before/{id}")
    fun getBeforePager(@Path("id") id: String): Observable<PageBean>

    /*@GET("/api/4/news/{id}")
    fun getDetails(@Path("id") id: String): Observable<PagerDetailBean>*/
}