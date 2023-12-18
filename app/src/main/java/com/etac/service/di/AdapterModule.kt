package com.etac.service.di

import com.etac.service.adapters.ServiceHistoryListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Provides
    @Singleton
    fun provideServiceHistoryListAdapter(): ServiceHistoryListAdapter {
        return ServiceHistoryListAdapter()
    }
}

