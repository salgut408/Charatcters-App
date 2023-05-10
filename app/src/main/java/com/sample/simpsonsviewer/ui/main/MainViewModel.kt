package com.sample.simpsonsviewer.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.domain_models.SimpsonsModel
import com.sample.simpsonsviewer.domain.repositories.SimpsonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val simpsonsRepository: SimpsonsRepository
) : ViewModel() {

    private val _simpsonModel: MutableLiveData<SimpsonsModel> = MutableLiveData()
    val simpsonModel: LiveData<SimpsonsModel> get() = _simpsonModel

    private val _charactersList: MutableLiveData<List<RelatedTopicModel>> = MutableLiveData()
    val charactersList: LiveData<List<RelatedTopicModel>> get() = _charactersList

    val _namesList: MutableLiveData<List<String>> = MutableLiveData()
    val namesList: LiveData<List<String>> get() = _namesList

    private val _searchResponse: MutableLiveData<List<RelatedTopicModel>> = MutableLiveData()
    val searchResponse: LiveData<List<RelatedTopicModel>> get() = _searchResponse

    init {
        getInfoForDb()
        getModelInfo()
        callList()

    }

    fun getNames(list: List<RelatedTopicModel>): List<String>{
        val delim = "-"
       return list.map { it.text.split(delim).toString() }
    }

    fun getModelInfo() = viewModelScope.launch {
        val model = simpsonsRepository.getSimpsonsModel()
        Log.e("VM-MODEL", model.toString())
        _simpsonModel.postValue(model)
//        _charactersList.postValue(model.relatedTopics)

    }

    fun callList() = viewModelScope.launch {
        val list = simpsonsRepository.getCharactersFromDb()
        Log.e("VM-List", list.toString())
        _charactersList.postValue(list)
        _namesList.postValue(getNames(list))

    }

    fun searchDb(searchQuery: String) = viewModelScope.launch {
        val results = simpsonsRepository.searchDb(searchQuery)
        _searchResponse.postValue(results)

    }

    fun getInfoForDb() = viewModelScope.launch {
        simpsonsRepository.saveInDatabase()
    }

    fun callNamesList() = viewModelScope.launch {
//        simpsonsRepository.getAllNamesDb()
    }

}