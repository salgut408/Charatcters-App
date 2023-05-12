package com.sample.simpsonsviewer.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.viewModels
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

        itemAdapter.differ.submitList(mainViewModel.listUiState.value.currentList)



        var job: Job? = null


        binding.etSearch

        binding.etSearch.addOnEditTextAttachedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(3000)
                editable.editText?.text?. let {
//                    if()
                    if (editable.editText!!.text.isNotEmpty()) {
                        mainViewModel.searchDb(it.toString())
                    }

                }
            }
        }





//        mainViewModel.charactersList.observe(viewLifecycleOwner) {
//            itemAdapter.differ.submitList(it)
//        }
//        itemAdapter.differ.submitList(mainViewModel.listUiState.value.currentList)


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


    fun EditText.onSubmit(func: (String) -> Unit, str: String) {
        setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                func(str)
            }

            true

        }
    }

}


































