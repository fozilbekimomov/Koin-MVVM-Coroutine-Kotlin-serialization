package uz.fozilbekimomov.koinmvvmcoroutine.core.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */

@Serializable
data class Movie (
    @SerialName("id") val id:Long,
    @SerialName("vote_average") val vote: Float?,
    @SerialName("title") val movieTitle: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("backdrop_path") val backdropPath: String,
    @SerialName("adult") val isAdult: Boolean,
    @SerialName("original_language") val language: String,
    @SerialName("genre_ids") val genres: ArrayList<Int>,
)