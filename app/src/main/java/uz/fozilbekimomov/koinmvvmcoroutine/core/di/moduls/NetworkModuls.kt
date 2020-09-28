package uz.fozilbekimomov.koinmvvmcoroutine.core.di.moduls

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import timber.log.Timber
import uz.fozilbekimomov.koinmvvmcoroutine.BuildConfig
import uz.fozilbekimomov.koinmvvmcoroutine.core.network.Constants
import uz.fozilbekimomov.koinmvvmcoroutine.core.network.service.HomeServices
import java.io.File


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */

private const val CACHE_FILE_SIZE: Long = 30 * 1000 * 1000 // 30 Mib

val networkModule = module {

    single {
        retrofit(get(), Constants.BASE_URL)
    }

    single<Call.Factory> {
        val cacheFile = cacheFile(androidContext())
        val cache = cache(cacheFile)
        okhttp(cache)
    }

    single {
        get<Retrofit>().create(HomeServices::class.java)
    }

}

private fun retrofit(callFactory: Call.Factory, baseUrl: String):Retrofit=Retrofit.Builder()
    .callFactory(callFactory)
    .baseUrl(baseUrl)
    .addConverterFactory(Json{
        isLenient = true
        ignoreUnknownKeys = true
    }.asConverterFactory("application/json".toMediaType()))
    .build()

val interceptorBody=object : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl =
            request.url.newBuilder().addQueryParameter("api_key",Constants.API_KEY).addQueryParameter("language","ru-Ru").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}


private val interceptor: Interceptor
    get() = HttpLoggingInterceptor().apply {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i("koinmvvmcoroutine ##  %s", message)
            }
        })
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }


private fun cacheFile(context: Context) = File(context.filesDir, "my_own_created_cache").apply {
    if (!this.exists())
        mkdirs()
}

private fun cache(cacheFile: File) = Cache(cacheFile, CACHE_FILE_SIZE)


private fun okhttp(cache: Cache) = OkHttpClient().newBuilder()
    .addInterceptor(interceptor)
    .addNetworkInterceptor(interceptorBody)
    .cache(cache)
    .build()
