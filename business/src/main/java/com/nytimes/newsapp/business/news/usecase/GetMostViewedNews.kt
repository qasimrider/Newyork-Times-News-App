package com.nytimes.newsapp.business.news.usecase

import com.nytimes.newsapp.common.base.BaseUseCase
import com.nytimes.newsapp.repositories.news.NewsRepository
import com.nytimes.newsapp.dtos.news.NewsView


class GetMostViewedNews(private val newsRepository: NewsRepository) :
    BaseUseCase<List<NewsView>, GetMostViewedNews.Params>() {
    override suspend fun run(param: Params) = newsRepository.news(param.sections, param.period)
    data class Params(val sections: String, val period: Int)
}