package com.zj.zhihu.bean

/**
 * Created by zhengjiong
 * date: 2017/10/5 23:12
 */

data class Goods(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val images: Array<String>,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String
)