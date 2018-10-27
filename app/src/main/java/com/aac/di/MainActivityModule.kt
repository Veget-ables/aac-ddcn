package com.aac.di

import com.aac.ui.EventListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeEmailListFragment(): EventListFragment
}