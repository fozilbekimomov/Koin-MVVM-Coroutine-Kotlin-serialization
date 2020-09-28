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
data class MovieListResponse(
    @SerialName(value = "page") val page: Int,
    @SerialName(value = "total_pages") val totalPages: Int,
    @SerialName(value = "results") val movies: ArrayList<Movie>
)