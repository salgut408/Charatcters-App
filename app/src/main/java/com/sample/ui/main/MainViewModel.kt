package com.sample.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.domain.repositories.CharacterRepository
import com.sample.domain.use_cases.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState(loading = true,))
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    init {
        loadList()
        loadTitle()
    }

    suspend fun searchDb(searchQuery: String) = viewModelScope.launch() {
        characterRepository.searchDbFlow(searchQuery).collectLatest { newItems ->
            _listUiState.emit(ListUiState(loading = false, currentList = newItems))
        }
    }

    private fun loadTitle() = viewModelScope.launch {
        val title = characterRepository.getTitleBarString()
        _title.emit(title)
        Log.e("TITLE", _title.value.toString())
    }

    private fun loadList() = viewModelScope.launch{
        getCharactersListUseCase.invoke().collectLatest { allItems ->
            _listUiState .emit(ListUiState(loading = false, currentList = allItems))
        }
    }
}