package tuna.core.data.repository

import tuna.core.data.model.request.EscapeRoomRequest
import tuna.core.data.model.request.MovieInfoRequest
import tuna.core.data.model.response.EscapeRoomResponse
import tuna.core.data.model.response.MovieInfo
import tuna.core.data.model.response.MovieInfoResponse
import tuna.core.data.network.ApiResult
import tuna.core.data.network.CinergyService
import tuna.core.domain.EscapeRoomRepository

class EscapeRoomRepositoryImpl(val cinergyService: CinergyService): EscapeRoomRepository {
    override suspend fun getEscapeRooms(escapeRoomRequest: EscapeRoomRequest): ApiResult<EscapeRoomResponse> {
        return try {
            val response = cinergyService.getEscapeRoomMovies(escapeRoomRequest)

            if (response.isSuccessful){
                response.body()?.let {
                    ApiResult.Success(it)
                }?: ApiResult.Error(response.message()?:"Something went wrong")
            }else{
                ApiResult.Error(response.message())
            }
        }catch (e: Exception){
            ApiResult.Error(e.localizedMessage?: "Something went wrong")
        }
    }

    override suspend fun getEscapeRoomMoviesInfo(movieInfoRequest: MovieInfoRequest): ApiResult<MovieInfoResponse> {
        return try {
            val response = cinergyService.getEscapeRoomMoviesInfo(movieInfoRequest)

            if (response.isSuccessful){
                response.body()?.let {
                    ApiResult.Success(it)
                }?: ApiResult.Error(response.message()?:"Something went wrong")
            }else{
                ApiResult.Error(response.message())
            }
        }catch (e: Exception){
            ApiResult.Error(e.localizedMessage?: "Something went wrong")
        }
    }
}