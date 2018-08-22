package com.example.danny.gitstar.di.component

import com.example.danny.gitstar.SearchActivityTest
import com.example.danny.gitstar.data.GithubService
import com.example.danny.gitstar.di.module.ApiModule
import com.example.danny.gitstar.di.module.ApiModuleMock
import com.example.danny.gitstar.di.module.GithubServiceMock
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, GithubServiceMock::class])
interface TestComponent : AppComponent {
    fun inject(activity: SearchActivityTest)
}