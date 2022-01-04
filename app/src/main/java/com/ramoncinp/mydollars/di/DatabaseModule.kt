package com.ramoncinp.mydollars.di

import android.content.Context
import androidx.room.Room
import com.ramoncinp.mydollars.data.database.DollarsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "dollars_database"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDataBase(
        @ApplicationContext context: Context
    ): DollarsDatabase = Room.databaseBuilder(
        context,
        DollarsDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    fun providesAccountsDao(database: DollarsDatabase) = database.accountsDao

    @Provides
    fun providesTransactionsDao(database: DollarsDatabase) = database.transactionsDao
}
