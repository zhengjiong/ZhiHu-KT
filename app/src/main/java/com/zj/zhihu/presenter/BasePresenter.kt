package com.zj.zhihu.presenter

import com.trello.rxlifecycle2.LifecycleProvider
import com.zj.zhihu.http.HttpManager
import io.reactivex.Observable
import io.reactivex.functions.Consumer

/**
 * Created by zhengjiong
 * date: 2017/10/6 17:29
 */

open class BasePresenter(val lifecycleProvider: LifecycleProvider<*>) {
    val httpManager = HttpManager

    fun <T> submitRequest(observable: Observable<T>, onNext: Consumer<T>, onError: Consumer<Throwable>) {
        httpManager.doHttp(lifecycleProvider, observable, onNext, onError)
    }
}