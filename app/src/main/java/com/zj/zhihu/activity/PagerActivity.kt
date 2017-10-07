package com.zj.zhihu.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.zj.zhihu.R
import com.zj.zhihu.adapter.PagerAdapter
import com.zj.zhihu.base.RxActivity
import com.zj.zhihu.bean.PageBean
import com.zj.zhihu.bean.multiitem.PageItemEntity
import com.zj.zhihu.presenter.PagerPresenter
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zhengjiong
 * date: 2017/10/5 23:05
 */
class PagerActivity : RxActivity() {
    var pagePresenter: PagerPresenter? = null
    var adapter: PagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagePresenter = PagerPresenter(this)

        adapter = PagerAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            refresh()
        }

        swipeRefresh.isRefreshing = true
        refresh()
    }

    fun refresh() {
        pagePresenter?.refresh(Consumer { pageBean ->
            swipeRefresh.isRefreshing = false
            val stories = pageBean.stories
            val banners = pageBean.top_stories

            var entities = mutableListOf<PageItemEntity>()
            banners?.let {
                entities.add(PageItemEntity(it, PageItemEntity.TYPE_HEADER))
            }
            stories?.forEach {
                entities.add(PageItemEntity(PageItemEntity.TYPE_ITEM, it))
            }

            adapter!!.setNewData(entities)
        }, Consumer { t ->
            swipeRefresh.isRefreshing = false
            t?.let(::println)
        })
    }
}
