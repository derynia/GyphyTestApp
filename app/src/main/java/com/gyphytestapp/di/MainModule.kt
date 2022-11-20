package com.gyphytestapp.di

import android.content.Context
import androidx.annotation.StringRes
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.gyphytestapp.data.EncryptedSharedPrefs
import com.gyphytestapp.data.db.GifsAppDb
import com.gyphytestapp.data.db.mapper.DataToLoadedMapper
import com.gyphytestapp.data.db.repository.LoadedPicsRepository
import com.gyphytestapp.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    private const val SERVER_MAIN = "https://api.giphy.com/"
    //"?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&amp;q=&amp;limit=25&amp;offset=0&amp;rating=g&amp;lang=en"

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_MAIN)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp)
        .build()

    @Singleton
    @Provides
    fun provideRetrofitClient(): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = okHttp

    @Provides
    @Singleton
    fun provideEncryptedSharedPrefs(@ApplicationContext context: Context) =
        EncryptedSharedPrefs(context)

    @Singleton
    class ResourcesProvider @Inject constructor(
        @ApplicationContext private val context: Context
    ) {
        fun getString(@StringRes stringResId: Int): String {
            return context.getString(stringResId)
        }
    }

    class ProgressIndicator @Inject constructor(
        @ApplicationContext private val context: Context
    ) {
        private val circularProgressDrawable = CircularProgressDrawable(context)

        fun getIndicator(): CircularProgressDrawable {
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            return circularProgressDrawable
        }
    }

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): GifsAppDb =
        GifsAppDb.getInstance(context)

    @Provides
    @Singleton
    fun provideDbRepository(gifsAppDb: GifsAppDb) =
        LoadedPicsRepository(gifsAppDb, DataToLoadedMapper())
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher