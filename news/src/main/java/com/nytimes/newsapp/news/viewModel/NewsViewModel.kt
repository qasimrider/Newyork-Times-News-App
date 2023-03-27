package com.nytimes.newsapp.news.viewModel

import com.nytimes.newsapp.common.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nytimes.newsapp.business.news.usecase.GetMostViewedNews
import com.nytimes.newsapp.dtos.news.NewsView

class NewsViewModel( private val getMostViewedNews: GetMostViewedNews) :
    BaseViewModel() {

    private val _mostViewedNews = MutableLiveData<List<NewsView>>()

    val mostViewedNews: LiveData<List<NewsView>>
        get() = _mostViewedNews


    //region Network Calls
    fun fetchMostViewedNews(params: GetMostViewedNews.Params) {

        val gson = Gson().fromJson("",NewsView::class.java)
        operationStatus.value = Operation.Started
        getMostViewedNews(viewModelScope,params) {
            it.either(::handleFailure)
            {
                _mostViewedNews.value = it
                operationStatus.value = Operation.Completed
            }
        }
    }

    //endregion

    override fun onCleared() {
        super.onCleared()
    }


}