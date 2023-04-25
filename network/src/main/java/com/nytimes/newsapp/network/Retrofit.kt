package com.nytimes.newsapp.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nytimes.newsapp.common.error.ErrorEntity
import com.nytimes.newsapp.common.functional.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call


/**
 * Takes in a transform lambda to return a modified version of the responsex
 */

var generalErrorImplementation = GeneralErrorImplementation()

@Suppress("unused")
fun <T, R> Call<T>.requestTransformBlocking(transform: (T) -> R): Either<ErrorEntity, R> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform(response.body()!!))
            false -> Either.Left(generalErrorImplementation.getHttpErrors(response))
        }
    } catch (exception: Throwable) {
        Either.Left(generalErrorImplementation.getError(exception))

    }
}

@Suppress("unused")
fun <T, R> Call<T>.requestBlocking(transform: (T) -> R): Either<ErrorEntity, R> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform(response.body()!!))

            false -> Either.Left(generalErrorImplementation.getHttpErrors(response))
        }
    } catch (exception: Throwable) {

        Either.Left(generalErrorImplementation.getError(exception))

    }

}

suspend fun <T, R> Call<T>.requestFlow(transform: (T) -> R): Flow<Either<ErrorEntity, R>> = flow {
    try {
        val response = execute()
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                emit(Either.Right(transform(result)))
            } else {
                emit(Either.Left(generalErrorImplementation.getHttpErrors(response)))
            }
        } else {
            emit(Either.Left(generalErrorImplementation.getHttpErrors(response)))
        }
    } catch (e: Exception) {
        emit(Either.Left(generalErrorImplementation.getError(e)))
    }
}


fun <T> Call<T>.requestBlocking(): Either<ErrorEntity, T> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right((response.body()!!))
            false -> Either.Left(generalErrorImplementation.getHttpErrors(response))
        }
    } catch (exception: Throwable) {
        Either.Left(generalErrorImplementation.getError(exception))
    }
}


inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)








