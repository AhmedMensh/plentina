package com.android.plentinatask.network

import com.android.plentinatask.models.BooksResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {


    @GET("books")
    suspend fun getCurrentBooks(
    ): Response<List<BooksResponseModel>>


    @GET("books/{book_id}")
    suspend fun getBookDetails(
        @Path("book_id") bookId: Int
    ): Response<BooksResponseModel>

}







