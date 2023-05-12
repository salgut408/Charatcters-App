package com.sample.simpsonsviewer.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.databinding.FragmentMainBinding
import com.sample.simpsonsviewer.databinding.ListPaneBinding
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.ui.main.adapters.ItemAdapter
import com.sample.simpsonsviewer.ui.main.adapters.TwoPanelAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : AbstractListDetailFragment() {

    private val mainViewModel by viewModels<MainViewModel>()
    lateinit var simpsonAdapter: ItemAdapter

    private var _binding: ListPaneBinding? = null
    private val binding get() = _binding!!




    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onListPaneViewCreated(view, savedInstanceState)
        setUpRecyclerView()


        simpsonAdapter.setOnItemClickListener {
            this.findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            )
        }


        var job: Job? = null


        binding.etSearch

        binding.etSearch.addOnEditTextAttachedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(3000)
                editable.editText?.text?. let {
//                    if()
                    if (editable.editText!!.isFocused) {
                        mainViewModel.searchDb(it.toString())
                        simpsonAdapter.differ.submitList(mainViewModel.searchResponse.value)
                    }
                }
            }
        }

        mainViewModel.apply {

        }

        mainViewModel.searchResponse.observe(viewLifecycleOwner) {response ->
            if (response.isEmpty()){
                Log.e("VMFRAG", "respempty")
            } else {
                simpsonAdapter.differ.submitList(response)

            }
        }



        mainViewModel.charactersList.observe(viewLifecycleOwner) {
            simpsonAdapter.differ.submitList(it)
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
        simpsonAdapter = ItemAdapter()
        binding.recView.apply {
            adapter = simpsonAdapter
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


































