package uz.gita.jaxongir.adminformapp.data.request

import uz.gita.jaxongir.adminformapp.data.source.database.entity.UserEntity

data class UserRequest(
    val userName: String,
    val password: String,
) {
    fun fromRequestToEntity(userId: String=""): UserEntity =
        UserEntity(userId, this.userName, this.password)
}