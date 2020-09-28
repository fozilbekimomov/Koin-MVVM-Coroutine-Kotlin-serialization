package uz.fozilbekimomov.koinmvvmcoroutine.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import uz.fozilbekimomov.koinmvvmcoroutine.core.utils.DataState


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */


interface HomeContract {

    interface View {
        val movieObserver: Observer<DataState>
        fun onMovieData(data: DataState)
    }


    interface VM {
        val moviesLiveData: LiveData<DataState>

        fun loadPopularMovie()

        fun loadLatestMovies()

        fun loadTopRated()

        fun getUpcoming()
    }
}