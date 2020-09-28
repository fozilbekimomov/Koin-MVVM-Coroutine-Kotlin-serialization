package uz.fozilbekimomov.koinmvvmcoroutine.core.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */


const val MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185"

fun View.changeDrawableColor(solidColor: String, strokeColor: String) {
    val gd = this.background.current as GradientDrawable
    gd.setColor(Color.parseColor(solidColor))
    gd.cornerRadii = floatArrayOf(30f, 30f, 30f, 30f, 0f, 0f, 30f, 30f)
    gd.setStroke(8, Color.parseColor(strokeColor))
}

const val APP_HEADER_ICON="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_short-8e7b30f73a4020692ccca9c88bafe5dcb6f8a62a4c6bc55cd9ba82bb2cd95f6c.svg"