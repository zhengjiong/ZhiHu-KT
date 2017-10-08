package com.zj.zhihu.presenter

import com.trello.rxlifecycle2.LifecycleProvider
import com.zj.zhihu.bean.PageBean
import com.zj.zhihu.http.ApiManager
import com.zj.zhihu.utils.transformer
import io.reactivex.functions.Consumer

/**
 * Created by zhengjiong
 * date: 2017/10/6 17:24
 */
class PagerPresenter(lifecycleProvider: LifecycleProvider<*>) : BasePresenter(lifecycleProvider) {
    var page = 1

    fun refresh(onNext: Consumer<PageBean>, onError: Consumer<String>) {
        page = 1
        requestList(onNext, onError)
    }

    fun loadMore(onNext: Consumer<PageBean>, onError: Consumer<String>) {
        page++
        requestList(onNext, onError)
    }

    private fun requestList(onNext: Consumer<PageBean>, onError: Consumer<String>) {
        ApiManager.zhihuApi.getLastestPager()
                .compose(lifecycleProvider.bindToLifecycle())
                .compose(transformer())
                .subscribe({
                    it?.let {
                        onNext.accept(it)
                    } ?: throw NullPointerException()
                }, {
                    onError.accept(it?.message)
                })
    }


}