package com.example.danny.gitstar.di.module

import android.app.Application
import android.content.Context
import com.example.danny.gitstar.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    internal fun provideApplication(): App {
        return app
    }

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return app.applicationContext
    }
}