package com.example.danny.gitstar.di.module

import com.example.danny.gitstar.data.remote.GithubApi
import org.mockito.Mockito.mock
import retrofit2.Retrofit

class ApiModuleMock: ApiModule() {

    override fun provideGithubApi(retrofit: Retrofit): GithubApi =
            mock(GithubApi::class.java)
}