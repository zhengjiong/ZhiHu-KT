package com.zj.zhihu.presenter

import android.support.annotation.Keep
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by zhengjiong
 * date: 2017/10/6 17:29
 */

open class BasePresenter(val lifecycleProvider: LifecycleProvider<*>) {

    fun <T> submitRequest(observable: Observable<T>, onNext: Consumer<T>, onError: Consumer<Throwable>) {
        doHttp(lifecycleProvider, observable, onNext, onError)
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
}