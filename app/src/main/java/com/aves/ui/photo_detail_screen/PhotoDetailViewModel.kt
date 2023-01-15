package com.aves.ui.photo_detail_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aves.data.repositories.NetworkRepository
import com.aves.domain.state.DataState
import com.aves.util.Constants.PHOTO_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val repository: NetworkRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableStateFlow<PhotoDetailScreenState> =
        MutableStateFlow(PhotoDetailScreenState())
    val state: StateFlow<PhotoDetailScreenState> = _state.asStateFlow()

    var isShowDialog by mutableStateOf(false)

    init {
        savedStateHandle.get<String>(PHOTO_ID)?.let {
            getPhoto(it)
        }
    }

    private fun getPhoto(id: String) {
        repository.getPhoto(id = id).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> _state.value = PhotoDetailScreenState(isLoading = true)
                is DataState.Success -> dataState.data?.let {
                    _state.value = PhotoDetailScreenState(photo = it)
                }
                is DataState.Error -> {
                    _state.value = PhotoDetailScreenState(error = dataState.message)
                    isShowDialog = true
                }
            }
        }.launchIn(viewModelScope)
    }

}