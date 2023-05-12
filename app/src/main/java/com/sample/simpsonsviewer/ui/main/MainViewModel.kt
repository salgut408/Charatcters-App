package com.sample.simpsonsviewer.ui.main

import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.*
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.domain_models.SimpsonsModel
import com.sample.simpsonsviewer.domain.repositories.SimpsonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val simpsonsRepository: SimpsonsRepository,
) : ViewModel() {



    private val _charactersList: MutableLiveData<List<RelatedTopicModel>> = MutableLiveData()
    val charactersList: LiveData<List<RelatedTopicModel>> get() = _charactersList

    val _namesList: MutableLiveData<List<String>> = MutableLiveData()
    val namesList: LiveData<List<String>> get() = _namesList

    private val _searchResponse: MutableLiveData<List<RelatedTopicModel>> = MutableLiveData()
    val searchResponse: LiveData<List<RelatedTopicModel>> get() = _searchResponse

    init {
        getInfoForDb()
        callList()
    }

    fun getNames(list: List<RelatedTopicModel>): List<String> {
        val delim = "-"
        return list.map { it.text.split(delim).toString() }
    }



    fun callList() = viewModelScope.launch {
        _charactersList
        val list = simpsonsRepository.getCharactersFromDb()
        Log.e("VM-List", list.toString())
        _charactersList.postValue(list)
        _namesList.postValue(getNames(list))
    }

    suspend fun searchDb(searchQuery: String) = viewModelScope.launch() {
        val results = simpsonsRepository.searchDb(searchQuery)

        _searchResponse.postValue(results)
        Log.e("VM_SEARCH", results.toString())
    }

    suspend fun onSearchEntered(searchQuery: String) {
        val result = simpsonsRepository.searchDb(searchQuery)

        _charactersList.postValue(result)
    }

    fun getInfoForDb() = viewModelScope.launch {
        simpsonsRepository.saveInDatabase()
    }



}