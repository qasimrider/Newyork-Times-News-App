package com.nytimes.newsapp.common.base

import com.nytimes.newsapp.common.error.ErrorEntity
import com.nytimes.newsapp.common.functional.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<out Type, in Params>() {
    abstract suspend fun run(param: Params): Either<ErrorEntity, Type>

    abstract suspend fun runFlow(param: Params): Flow<Either<ErrorEntity, Type>>
    operator fun invoke(
        viewModelScope: CoroutineScope,
        params: Params,
        onResult: (Either<ErrorEntity, Type>) -> Unit
    ) {
//        viewModelScope.launch {
//            CoroutineScope(Dispatchers.IO)
//                .launch {
//                    val result = run(params)
//                    withContext(Dispatchers.Main)
//                    {
//                        onResult(result)
//                    }
//                }
//        }

//        flow API
        viewModelScope.launch {
//            CoroutineScope(Dispatchers.IO)
//                .launch {
                    val result = runFlow(params)
//                    withContext(Dispatchers.Main)
//                    {
                        result.collect { onResult(it) }
//                    }
//                }
        }
    }

    class None
}