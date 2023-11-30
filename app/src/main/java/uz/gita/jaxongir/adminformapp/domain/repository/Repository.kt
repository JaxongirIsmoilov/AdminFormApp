package uz.gita.jaxongir.adminformapp.domain.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.data.request.UserRequest

interface Repository {
    fun addComponent(componentData: ComponentData, id: Int): Flow<Result<String>>
    fun deleteComponent(componentData: ComponentData): Flow<Result<String>>
    fun editComponent(componentData: ComponentData): Flow<Result<String>>
    fun addUser(request: UserRequest): Flow<Result<String>>
    fun deleteUser(userData: UserData): Flow<Result<String>>
    fun getComponentsByUserId(userID: String): Flow<Result<List<ComponentData>>>
    fun getUsers(): Flow<Result<List<UserData>>>
    fun uploadPhoto(componentData: ComponentData, id: Int): Flow<Result<Unit>>
}