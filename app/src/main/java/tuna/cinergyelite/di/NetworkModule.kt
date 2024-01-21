package tuna.cinergyelite.di

import com.google.gson.GsonBuilder
import tuna.core.data.network.CinergyService
import tuna.core.data.repository.EscapeRoomRepositoryImpl
import tuna.core.data.repository.LoginRepositoryImpl
import tuna.core.domain.EscapeRoomRepository
import tuna.core.domain.LoginRepository
import tuna.core.constansts.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tuna.cinergyelite.main.HeaderInterceptor

val networkModule = module {
    val gson = GsonBuilder().setLenient().create()

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(CinergyService::class.java)
    }

    single<LoginRepository> {LoginRepositoryImpl(get()) }
    single<EscapeRoomRepository> {EscapeRoomRepositoryImpl(get()) }


}