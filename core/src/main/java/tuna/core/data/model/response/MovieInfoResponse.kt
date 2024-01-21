package tuna.core.data.model.response

import com.google.gson.annotations.SerializedName

data class MovieInfoResponse(
    @SerializedName("response") val response: String,
    @SerializedName("movie_info") val movieInfo: MovieInfo,
    @SerializedName("er_tickets") val erTickets: String,
)

data class MovieInfo(
    @SerializedName("Title") val title: String,
    @SerializedName("Rating") val rating: String,
    @SerializedName("RunTime") val runTime: String,
    @SerializedName("Synopsis") val synopsis: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("date_list") val dateList: List<String>,
    @SerializedName("show_times") val showTimes: List<ShowTime>
)

data class ShowTime(
    @SerializedName("date") val date: String,
    @SerializedName("sessions") val sessions: List<Session>
)

data class Session(
    @SerializedName("SessionId") val sessionId: String,
    @SerializedName("Showtime") val showtime: String
)
