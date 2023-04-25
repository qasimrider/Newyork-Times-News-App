package com.nytimes.newsapp.business.news.usecase

import com.nytimes.newsapp.common.base.BaseUseCase
import com.nytimes.newsapp.common.error.ErrorEntity
import com.nytimes.newsapp.common.functional.Either
import com.nytimes.newsapp.repositories.news.NewsRepository
import com.nytimes.newsapp.dtos.news.NewsView


class GetMostViewedNews(private val newsRepository: NewsRepository) :
    BaseUseCase<List<NewsView>, GetMostViewedNews.Params>() {
    override suspend fun runFlow(param: Params) = newsRepository.news(param.sections, param.period)
    data class Params(val sections: String, val period: Int)

    override suspend fun run(param: Params): Either<ErrorEntity, List<NewsView>> {
        TODO("Not yet implemented")
    }
}