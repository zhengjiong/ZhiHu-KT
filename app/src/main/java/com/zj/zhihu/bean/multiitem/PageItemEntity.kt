package com.zj.zhihu.bean.multiitem

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.zj.zhihu.bean.StoryBean
import com.zj.zhihu.bean.TopStoryBean

/**
 * Created by zhengjiong
 * date: 2017/10/7 9:46
 */
class PageItemEntity(val type: Int) : MultiItemEntity {
    var stories: StoryBean? = null
    var topStories: List<TopStoryBean>? = null

    constructor(type: Int, stories: StoryBean) : this(type) {
        this.stories = stories
    }

    constructor(topStoryBean: List<TopStoryBean>, type: Int) : this(type) {
        this.topStories = topStoryBean
    }

    companion object {
        const val TYPE_HEADER = 1
        const val TYPE_ITEM = 2
    }

    override fun getItemType(): Int {
        return type
    }

}