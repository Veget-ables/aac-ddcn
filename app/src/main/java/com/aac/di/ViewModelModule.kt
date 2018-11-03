package com.aac.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aac.ui.ViewModelFactory
import com.aac.ui.event.EventViewModel
import com.aac.ui.eventlist.EventListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EventListViewModel::class)
    abstract fun bindEventListViewModel(viewModel: EventListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel::class)
    abstract fun bindEventViewModel(viewModel: EventViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}