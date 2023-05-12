package com.sample.simpsonsviewer.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.simpsonsviewer.databinding.ListPaneBinding
import com.sample.simpsonsviewer.ui.main.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : AbstractListDetailFragment() {

    private val mainViewModel by viewModels<MainViewModel>()
    lateinit var itemAdapter: ItemAdapter

    private var _binding: ListPaneBinding? = null
    private val binding get() = _binding!!


    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onListPaneViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        itemAdapter.setOnItemClickListener {
            this.findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.listUiState.collect{items ->
                itemAdapter.differ.submitList(items.currentList)
            }
        }

//        itemAdapter.differ.submitList(mainViewModel.listUiState.value.currentList)

        var job: Job? = null


        binding.etSearch.addOnEditTextAttachedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(3000)
                editable.editText?.text?. let {
                    if (editable.editText!!.text.isNotEmpty() && !mainViewModel.listUiState.value.loading) {
                        mainViewModel.searchDb(it.toString())
                    }
                }
            }
        }

    }


    override fun onCreateListPaneView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ListPaneBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setUpRecyclerView() {
        itemAdapter = ItemAdapter()
        binding.recView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }



}