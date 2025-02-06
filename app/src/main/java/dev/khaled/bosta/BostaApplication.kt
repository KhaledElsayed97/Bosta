package dev.khaled.bosta

import android.app.Application
import dev.khaled.bosta.di.ApplicationComponent
import dev.khaled.bosta.di.DaggerApplicationComponent

class BostaApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}