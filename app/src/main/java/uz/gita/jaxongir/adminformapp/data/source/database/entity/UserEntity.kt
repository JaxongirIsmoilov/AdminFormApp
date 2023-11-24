package uz.gita.jaxongir.adminformapp.data.source.database.entity

import androidx.room.Entity
import uz.gita.jaxongir.adminformapp.data.model.UserData
@Entity(tableName = "users")
data class UserEntity(
    val id: String,
    val userName: String,
    val password: String,
){
    fun toData(): UserData = UserData(
        id = id,
        userName = userName,
        password = password
    )
}