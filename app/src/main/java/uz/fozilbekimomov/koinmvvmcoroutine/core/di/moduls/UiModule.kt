package uz.fozilbekimomov.koinmvvmcoroutine.core.di.moduls

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.fozilbekimomov.koinmvvmcoroutine.ui.home.HomeVM
import uz.fozilbekimomov.koinmvvmcoroutine.ui.movieList.MovieListVM


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */

val uiModule = module {
    viewModel {
        HomeVM(get())
    }

    viewModel {
        MovieListVM(get())
    }
}