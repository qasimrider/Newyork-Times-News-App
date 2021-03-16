package com.nytimes.newsapp

import android.app.Application
import com.nytimes.newsapp.business.useCasesDependencies
import com.nytimes.newsapp.news.newsDependencies
import com.nytimes.newsapp.repositories.repoDependencies
import com.nytimes.newsapp.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    useCasesDependencies,
                    repoDependencies,
                    networkModule,
                    newsDependencies,
                )
            )
        }
    }

}