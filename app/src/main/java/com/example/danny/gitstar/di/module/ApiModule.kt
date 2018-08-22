package com.example.danny.gitstar.di.module

import com.example.danny.gitstar.data.remote.GithubApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
open class ApiModule {

    @Provides
    @Singleton
    internal open fun provideGithubApi(retrofit: Retrofit): GithubApi =
        retrofit.create(GithubApi::class.java)
}