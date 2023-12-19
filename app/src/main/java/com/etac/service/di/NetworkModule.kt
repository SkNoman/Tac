package com.etac.service.di

import android.util.Base64
import com.etac.service.BuildConfig
import com.etac.service.network.ApiInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        val clientInterceptor = Interceptor { chain: Interceptor.Chain ->
            val username = BuildConfig.USER_NAME
            val password = BuildConfig.PASSWORD
            val credentials = "$username:$password"
            val base64Credentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic $base64Credentials")
                .build()

            chain.proceed(request)
        }


        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }


        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(clientInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()


        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)


    //IF NEED TO ADD QUERY PARAMETERS GLOBALLY THEN USE THIS METHOD
    /*val clientInterceptor = Interceptor { chain: Interceptor.Chain ->
    val username = "your_username"
    val password = "your_password"

    val credentials = "$username:$password"
    val base64Credentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

    val originalRequest = chain.request()
    val url = originalRequest.url.newBuilder()
        .addQueryParameter(KEY_JSON, "true")
        .addQueryParameter(KEY_CLIENT_TYPE, CLIENT_TYPE.toString())
        .addQueryParameter(KEY_CLIENT_VERSION, Constant.CURRENT_BUILD_VERSION.toString())
        .build()

    val request = originalRequest.newBuilder()
        .url(url)
        .addHeader("Authorization", "Basic $base64Credentials")
        .build()

    chain.proceed(request)
}
*/

}