package com.aac.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aac.ui.EventListViewModel
import com.aac.ui.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EventListViewModel::class)
    abstract fun bindEmailListViewModel(viewModel: EventListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}