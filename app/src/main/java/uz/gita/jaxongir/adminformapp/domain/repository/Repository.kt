package uz.gita.jaxongir.adminformapp.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.UserData

interface Repository {
    fun addComponent(componentData: ComponentData): Flow<Result<String>>
    fun deleteComponent(componentData: ComponentData): Flow<Result<String>>
    fun editComponent(componentData: ComponentData): Flow<Result<String>>
    fun addUser(userData: UserData): Flow<Result<String>>
    fun deleteUser(userData: UserData): Flow<Result<String>>
    fun getComponentsByUserId(userID: String): Flow<Result<List<ComponentData>>>
    fun getUsers(): Flow<Result<List<UserData>>>
}