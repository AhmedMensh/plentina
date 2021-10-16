package com.android.plentinatask.repositories

import com.android.plentinatask.models.DataResult
import com.android.plentinatask.models.BooksResponseModel

interface IBooksRepo {

    suspend fun getCurrentBooks() : DataResult<List<BooksResponseModel>>
    suspend fun getBookDetails(bookId : Int) : DataResult<BooksResponseModel>
}