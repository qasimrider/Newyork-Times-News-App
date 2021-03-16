package com.nytimes.newsapp.business

import com.nytimes.newsapp.business.news.usecase.GetMostViewedNews
import org.koin.dsl.module


val useCasesDependencies = module{

    single{
        GetMostViewedNews(get())
    }
}