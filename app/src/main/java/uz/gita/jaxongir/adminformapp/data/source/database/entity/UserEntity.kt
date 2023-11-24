package uz.gita.jaxongir.adminformapp.data.source.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.jaxongir.adminformapp.data.model.UserData
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val userName: String,
    val password: String,
){
    fun toData(): UserData = UserData(
        userId = id,
        userName = userName,
        password = password
    )
}