package uz.fozilbekimomov.koinmvvmcoroutine.core.models

import uz.fozilbekimomov.koinmvvmcoroutine.core.models.response.Movie


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */


data class HomeData(val categoryId: Int, val categoryTitle: String, val data: ArrayList<Movie>?)