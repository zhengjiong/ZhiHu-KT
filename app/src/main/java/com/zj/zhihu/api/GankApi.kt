package com.zj.zhihu.api

import com.zj.zhihu.bean.Goods
import com.zj.zhihu.bean.JsonResult
import com.zj.zhihu.bean.PageBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by zhengjiong
 * date: 2017/10/5 23:05
 */
interface GankApi {

    /**
     * gank api
     */
    @GET("data/Android/10/{page}")
    fun getAndroidData(@Path("page") page: Int): Observable<JsonResult<List<Goods>>>

    @GET("data/福利/10/{page}")
    fun getMeizhi(@Path("page") page: Int): Observable<JsonResult<List<Goods>>>


    //知乎api
    @GET("api/4/news/latest")
    fun getLastestPager(): Observable<PageBean>

    @GET("/api/4/news/before/{id}")
    fun getBeforePager(@Path("id") id: String): Observable<PageBean>

    /*@GET("/api/4/news/{id}")
    fun getDetails(@Path("id") id: String): Observable<PagerDetailBean>*/
}