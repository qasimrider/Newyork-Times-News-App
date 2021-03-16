package com.nytimes.newsapp.repositories
import com.nytimes.newsapp.repositories.news.NewsRepository
import com.nytimes.newsapp.repositories.news.NewsWebService
import org.koin.dsl.module
import retrofit2.Retrofit

val repoDependencies = module {

    //retrofit
    single { get<Retrofit>().create(NewsWebService::class.java) }

    //repositories
    single { NewsRepository(get()) }


}

