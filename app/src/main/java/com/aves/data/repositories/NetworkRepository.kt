package com.aves.data.repositories

import com.aves.data.network_util.emitResponse
import com.aves.data.services.NetworkService
import com.aves.domain.models.global.Photo
import com.aves.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val service: NetworkService
) {

    fun getPhotos(): Flow<DataState<List<Photo>>> = flow {
        emit(DataState.Loading<Nothing>())
        emitResponse(
            response = service.getPhotos(),
            validate = { this != null }
        )
    }

    fun getPhoto(id: String) : Flow<DataState<Photo>> = flow {
        emit(DataState.Loading<Nothing>())
        emitResponse(
            response = service.getPhoto(id),
            validate = { this != null }
        )
    }

}