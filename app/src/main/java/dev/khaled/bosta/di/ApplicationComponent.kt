package dev.khaled.bosta.di

import dagger.Component
import dev.khaled.bosta.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}