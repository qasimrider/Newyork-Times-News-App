package com.nytimes.newsapp.repositories.news

import com.nytimes.newsapp.network.requestBlocking
import com.nytimes.newsapp.network.requestFlow

class NewsRepository(private val newsWebService: NewsWebService) : NewsDataSource {
    override suspend fun news(sections: String, period: Int) = newsWebService.news(sections, period)
        .requestFlow {
            it.results.map { it.toView() }

        }
}