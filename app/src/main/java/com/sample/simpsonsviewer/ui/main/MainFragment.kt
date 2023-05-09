package com.sample.simpsonsviewer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.databinding.FragmentMainBinding
import com.sample.simpsonsviewer.ui.main.adapters.ItemAdapter
import com.sample.simpsonsviewer.ui.main.adapters.TwoPanelAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : AbstractListDetailFragment() {

    private  val mainViewModel by viewModels<MainViewModel>()
    lateinit var simpsonAdapter: ItemAdapter
    lateinit var twoPaneAdapter: TwoPanelAdapter

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDetailPaneNavHostFragment(): NavHostFragment {
        return NavHostFragment.create(R.navigation.nav_graph)
    }

    override fun onCreateListPaneView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onListPaneViewCreated(view, savedInstanceState)
       setUpRecyclerView()
        simpsonAdapter.setOnItemClickListener { relatedModel ->

            openDetails(
                MainFragmentDirections.actionMainFragmentToDetailFragment(relatedModel).actionId
            )
        }
        mainViewModel.charactersList.observe(viewLifecycleOwner){
            simpsonAdapter.differ.submitList(it)
        }
    }

    private fun openDetails(destinationId: Int){
        val navController = detailPaneNavHostFragment.navController
        navController.navigate(
            destinationId,
            null,
            NavOptions.Builder()
                .setPopUpTo(navController.graph.startDestinationId, true)
                .apply {
                    if (slidingPaneLayout.isOpen) {
//                        setEnterAnim(R.anim.nav_default_enter_anim)
//                        setExitAnim(R.anim.nav_default_exit_anim)
                    }
                }
                .build()
        )
        slidingPaneLayout.open()
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentMainBinding.inflate(inflater, container, false)
//        val root : View = binding.root
//        return root
//    }



//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setUpRecyclerView()
//        simpsonAdapter.setOnItemClickListener {
//            this.findNavController().navigate(
//                MainFragmentDirections.actionMainFragmentToDetailFragment(it)
//            )
//        }
//
//        mainViewModel.charactersList.observe(viewLifecycleOwner){
//            simpsonAdapter.differ.submitList(it)
//        }
//
//
//
//    }

    private fun setUpRecyclerView() {
        simpsonAdapter = ItemAdapter()
        binding.recView.apply {
          adapter = simpsonAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }


    companion object {
        val map = mapOf(
            "first" to R.id.mainFragment,
            "second" to R.id.detailFragment,
        )
    }

}

