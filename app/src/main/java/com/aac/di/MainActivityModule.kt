package com.aac.di

import com.aac.ui.EventsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeEventsListFragment(): EventsListFragment
}