package tuna.core.data.model.request

import com.google.gson.annotations.SerializedName

data class EscapeRoomRequest(
    @SerializedName("device_id")
    val deviceId: String,
    @SerializedName("member_id")
    val memberId: Int?,
    @SerializedName("device_type")
    val deviceType: Int = 2,
    @SerializedName("location_id")
    val locationId: Int = 5
)