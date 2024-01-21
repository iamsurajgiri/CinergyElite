package tuna.cinergyelite.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tuna.core.data.model.request.GuestTokenRequest
import tuna.core.data.model.request.LoginRequest
import tuna.core.data.model.response.GuestTokenResponse
import tuna.core.data.model.response.LoginResponse
import tuna.core.data.network.ApiResult
import tuna.core.domain.LoginRepository

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel(){
    val token = MutableLiveData<ApiResult<GuestTokenResponse>>()

    val login = MutableLiveData<ApiResult<LoginResponse>>()


    fun getGuestToken(guestTokenRequest: GuestTokenRequest){
        token.postValue(ApiResult.Loading)
        viewModelScope.launch {
            token.postValue(loginRepository.getGuestToken(guestTokenRequest))
        }
    }

    fun guestLogin(loginRequest: LoginRequest){
        login.postValue(ApiResult.Loading)
        viewModelScope.launch {
            login.postValue(loginRepository.login(loginRequest))
        }
    }
}