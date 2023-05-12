package com.sample.simpsonsviewer.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.repositories.SimpsonsRepository
import com.sample.simpsonsviewer.domain.use_cases.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val simpsonsRepository: SimpsonsRepository,
    private val getCharactersList: GetCharactersListUseCase
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState())
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()

    private val _charactersList: MutableLiveData<List<RelatedTopicModel>> = MutableLiveData()
    val charactersList: LiveData<List<RelatedTopicModel>> get() = _charactersList

    private val _searchResponse: MutableLiveData<List<RelatedTopicModel>> = MutableLiveData()
    val searchResponse: LiveData<List<RelatedTopicModel>> get() = _searchResponse

    init {
        loadList()
    }



    suspend fun searchDb(searchQuery: String) = viewModelScope.launch() {
        val results = simpsonsRepository.searchDb(searchQuery)
        _searchResponse.postValue(results)

        simpsonsRepository.searchDbFlow(searchQuery).collect { newItems ->
            _listUiState.emit(ListUiState(loading = false, currentList = newItems.filter { it.text.contains(searchQuery) }))
            Log.e("VM_SEARCH_FLOW", _listUiState.value.toString())

        }
    }

    suspend fun onSearchEntered(searchQuery: String) {
        val result = simpsonsRepository.searchDb(searchQuery)
    }



    fun loadList() = viewModelScope.launch{
        val currentCharactersList = getCharactersList()
        _charactersList.postValue(currentCharactersList)

        simpsonsRepository.getCharactersFromDbFlow().collect { allItems ->
            _listUiState.emit(ListUiState(loading = false, currentList = allItems))
            Log.d("FLOW_VM", _listUiState.value .toString())
        }


//        setListUiState(
//            loading = false,
//            currentList = listUiState.value.currentList
//        )
    }



    private fun setListUiState(
        loading: Boolean,
        currentList: List<RelatedTopicModel>,
    ) {
        _listUiState.update {
            it.copy(

                loading = loading,
                currentList = currentList,

            )
        }
    }

}