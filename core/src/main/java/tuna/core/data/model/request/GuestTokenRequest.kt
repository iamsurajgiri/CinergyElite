package tuna.core.data.model.request

import com.google.gson.annotations.SerializedName

data class GuestTokenRequest(
    @SerializedName("device_id")
    val deviceId: String,
    @SerializedName("secret_key")
    val secretKey: String,
    @SerializedName("device_type")
    val deviceType: Int = 2,
    @SerializedName("push_token")
    val pushToken: String = ""
)
