package com.nytimes.newsapp.repositories.news
import com.nytimes.newsapp.common.functional.Either
import com.nytimes.newsapp.common.error.ErrorEntity
import com.nytimes.newsapp.dtos.news.NewsView
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
    suspend fun news(sections:String,period:Int) : Flow<Either<ErrorEntity, List<NewsView>>>


}