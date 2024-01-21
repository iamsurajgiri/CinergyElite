package tuna.cinergyelite.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tuna.core.data.model.request.EscapeRoomRequest
import tuna.core.data.model.request.MovieInfoRequest
import tuna.core.data.model.response.EscapeRoomResponse
import tuna.core.data.model.response.MovieInfoResponse
import tuna.core.data.network.ApiResult
import tuna.core.domain.EscapeRoomRepository

class EscapeRoomViewModel(
    private val escapeRoomRepository: EscapeRoomRepository
) : ViewModel(){
    private val _escapeRooms = MutableLiveData<ApiResult<EscapeRoomResponse>>()
    val escapeRooms: LiveData<ApiResult<EscapeRoomResponse>> = _escapeRooms

    private val _movieInfo = MutableLiveData<ApiResult<MovieInfoResponse>>()
    val movieInfo: LiveData<ApiResult<MovieInfoResponse>> = _movieInfo


    fun getEscapeRooms(escapeRoomRequest: EscapeRoomRequest){
        _escapeRooms.postValue(ApiResult.Loading)
        viewModelScope.launch {
            _escapeRooms.postValue(escapeRoomRepository.getEscapeRooms(escapeRoomRequest))
        }
    }

    fun getMovieInfo(movieInfoRequest: MovieInfoRequest){
        _movieInfo.postValue(ApiResult.Loading)
        viewModelScope.launch {
            _movieInfo.postValue(escapeRoomRepository.getEscapeRoomMoviesInfo(movieInfoRequest))
        }
    }
}