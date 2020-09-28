package uz.fozilbekimomov.koinmvvmcoroutine.core.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import uz.fozilbekimomov.koinmvvmcoroutine.core.di.moduls.networkModule
import uz.fozilbekimomov.koinmvvmcoroutine.core.di.moduls.uiModule


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(networkModule, uiModule))
        }
    }
}