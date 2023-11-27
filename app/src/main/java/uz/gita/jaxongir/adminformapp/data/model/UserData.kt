package uz.gita.jaxongir.adminformapp.data.model

import uz.gita.jaxongir.adminformapp.data.request.UserRequest
import uz.gita.jaxongir.adminformapp.data.source.database.entity.UserEntity
import java.io.Serializable

data class UserData(
    var userId: String,
    var userName: String,
    var password: String,
) : Serializable {
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