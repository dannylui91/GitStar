package com.example.danny.gitstar

import android.app.Application
import android.content.Context
import com.example.danny.gitstar.di.ComponentReflectionInjector
import com.example.danny.gitstar.di.component.AppComponent
import com.example.danny.gitstar.di.component.DaggerAppComponent
import com.example.danny.gitstar.di.module.AppModule
import timber.log.Timber

class App : Application() {

    companion object {
        operator fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

    private var appComponent: AppComponent? = null
    private var appInjector: ComponentReflectionInjector<AppComponent>? = null

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    var component: AppComponent?
        get() {
            if (appComponent == null) {
                appComponent = DaggerAppComponent.builder()
                        .appModule(AppModule(this))
                        .build()
            }
            return appComponent
        }
        set(appComponent) {
            this.appComponent = appComponent
        }

    fun getInjector(): ComponentReflectionInjector<AppComponent> {
        if (appInjector == null) {
            appInjector = ComponentReflectionInjector<AppComponent>(AppComponent::class.java, component)
        }
        return appInjector as ComponentReflectionInjector<AppComponent>
    }

    fun setInjector(appComponent: AppComponent) {
        appInjector = ComponentReflectionInjector<AppComponent>(AppComponent::class.java, appComponent)
    }
}