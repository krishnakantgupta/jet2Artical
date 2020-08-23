package com.kk.jet2articalassignment.dagger

import com.kk.jet2articalassignment.BaseApplication
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponents {

    @Inject
    fun inject(app: BaseApplication)
}