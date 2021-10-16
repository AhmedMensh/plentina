package com.android.plentinatask.ui.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.plentinatask.models.DataResult
import com.android.plentinatask.models.BooksResponseModel
import com.android.plentinatask.repositories.BooksRepo
import com.android.plentinatask.repositories.IBooksRepo
import kotlinx.coroutines.launch

class BooksViewModel(private val iBooksRepo: IBooksRepo = BooksRepo()) : ViewModel() {


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _books = MutableLiveData<List<BooksResponseModel>>()
    val books: LiveData<List<BooksResponseModel>> get() = _books

    init {
        getCurrentBooks()
    }
    private fun getCurrentBooks() {

        viewModelScope.launch {
            _isLoading.value = true
            when (val response = iBooksRepo.getCurrentBooks()) {
                is DataResult.Success -> {
                    _isLoading.value = false
                    _books.value = response.content
                }
                is DataResult.Error -> {
                    _isLoading.value = false
                    _error.value = response.exception.message
                }
            }
        }
    }

}