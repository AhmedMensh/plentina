package com.android.plentinatask.network

import android.util.Log
import com.android.plentinatask.models.ApiError
import com.android.plentinatask.models.DataResult
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import retrofit2.Response

import java.io.IOException


val moshi: Moshi = Moshi.Builder().build()
val jsonAdapter: JsonAdapter<ApiError> = moshi.adapter<ApiError>(ApiError::class.java)
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataResult<T> {

    return try {
        val result = apiCall()
        if (result.isSuccessful) {

//            val data = result
            return DataResult.Success(result.body())
        }
        Log.i("Error", "safe call api try error")
        return DataResult.Error(Exception("Something went wrong"))

    } catch (e: Exception) {
        e.message?.let { Log.i("Error", it) }
        when (e) {
            is HttpException -> {
                val errorBodyString = e.response()?.errorBody()?.string()
                var errorBodyJson: ApiError? = null
                if (errorBodyString != null) {
                    try {
                        errorBodyJson = jsonAdapter.fromJson(errorBodyString)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                when (e.code()) {
                    in 300 until 400 -> {
                        DataResult.Error(Exception("You are not authorized."))
                    }
                    in 400 until 500 -> {
                        DataResult.Error(
                            Exception(
                                errorBodyJson?.errors  ?: "Please verify the data."
                            )
                        )
                    }
                    in 500 until 600 -> {
                        DataResult.Error(Exception("An error occurred in the server. Please try again soon."))
                    }
                    else -> {
                        DataResult.Error(Exception("Something went wrong."))
                    }
                }
            }
            is IOException -> {
                DataResult.Error(Exception("No connection Please make sure you are connected to the Internet."))
            }
            else -> {
                DataResult.Error(Exception("Something went wrong."))
            }
        }
    }
}
