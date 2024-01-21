package tuna.core.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import tuna.core.data.model.request.EscapeRoomRequest
import tuna.core.data.model.request.GuestTokenRequest
import tuna.core.data.model.request.LoginRequest
import tuna.core.data.model.request.MovieInfoRequest
import tuna.core.data.model.response.EscapeRoomResponse
import tuna.core.data.model.response.GuestTokenResponse
import tuna.core.data.model.response.LoginResponse
import tuna.core.data.model.response.MovieInfoResponse

interface CinergyService {

    //token
    @POST("guestToken")
    suspend fun getGuestToken(
        @Body request: GuestTokenRequest
    ): Response<GuestTokenResponse>

    //login
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    //escape room movies
    @POST("escapeRoomMovies")
    suspend fun getEscapeRoomMovies(
        @Body request: EscapeRoomRequest
    ): Response<EscapeRoomResponse>

    //movies info
    @POST("getMovieInfo")
    suspend fun getEscapeRoomMoviesInfo(
        @Body request: MovieInfoRequest
    ): Response<MovieInfoResponse>

}