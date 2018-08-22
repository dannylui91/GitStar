package com.example.danny.gitstar.di.module

import com.example.danny.gitstar.data.GithubService
import com.example.danny.gitstar.data.remote.GithubApi
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import javax.inject.Singleton

@Module
class GithubServiceMock {

    @Provides
    @Singleton
    internal fun provideGithubService(githubApi: GithubApi): GithubService {
        return spy(GithubService(githubApi))
    }
}