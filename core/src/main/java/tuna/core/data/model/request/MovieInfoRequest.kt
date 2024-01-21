package tuna.core.data.model.request

import com.google.gson.annotations.SerializedName

data class MovieInfoRequest(
    @SerializedName("movie_id")
    val movieId: String,
    @SerializedName("device_id")
    val deviceId: String,
    @SerializedName("device_type")
    val deviceType: Int = 2,
    @SerializedName("location_id")
    val locationId: Int = 5,
)
