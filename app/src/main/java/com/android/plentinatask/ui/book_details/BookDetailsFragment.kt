package com.android.plentinatask.ui.book_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.android.plentinatask.R
import com.android.plentinatask.databinding.FragmentBookDetailsBinding
import com.android.plentinatask.ui.base.BaseFragmentT


class BookDetailsFragment :
    BaseFragmentT<FragmentBookDetailsBinding>(R.layout.fragment_book_details) {

    private val args: BookDetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<BookDetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBookDetails(args.bookId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        render()
    }
    private fun render(){
        viewModel.error.observe(viewLifecycleOwner){
            it?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressCircular.isVisible = it
        }
    }
}