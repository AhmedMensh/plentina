package com.android.plentinatask.network


class RemoteDataSource(private val api: ApiService = apiService) {


    suspend fun getCurrentBooks() = safeApiCall { apiService.getCurrentBooks()}
    suspend fun getBookDetails(bookId : Int) = safeApiCall { apiService.getBookDetails(bookId)}
}