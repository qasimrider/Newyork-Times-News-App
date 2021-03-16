package com.nytimes.newsapp.news

import com.nytimes.newsapp.news.viewModel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val newsDependencies = module {
    viewModel { NewsViewModel(get()) }
}