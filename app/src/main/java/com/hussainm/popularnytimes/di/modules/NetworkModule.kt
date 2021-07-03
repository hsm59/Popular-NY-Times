package com.hussainm.popularnytimes.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hussainm.popularnytimes.BuildConfig
import com.hussainm.popularnytimes.network.RemoteDataStore
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpCache(context: Context): okhttp3.Cache {
        val cacheSize = 1 * 1024
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideHttpInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val originalRequest = chain.request()

            val originalHttpUrl = originalRequest.url


            val modifiedHttpUrl = originalHttpUrl.newBuilder().apply {
                addQueryParameter(API_KEY, BuildConfig.API_KEY)
            }.build()

            val builder = originalRequest.newBuilder().apply {
                method(originalRequest.method, originalRequest.body)
                url(modifiedHttpUrl)
            }

            chain.proceed(builder.build())
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply {
            level = if (!BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY
        }

        return loggingInterceptor
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: okhttp3.Cache?,
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor?
    ): OkHttpClient {
        return OkHttpClient().newBuilder().apply {

            connectTimeout(
                TIMEOUT.toLong(),
                TimeUnit.SECONDS
            )

            readTimeout(
                TIMEOUT.toLong(),
                TimeUnit.SECONDS
            )

            writeTimeout(
                TIMEOUT.toLong(),
                TimeUnit.SECONDS
            )

            cache(cache)

            interceptor?.let { addInterceptor(it) }

            addInterceptor(loggingInterceptor)
        }.build()

    }


    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson?,
        okHttpClient: OkHttpClient?
    ): Retrofit = Retrofit.Builder().apply {
        gson?.let { addConverterFactory(GsonConverterFactory.create(gson)) }
        baseUrl(BuildConfig.BASE_URL)
        okHttpClient?.let { client(okHttpClient) }
    }.build()


    @Provides
    @Singleton
    fun provideRemoteDataStore(retrofit: Retrofit): RemoteDataStore = RemoteDataStore(retrofit)


    companion object {
        private const val TIMEOUT = 90
        private const val API_KEY = "api-key"
    }
}