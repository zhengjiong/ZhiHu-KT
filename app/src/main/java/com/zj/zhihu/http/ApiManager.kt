package com.zj.zhihu.http

import com.zj.zhihu.api.GankApi
import com.zj.zhihu.common.Constants
import com.zj.zhihu.retrofit.CustomGsonConvertFactor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.trello.rxlifecycle2.LifecycleProvider
import android.support.annotation.Keep
import com.zj.zhihu.api.ZhiHuApi
import io.reactivex.Observable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer


/**
 * Created by zhengjiong
 * date: 2017/10/5 23:18
 */
object ApiManager {
    @JvmStatic
    val BASE_URL = "http://news-at.zhihu.com/"
    //val BASE_URL = "http://gank.io/api/"

    @JvmStatic
    val TIMEOUT = 10L

    val zhihuApi: ZhiHuApi by lazy {
        val builder = OkHttpClient.Builder()
                //.addInterceptor(new LoggingInterceptor())
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(CustomGsonConvertFactor.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(builder.build())
                .build()

        retrofit.create(ZhiHuApi::class.java)
    }
}