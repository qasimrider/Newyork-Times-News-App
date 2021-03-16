package com.nytimes.newsapp.repositories.news
import com.nytimes.newsapp.common.functional.Either
import com.nytimes.newsapp.common.error.ErrorEntity
import com.nytimes.newsapp.dtos.news.NewsView

interface NewsDataSource {
    fun news(sections:String,period:Int) : Either<ErrorEntity, List<NewsView>>
}