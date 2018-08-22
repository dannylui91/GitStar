package com.example.danny.gitstar.di.component

import com.example.danny.gitstar.data.GithubService
import com.example.danny.gitstar.di.module.ApiModule
import com.example.danny.gitstar.di.module.AppModule
import com.example.danny.gitstar.ui.search.SearchActivity
import com.example.danny.gitstar.ui.search.SearchPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {
    /** Activities */
    fun inject(activity: SearchActivity)

    /** Presenters */
    fun inject(presenter: SearchPresenter)

}