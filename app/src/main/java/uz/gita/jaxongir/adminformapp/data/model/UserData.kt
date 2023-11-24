package uz.gita.jaxongir.adminformapp.data.model

import uz.gita.jaxongir.adminformapp.data.request.UserRequest
import uz.gita.jaxongir.adminformapp.data.source.database.entity.UserEntity

data class UserData(
    val userId: String,
    val userName: String,
    val password: String,
) {
    fun toEntity(): UserEntity = UserEntity(
        id = userId,
        userName = userName,
        password = password
    )

    fun toRequest(): UserRequest = UserRequest(
        userName = userName,
        password = password
    )


}