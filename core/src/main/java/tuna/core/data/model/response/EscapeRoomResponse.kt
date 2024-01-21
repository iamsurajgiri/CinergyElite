package tuna.core.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EscapeRoomResponse(
    @SerializedName("response")
    val response: String,
    @SerializedName("er_tickets")
    val erTickets: String,
    @SerializedName("escape_rooms_movies")
    val escapeRoomsMovies: List<EscapeRoomMovie>
)

data class EscapeRoomMovie(
    @SerializedName("ID")
    val id: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Rating")
    val rating: String,
    @SerializedName("RunTime")
    val runTime: String,
    @SerializedName("Synopsis")
    val synopsis: String,
    @SerializedName("image_url")
    val imageUrl: String
): Serializable
