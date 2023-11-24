package uz.gita.jaxongir.adminformapp.data.model

import uz.gita.jaxongir.adminformapp.data.request.UserRequest
import uz.gita.jaxongir.adminformapp.data.source.database.entity.UserEntity

data class UserData (
    val id: String,
    val userName: String,
    val password: String,
){
    fun toEntity(): UserEntity = UserEntity(
        id = id,
        userName = userName,
        password = password
    )

    fun toRequest(): UserRequest = UserRequest(
        id = "id",
        userName = userName,
        password = password
    )


}