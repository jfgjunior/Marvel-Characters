package com.marvel_characters.di

import com.marvel_characters.BuildConfig
import com.marvel_characters.common.network.Cache
import com.marvel_characters.common.network.MarvelApi
import com.marvel_characters.common.network.MarvelCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.MessageDigest
import javax.inject.Singleton

@Module
interface ApplicationModule {

    @Binds
    fun bindMarvelCache(marvelCache: MarvelCache): Cache

    companion object {
        @Singleton
        @Provides
        fun provideMarvelApi(): MarvelApi {
            return Retrofit.Builder()
                .baseUrl(MarvelApi.URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val original: Request = chain.request()
                            val originalHttpUrl: HttpUrl = original.url()
                            val ts = System.currentTimeMillis().toString()
                            val hash =
                                "$ts${BuildConfig.privateKey}${BuildConfig.publicKey}".toMD5()
                            val url = originalHttpUrl.newBuilder()
                                .addQueryParameter(API_KEY_QUERY, BuildConfig.publicKey)
                                .addQueryParameter(HASH_QUERY, hash)
                                .addQueryParameter(TIMESTAMP_QUERY, ts)
                                .build()
                            val requestBuilder: Request.Builder = original.newBuilder()
                                .url(url)
                            val request: Request = requestBuilder.build()
                            chain.proceed(request)
                        }
                        .build()
                )
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MarvelApi::class.java)
        }

        private fun String.toMD5(): String {
            val bytes = MessageDigest.getInstance(ALGORITHM_TYPE).digest(this.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }

        private const val ALGORITHM_TYPE = "MD5"
        private const val API_KEY_QUERY = "apikey"
        private const val HASH_QUERY = "hash"
        private const val TIMESTAMP_QUERY = "ts"
    }
}