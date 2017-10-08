package com.zj.zhihu.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by zhengjiong
 * date: 2017/10/8 11:49
 */

/*
完整写法
inline fun <T> transformer(): ObservableTransformer<T, T>{
    return object: ObservableTransformer<T, T>{
        override fun apply(upstream: Observable<T>): ObservableSource<T> {
            upstream.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            return upstream
        }
    }
}*/


inline fun <T> transformer(): ObservableTransformer<T, T> {
    return ObservableTransformer {
        it.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

/*
这样是错误的
fun <T> transformer(): (Observable<T>) -> Observable<T> {
    return fun(upstream:Observable<T>):Observable<T> = upstream
}*/
