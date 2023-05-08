package com.sample.simpsonsviewer.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.simpsonsviewer.data.constants.Constants.Companion.BASE_URL
import com.sample.simpsonsviewer.databinding.FragmentMainBinding
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.domain_models.SimpsonsModel
import com.sample.simpsonsviewer.ui.main.adapters.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
@AndroidEntryPoint
class MainFragment : Fragment() {



    private  val mainViewModel by viewModels<MainViewModel>()
    lateinit var simpsonAdapter: ListAdapter
    lateinit var binding: FragmentMainBinding




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater).apply {
            viewModel = mainViewModel
        }
        binding.headerDisplay.text = mainViewModel.simpsonModel.value?.toString()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner

    setUpRecyclerView()
        mainViewModel.simpsonModel.observe(viewLifecycleOwner ) {text ->
            text.apply {
                binding.headerDisplay.text = text.toString()
            }
        }

        mainViewModel.charactersList.observe(viewLifecycleOwner) { list ->
            list.apply {
                simpsonAdapter.differ.submitList(list)
            }
        }
        binding.headerDisplay.text = mainViewModel.simpsonModel.toString()

    }

    private fun setUpRecyclerView() {
        simpsonAdapter = ListAdapter()
        binding.recView.apply {
          adapter = simpsonAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

}