package uz.fozilbekimomov.koinmvvmcoroutine.core.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.fozilbekimomov.koinmvvmcoroutine.core.models.response.MovieListResponse


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */


interface HomeServices {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieListResponse
}