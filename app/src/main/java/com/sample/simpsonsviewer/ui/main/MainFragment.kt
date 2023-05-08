package com.sample.simpsonsviewer.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.headerDisplay.text = mainViewModel.simpsonModel.value?.toString()
        val root : View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        mainViewModel.charactersList.observe(viewLifecycleOwner,
        Observer<List<RelatedTopicModel>> {standing ->
            standing.apply { simpsonAdapter.differ.submitList(standing) }
        })

//        mainViewModel.simpsonModel.observe(viewLifecycleOwner){
//            Log.d("FUCK", "$it")
//            binding.headerDisplay.text = it.relatedTopics.toString()
//        }
//        mainViewModel.simpsonModel.observe(viewLifecycleOwner ) {text ->
//            simpsonAdapter.differ.submitList(text.relatedTopics)
//        }


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