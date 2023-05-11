package com.sample.simpsonsviewer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : AbstractListDetailFragment() {

    private  val mainViewModel by viewModels<MainViewModel>()
    lateinit var simpsonAdapter: ItemAdapter

    private var _binding: ListPaneBinding? = null
    private val binding get() = _binding!!




    private fun setUpRecyclerView() {
        simpsonAdapter = ItemAdapter()
        binding.recView.apply {
          adapter = simpsonAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onListPaneViewCreated(view, savedInstanceState)
                setUpRecyclerView()
        simpsonAdapter.setOnItemClickListener {
            this.findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            )
        }
        var job: Job? = null
        binding.etSearch.addOnEditTextAttachedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(100)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        mainViewModel.searchDb(editable.toString())
                    }
                }
            }
        }
//        mainViewModel.searchResponse.observe(viewLifecycleOwner) {response ->
//            simpsonAdapter.differ.submitList(response)
//        }

        mainViewModel.charactersList.observe(viewLifecycleOwner){
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



    private fun openDetails(destinationId: Int) {
        val detailNavController = detailPaneNavHostFragment.navController
        detailNavController.navigate(
            destinationId,
            null,
            NavOptions.Builder()
                .setPopUpTo(detailNavController.graph.startDestinationId, true)

                .build()
        )
        slidingPaneLayout.open()
    }


}


































