package com.example.cristian_tavarez_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.cristian_tavarez_ap2_p1.data.database.AppDatabase
import com.example.cristian_tavarez_ap2_p1.data.local.AmonestacionDao
import com.example.cristian_tavarez_ap2_p1.data.repository.AmonestacionRepositoryImpl
import com.example.cristian_tavarez_ap2_p1.domain.repository.AmonestacionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "amonestaciones_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAmonestacionDao(db: AppDatabase): AmonestacionDao = db.amonestacionDao

    @Provides
    @Singleton
    fun provideAmonestacionRepository(dao: AmonestacionDao): AmonestacionRepository {
        return AmonestacionRepositoryImpl(dao)
    }
}