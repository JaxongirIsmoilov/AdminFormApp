package uz.gita.jaxongir.adminformapp.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.gita.jaxongir.adminformapp.data.source.database.dao.Dao
import uz.gita.jaxongir.adminformapp.data.source.database.entity.ComponentEntity
import uz.gita.jaxongir.adminformapp.data.source.database.entity.UserEntity

@Database([ComponentEntity::class, UserEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class Database: RoomDatabase() {
    abstract fun getDao(): Dao
}