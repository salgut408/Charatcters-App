package com.sample.simpsonsviewer.di

import android.content.Context
import androidx.room.Room
import com.sample.simpsonsviewer.data.constants.Constants.Companion.BASE_URL
import com.sample.simpsonsviewer.data.db.RelatedTopicDao
import com.sample.simpsonsviewer.data.db.CharacterDatabase
import com.sample.simpsonsviewer.data.remote.api_service.SimpsonsApi
import com.sample.simpsonsviewer.data.repository.SimpsonsRepositoryImpl
import com.sample.simpsonsviewer.domain.repositories.SimpsonsRepository
import com.sample.simpsonsviewer.domain.use_cases.GetCharactersListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDao(simpsonsDatabase: CharacterDatabase): RelatedTopicDao = simpsonsDatabase.getDao()

    @Provides
    fun provideCharactersListUseCase(
        simpsonsRepository: SimpsonsRepository,
    ): GetCharactersListUseCase = GetCharactersListUseCase(simpsonsRepository)




    @Provides
    fun provideSimpsonsRepository(
        simpsonsApi: SimpsonsApi,
        relatedTopicDao: RelatedTopicDao
    ): SimpsonsRepository = SimpsonsRepositoryImpl(simpsonsApi,  relatedTopicDao)


    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): CharacterDatabase =
        Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "simpson_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideSimpsonsApi(okHttpClient: OkHttpClient): SimpsonsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SimpsonsApi::class.java)
}