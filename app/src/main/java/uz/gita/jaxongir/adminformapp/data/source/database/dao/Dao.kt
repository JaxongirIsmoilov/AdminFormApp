package uz.gita.jaxongir.adminformapp.data.source.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.jaxongir.adminformapp.data.source.database.entity.ComponentEntity
import uz.gita.jaxongir.adminformapp.data.source.database.entity.UserEntity

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(componentEntity: ComponentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDatas(componentEntity: List<ComponentEntity>)

    @Delete
    suspend fun deleteData(componentEntity: ComponentEntity)

    @Query("select * from components where userId = :userId order by locId asc ")
    fun getByUser(userId: String): Flow<List<ComponentEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(userEntity: List<UserEntity>)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

    @Query("select * from users")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("delete from users")
    suspend fun deleteUsers()
}