package uz.fozilbekimomov.koinmvvmcoroutine.core.utils

import uz.fozilbekimomov.koinmvvmcoroutine.core.models.HomeData


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */


data class DataState (
    val showProgress: Boolean,
    val movies: Event<HomeData>?,
    val error: Event<String>?
)