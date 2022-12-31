package com.example.termcommandsandroid.di

import android.content.Context
import com.example.termcommandsandroid.CoreLocalHelperImpl
import com.example.termcommandsandroid.CoreLocalHelper
import com.example.termcommandsandroid.api.RestApi
import com.example.termcommandsandroid.domain.repository.TermRepository
import com.example.termcommandsandroid.repository.TermRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserInfoModules {
    @Provides
    @Singleton
    fun providerGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,localHelper: CoreLocalHelper): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain ->
                val request: Request =
                    chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer ${localHelper.getAuthorizationToken()}")
                        .addHeader("app-language", "tr")
                        .build()

                chain.proceed(request)
            })
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://159.65.164.9:8090/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()


    }
    @Provides
    @Singleton
    fun provideRetrofitClient(@ApplicationContext context: Context,
    ): CoreLocalHelper {
        return CoreLocalHelperImpl(context)


    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }
    @Provides
    @Singleton
    fun provideUserInfoRepository(apiService: RestApi): TermRepository {
        return TermRepositoryImpl(apiService)

        //nesnelerin oluşturma kurallarının belirlendiği yer
    }
}