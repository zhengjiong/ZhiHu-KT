package com.zj.zhihu.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Created by zhengjiong
 * date: 2017/10/7 12:13
 */

class PagerBannerAdapter(val banners: List<View>) : PagerAdapter() {

    override fun getCount(): Int {
        return banners.size
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val view = banners[position]
        container?.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(banners[position])
    }
}