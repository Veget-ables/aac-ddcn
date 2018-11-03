package com.aac.di

import com.aac.ui.event.EventFragment
import com.aac.ui.eventlist.EventListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeEventListFragment(): EventListFragment

    @ContributesAndroidInjector
    abstract fun contributeEventFragment(): EventFragment
}