package uz.gita.jaxongir.adminformapp.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.jaxongir.adminformapp.data.model.ComponentData

interface Repository {
    fun addComponent(componentData: ComponentData): Flow<Result<String>>
    fun deleteComponent(componentData: ComponentData): Flow<Result<String>>
    fun editComponent(componentData: ComponentData): Flow<Result<String>>
}