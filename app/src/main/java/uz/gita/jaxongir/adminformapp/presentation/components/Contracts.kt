package uz.gita.jaxongir.adminformapp.presentation.components

import uz.gita.jaxongir.adminformapp.data.model.ComponentData

interface Contracts {
    interface ViewModel{

    }

    interface Direction{
        suspend fun back
    }

    interface Intent{

    }

    data class UIState(
        val components: List<ComponentData> = listOf(),
        val isLoading: Boolean = false
    )
}