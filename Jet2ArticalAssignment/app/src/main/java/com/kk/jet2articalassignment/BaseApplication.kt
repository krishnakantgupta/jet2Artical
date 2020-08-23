package com.kk.jet2articalassignment

import android.app.Application
import com.kk.jet2articalassignment.dagger.AppComponents
import com.kk.jet2articalassignment.dagger.AppModule
//import com.kk.jet2articalassignment.dagger.DaggerAppComponents

class BaseApplication : Application() {

//    val component: AppComponents by lazy {
//        DaggerAppComponents
//            .builder()
//            .appModule(AppModule(this))
//            .build()
//    }

    override fun onCreate() {
        super.onCreate()
//        component.inject(this)
    }

}
