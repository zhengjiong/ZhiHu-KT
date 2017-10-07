package com.zj.zhihu.adapter

import android.net.Uri
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import com.rd.PageIndicatorView
import com.rd.animation.type.AnimationType
import com.zj.zhihu.R
import com.zj.zhihu.bean.multiitem.PageItemEntity

/**
 * Created by zhengjiong
 * date: 2017/10/7 9:42
 */

class PagerAdapter: BaseMultiItemQuickAdapter<PageItemEntity, BaseViewHolder>(mutableListOf<PageItemEntity>()) {

    init {
        addItemType(PageItemEntity.TYPE_HEADER, R.layout.item_pager_header_layout)
        addItemType(PageItemEntity.TYPE_ITEM, R.layout.item_pager_layout)
    }

    override fun convert(helper: BaseViewHolder, item: PageItemEntity) {
        if (PageItemEntity.TYPE_ITEM == item.itemType) {
            helper.setText(R.id.tv_title, item.stories?.title)
            var draweeView = helper.getView<SimpleDraweeView>(R.id.simpleDraweeView)


            draweeView?.setImageURI(Uri.parse(item.stories?.images?.get(0)))

        } else {
            if (item.topStories?.isNotEmpty()!!) {
                item.topStories!!.map {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_banner_layout, null)
                    val draweeView  = view.findViewById<SimpleDraweeView>(R.id.draweeView)
                    val title = view.findViewById<TextView>(R.id.tvTitle)
                    draweeView.setImageURI(Uri.parse(it.image), null)
                    title.text = it.title
                    view
                }.let {
                    val viewPager = helper.getView<ViewPager>(R.id.viewPager)
                    val pageIndicatorView = helper.getView<PageIndicatorView>(R.id.pageIndicatorView)

                    viewPager.adapter = PagerBannerAdapter(it)
                    pageIndicatorView.setRadius(4)
                    pageIndicatorView.setAnimationType(com.rd.animation.type.AnimationType.SLIDE)
                    pageIndicatorView.setViewPager(viewPager)
                }
            }

        }
    }

}