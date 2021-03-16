package com.nytimes.newsapp.common.base

import androidx.lifecycle.ViewModel
import com.nytimes.newsapp.common.error.ErrorEntity
import com.nytimes.newsapp.common.singleLiveData.SingleLiveEvent

abstract class BaseViewModel() : ViewModel() {
    var errorEntity: SingleLiveEvent<ErrorEntity> = SingleLiveEvent()
    var operationStatus: SingleLiveEvent<Operation> = SingleLiveEvent()

    protected fun handleFailure(errorEntity: ErrorEntity) {
        this.errorEntity.value = errorEntity
    }

    sealed class Operation {
        object Started : Operation()
        object Completed : Operation()
    }

}