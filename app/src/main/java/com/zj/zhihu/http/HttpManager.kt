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
import io.reactivex.Observable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer


/**
 * Created by zhengjiong
 * date: 2017/10/5 23:18
 */
object HttpManager {
    var zhihuApi: GankApi

    init {
        val builder = OkHttpClient.Builder()
                //.addInterceptor(new LoggingInterceptor())
                .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(CustomGsonConvertFactor.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(builder.build())
                .build()

        zhihuApi = retrofit.create(GankApi::class.java)
    }

    @Keep
    fun <T> doHttp(lifecycleProvider: LifecycleProvider<*>, httpObservable: Observable<T>, onNext: Consumer<T>) {
        httpObservable
                /*失败后的retry配置*/
                //.retryWhen(RetryWhenNetworkException())
                /*生命周期管理*/
                .compose(lifecycleProvider.bindToLifecycle())
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread()).subscribe(onNext)
    }

    @Keep
    fun <T> doHttp(lifecycleProvider: LifecycleProvider<*>, httpObservable: Observable<T>, onNext: Consumer<T>, onError: Consumer<Throwable>) {
        httpObservable
                /*失败后的retry配置*/
                //.retryWhen(RetryWhenNetworkException())
                /*生命周期管理*/
                .compose(lifecycleProvider.bindToLifecycle())
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError)
    }

    @Keep
    fun <T> doHttp(lifecycleProvider: LifecycleProvider<*>, httpObservable: Observable<T>, onNext: Consumer<T>, onError: Consumer<Throwable>, onComplete: Action) {
        httpObservable
                /*结果判断*/
                //.map(basePostEntity);
                /*失败后的retry配置*/
                //.retryWhen(RetryWhenNetworkException())
                /*生命周期管理*/
                .compose(lifecycleProvider.bindToLifecycle())
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete)
    }
}