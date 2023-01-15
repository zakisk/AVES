package com.aves.ui.photos_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aves.data.repositories.NetworkRepository
import com.aves.domain.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class PhotosViewModel @Inject constructor(private val repository: NetworkRepository) : ViewModel() {

    private val _state: MutableStateFlow<PhotoScreenState> = MutableStateFlow(PhotoScreenState())
    val state: StateFlow<PhotoScreenState> = _state.asStateFlow()

    var isShowDialog by mutableStateOf(false)

    init {
        getPhotos()
    }

    private fun getPhotos() {
        repository.getPhotos().onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> _state.value = PhotoScreenState(isLoading = true)
                is DataState.Success -> dataState.data?.let {
                    _state.value = PhotoScreenState(photos = it)
                }
                is DataState.Error -> {
                    _state.value = PhotoScreenState(error = dataState.message)
                    isShowDialog = true
                }
            }
        }.launchIn(viewModelScope)
    }
}