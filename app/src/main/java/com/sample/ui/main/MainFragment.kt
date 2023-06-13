package com.sample.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.databinding.ListPaneBinding
import com.sample.ui.main.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : AbstractListDetailFragment() {

    private val mainViewModel by viewModels<MainViewModel>()
    lateinit var itemAdapter: ItemAdapter

    private var _binding: ListPaneBinding? = null
    private val binding get() = _binding!!


    override fun onCreateListPaneView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ListPaneBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onListPaneViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        itemAdapter.setOnItemClickListener {
            this.findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.listUiState.collect{ listUiState ->
                itemAdapter.differ.submitList(listUiState.currentList)
            }
        }

        var job: Job? = null
        binding.etSearch.editText?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                job?.cancel()
                job = MainScope().launch {
                    mainViewModel.searchDb( binding.etSearch.editText!!.text.toString())
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }



    private fun setUpRecyclerView() {
        itemAdapter = ItemAdapter()
        binding.recView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }
}