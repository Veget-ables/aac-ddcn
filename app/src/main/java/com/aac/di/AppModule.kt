package com.aac.di

import android.app.Application
import android.arch.persistence.room.Room
import com.aac.api.RandomUserService
import com.aac.db.EventDao
import com.aac.db.SampleDb
import com.aac.db.UserDao
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideRandomUserService(): RandomUserService {
        return Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RandomUserService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): SampleDb {
        return Room.databaseBuilder(app, SampleDb::class.java, "sample.db").build()
    }

    @Singleton
    @Provides
    fun provideEventDao(db: SampleDb): EventDao {
        return db.eventDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: SampleDb): UserDao {
        return db.userDao()
    }
}