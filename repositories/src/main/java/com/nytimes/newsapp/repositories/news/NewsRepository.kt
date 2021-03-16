package com.nytimes.newsapp.repositories.news

import com.nytimes.newsapp.network.requestBlocking

class NewsRepository(private val newsWebService: NewsWebService) : NewsDataSource {
    override fun news(sections: String, period: Int) = newsWebService.news(sections, period)
        .requestBlocking {
            it.results.map { it.toView() }

        }
}