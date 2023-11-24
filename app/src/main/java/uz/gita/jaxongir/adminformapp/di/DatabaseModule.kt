package uz.gita.jaxongir.adminformapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.jaxongir.adminformapp.data.source.database.Database
import uz.gita.jaxongir.adminformapp.data.source.database.dao.Dao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @[Provides Singleton]
    fun databaseProvider(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "components")
            .build()

    @Provides
    fun daoProvider(database: Database): Dao = database.getDao()
}