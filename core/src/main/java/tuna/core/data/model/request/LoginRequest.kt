package tuna.core.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("device_id")
    val deviceId: String,
    @SerializedName("login_type")
    val loginType: Int = 2,
    @SerializedName("device_type")
    val deviceType: Int = 2,
)