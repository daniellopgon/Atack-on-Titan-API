package com.example.atackontitanapi.core.data.remote.api

import com.example.atackontitanapi.core.domain.ErrorApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

suspend fun <T> apiCall(
    call: suspend () -> Response<T>
): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = call()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else if (response.code() == 404) {
                Result.failure(ErrorApp.NotFoundError)
            } else {
                Result.failure(ErrorApp.ServerError)
            }
        } catch (e: IOException) {
            Result.failure(ErrorApp.NetworkError)
        } catch (e: Exception) {
            Result.failure(ErrorApp.ServerError)
        }
    }
}
