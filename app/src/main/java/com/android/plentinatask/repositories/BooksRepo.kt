package com.android.plentinatask.repositories

import com.android.plentinatask.models.DataResult
import com.android.plentinatask.models.BooksResponseModel
import com.android.plentinatask.network.RemoteDataSource

class BooksRepo(private val remoteDataSource: RemoteDataSource = RemoteDataSource()) : IBooksRepo {
    override suspend fun getCurrentBooks(): DataResult<List<BooksResponseModel>> {
        return remoteDataSource.getCurrentBooks()
    }

    override suspend fun getBookDetails(bookId: Int): DataResult<BooksResponseModel> =
        remoteDataSource.getBookDetails(bookId)
}