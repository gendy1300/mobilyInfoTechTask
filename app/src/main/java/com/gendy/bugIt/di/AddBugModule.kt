package com.gendy.bugIt.di

import com.gendy.bugIt.addBug.data.remote.AddBugApis
import com.gendy.bugIt.addBug.data.repository.AddABugRepoImp
import com.gendy.bugIt.addBug.domain.repositories.AddBugRepo
import com.gendy.bugIt.utils.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddBugModule {

    @Singleton
    @Provides
    fun provideAddBugApis(remoteDataSource: RetrofitClient): AddBugApis {
        return remoteDataSource.buildApi(AddBugApis::class.java)
    }


    @Singleton
    @Provides
    fun provideAddABugRepo(api: AddBugApis): AddBugRepo {
        return AddABugRepoImp(api)
    }
}