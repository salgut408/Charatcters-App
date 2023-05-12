package com.sample.simpsonsviewer.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.simpsonsviewer.domain.repositories.SimpsonsRepository
import com.sample.simpsonsviewer.domain.use_cases.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val simpsonsRepository: SimpsonsRepository,
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState(loading = true,))
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()

    init {
        loadList()
    }

    suspend fun searchDb(searchQuery: String) = viewModelScope.launch() {
        simpsonsRepository.searchDbFlow(searchQuery).collectLatest { newItems ->
            _listUiState.emit(ListUiState(loading = false, currentList = newItems))
        }
    }

    private fun loadList() = viewModelScope.launch{
        getCharactersListUseCase.invoke().collectLatest { allItems ->
            _listUiState .emit(ListUiState(loading = false, currentList = allItems))
        }
    }
}