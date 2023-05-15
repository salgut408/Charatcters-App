package com.sample.di

import android.content.Context
import androidx.room.Room
import com.sample.data.constants.Constants.Companion.BASE_URL
import com.sample.data.db.RelatedTopicDao
import com.sample.data.db.CharacterDatabase
import com.sample.data.remote.api_service.CharacterApi
import com.sample.data.repository.CharacterRepositoryImpl
import com.sample.domain.repositories.CharacterRepository
import com.sample.domain.use_cases.GetCharactersListUseCase
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
    fun provideDao(characterDatabase: CharacterDatabase): RelatedTopicDao = characterDatabase.getDao()

    @Provides
    fun provideCharactersListUseCase(
        simpsonsRepository: CharacterRepository,
    ): GetCharactersListUseCase = GetCharactersListUseCase(simpsonsRepository)




    @Provides
    fun provideCharacterRepository(
        characterApi: CharacterApi,
        relatedTopicDao: RelatedTopicDao
    ): CharacterRepository = CharacterRepositoryImpl(characterApi,  relatedTopicDao)


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
    fun provideCharacterApi(okHttpClient: OkHttpClient): CharacterApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApi::class.java)
}