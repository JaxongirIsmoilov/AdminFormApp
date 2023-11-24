package uz.gita.jaxongir.adminformapp.data.source.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.jaxongir.adminformapp.data.source.database.entity.ComponentEntity

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(componentEntity: ComponentEntity)

    @Delete
    suspend fun deleteData(componentEntity: ComponentEntity)

    @Query("select * from components where userId = :userId")
    fun getByUser(userId: String): Flow<List<ComponentEntity>>
}