package ru.isaev.swstest.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CiceroneModule {


    @Provides
    @Singleton
    fun getCiceroneInstance(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @Singleton
    fun getNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun getRouter(cicerone: Cicerone<Router>): Router =
        cicerone.router

}