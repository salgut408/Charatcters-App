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
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState(loading = true, currentList = listOf()))
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()

    init {
        loadList()
    }

    suspend fun searchDb(searchQuery: String) = viewModelScope.launch() {
        simpsonsRepository.searchDbFlow(searchQuery).collectLatest { newItems ->
            _listUiState.emit(ListUiState(loading = false, currentList = newItems))
            Log.e("VM_SEARCH_FLOW", _listUiState.value.toString())
        }
    }

//    suspend fun onSearchEntered(searchQuery: String) {
//        val result = simpsonsRepository.searchDb(searchQuery)
//    }


    fun loadList() = viewModelScope.launch{
        getCharactersListUseCase.invoke().collectLatest { allItems ->
            _listUiState.emit(ListUiState(loading = false, currentList = allItems))
            Log.d("FLOW_VM", _listUiState.value .toString())
        }
    }
}