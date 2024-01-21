package tuna.core.data.model.response

import com.google.gson.annotations.SerializedName

data class GuestTokenResponse(
    @SerializedName("response")
    val response: String,
    @SerializedName("token")
    val token : String
)