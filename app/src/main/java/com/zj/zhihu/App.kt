package com.zj.zhihu

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by zhengjiong
 * date: 2017/10/7 11:09
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this);
    }
}