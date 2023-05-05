package com.sample.simpsonsviewer.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.domain_models.SimpsonsModel
import com.sample.simpsonsviewer.domain.repositories.SimpsonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
        getInfo()
        getInfoForDb()
        callList()
    }

    fun getInfo() = viewModelScope.launch {
        val model = simpsonsRepository.getSimpsonsModel()
        Log.e("VMODEL", model.toString())
        _simpsonModel.postValue(model)
    }

    fun callList() = viewModelScope.launch {
        val list = simpsonsRepository.getSimpsonsList()
        Log.e("VMODEL", list.toString())
        _charactersList.postValue(list)

    }

    fun getInfoForDb() = viewModelScope.launch {
        simpsonsRepository.saveInDatabase()
    }

}