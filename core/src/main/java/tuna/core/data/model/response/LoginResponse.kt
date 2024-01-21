package tuna.core.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("response")
    val response: String,
    @SerializedName("user")
    val user : User?
)

data class User(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("member_id")
    val memberId: Int?
)
