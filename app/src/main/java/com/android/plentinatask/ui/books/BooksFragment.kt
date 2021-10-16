package com.android.plentinatask.ui.books

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.android.plentinatask.R
import com.android.plentinatask.databinding.FragmentBooksBinding
import com.android.plentinatask.ui.base.BaseFragmentT


class BooksFragment : BaseFragmentT<FragmentBooksBinding>(R.layout.fragment_books) {

    private val viewModel by viewModels<BooksViewModel>()
    private val booksAdapter by lazy { BooksAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        render()
    }

    private fun render(){



        BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment()
//        BooksFragmentDirection.
//        val action = FragmentDirections.confirmationAction(amount)
//        v.findNavController().navigate(action)
        viewModel.books.observe(viewLifecycleOwner){
            it?.let {
                binding.rvBooks.setHasFixedSize(true)
                binding.rvBooks.adapter = booksAdapter
                booksAdapter.submitList(it)
            }
        }

        viewModel.error.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressCircular.isVisible = it
        }


    }
}