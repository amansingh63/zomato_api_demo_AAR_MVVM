package com.amansingh63.zomatoapidemo.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.viewModels
import com.amansingh63.zomatoapidemo.R
import com.amansingh63.zomatoapidemo.databinding.SearchFragmentBinding
import com.amansingh63.zomatoapidemo.ui.BaseViewModel
import com.amansingh63.zomatoapidemo.ui.base.BaseFragment
import com.amansingh63.zomatoapidemo.util.hideKeyboard

const val MIN_QUERY_LENGTH = 2

class SearchFragment : BaseFragment<SearchFragmentBinding>() {

    private val viewModel by viewModels<SearchViewModel> { viewModelFactory }

    private val searchAdapter by lazy {
        SearchResultAdapter(viewModel)
    }

    private val textWatcher by lazy {
        object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    viewModel.getSearchResults()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    override fun layoutRes(): Int = R.layout.search_fragment

    override fun setViewModel() {
        binding.viewmodel = viewModel
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSearchView()
        setupListAdapter()
        viewModel.getSearchResults(firstCall = true)
    }

    private fun setupSearchView() {
        binding.etSearchQuery.addTextChangedListener(textWatcher)
        binding.etSearchQuery.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                activity?.hideKeyboard()
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun performSearch() {
        val query = binding.etSearchQuery.text.toString()
        if (query.isNotEmpty()) {
            if (query.length > MIN_QUERY_LENGTH) {
                viewModel.getSearchResults(query, true)
            } else {
                viewModel.showSnackbarMessage("Please enter more characters to search")
            }
        } else {
            viewModel.showSnackbarMessage("Please enter characters to search")
        }
    }

    private fun setupListAdapter() {
        val viewModel = binding.viewmodel
        if (viewModel != null) {
            binding.rvSearchResults.adapter = searchAdapter
        }
    }


}
