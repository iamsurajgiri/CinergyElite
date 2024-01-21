package tuna.core.domain

import tuna.core.data.model.request.GuestTokenRequest
import tuna.core.data.model.request.LoginRequest
import tuna.core.data.model.response.GuestTokenResponse
import tuna.core.data.model.response.LoginResponse
import tuna.core.data.network.ApiResult

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): ApiResult<LoginResponse>
    suspend fun getGuestToken(guestTokenRequest: GuestTokenRequest): ApiResult<GuestTokenResponse>
}