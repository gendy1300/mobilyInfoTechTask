package com.gendy.bugIt.di

import com.gendy.bugIt.home.data.remote.HomeApis
import com.gendy.bugIt.home.data.repository.HomeRepoImp
import com.gendy.bugIt.home.domain.repositories.HomeRepo
import com.gendy.bugIt.utils.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideHomeApis(remoteDataSource: RetrofitClient): HomeApis {
        return remoteDataSource.buildApi(HomeApis::class.java)
    }


    @Singleton
    @Provides
    fun provideHomeRepo(api: HomeApis): HomeRepo {
        return HomeRepoImp(api)
    }

}