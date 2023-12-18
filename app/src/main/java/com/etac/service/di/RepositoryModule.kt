package com.etac.service.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
//Repositories will live same as the activity that requires them
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /*@Binds
    abstract fun providesBankRepository(impl: BankRepositoryImpl): BankRepository*/
}