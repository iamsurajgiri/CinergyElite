package tuna.core.data.repository

import tuna.core.data.model.request.GuestTokenRequest
import tuna.core.data.model.request.LoginRequest
import tuna.core.data.model.response.GuestTokenResponse
import tuna.core.data.model.response.LoginResponse
import tuna.core.data.network.ApiResult
import tuna.core.data.network.CinergyService
import tuna.core.domain.LoginRepository

class LoginRepositoryImpl (val cinergyService: CinergyService) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): ApiResult<LoginResponse> {
        return try {
            val response = cinergyService.login(loginRequest)

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

    override suspend fun getGuestToken(guestTokenRequest: GuestTokenRequest): ApiResult<GuestTokenResponse> {
        return try {
            val response = cinergyService.getGuestToken(guestTokenRequest)

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