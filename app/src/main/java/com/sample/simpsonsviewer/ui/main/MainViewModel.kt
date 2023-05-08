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

    init {
        getInfoForDb()
        getModelInfo()
        callList()
        Log.e("VM-MODELinit", simpsonModel.value.toString())

    }

    fun getModelInfo() = viewModelScope.launch {
        val model = simpsonsRepository.getSimpsonsModel()
        Log.e("VM-MODEL", model.toString())
        _simpsonModel.postValue(model)
    }

    fun callList() = viewModelScope.launch {
        val list = simpsonsRepository.getCharactersFromDb()
        Log.e("VM-List", list.toString())
        _charactersList.postValue(list.value)

    }

    fun getInfoForDb() = viewModelScope.launch {
        simpsonsRepository.saveInDatabase()
    }

}