package tuna.core.domain

import tuna.core.data.model.request.EscapeRoomRequest
import tuna.core.data.model.request.MovieInfoRequest
import tuna.core.data.model.response.EscapeRoomResponse
import tuna.core.data.model.response.MovieInfo
import tuna.core.data.model.response.MovieInfoResponse
import tuna.core.data.network.ApiResult

interface EscapeRoomRepository {
    suspend fun getEscapeRooms(escapeRoomRequest: EscapeRoomRequest): ApiResult<EscapeRoomResponse>
    suspend fun getEscapeRoomMoviesInfo(movieInfoRequest: MovieInfoRequest): ApiResult<MovieInfoResponse>
}