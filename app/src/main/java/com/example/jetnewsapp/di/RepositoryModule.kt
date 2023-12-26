package com.example.jetnewsapp.di

import com.example.jetnewsapp.data.remote.NewsApi
import com.example.jetnewsapp.data.repository.NewsRepositoryImpl
import com.example.jetnewsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(newsApi: NewsApi): NewsRepository {
       return NewsRepositoryImpl(newsApi)
    }

}