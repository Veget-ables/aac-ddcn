package com.aac.di

import android.app.Application
import android.arch.persistence.room.Room
import com.aac.db.EventDao
import com.aac.db.SampleDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideDb(app: Application): SampleDb {
        return Room.databaseBuilder(app, SampleDb::class.java, "gohoubi.db").build()
    }

    @Singleton
    @Provides
    fun provideEventDao(db: SampleDb): EventDao {
        return db.eventDao()
    }
}