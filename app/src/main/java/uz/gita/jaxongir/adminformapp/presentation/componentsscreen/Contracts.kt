package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.UserData

interface Contracts {
    interface ViewModel{
        val uiState: StateFlow<UIState>

        fun eventDispatcher(intent: Intent)
    }

    interface Direction{
        suspend fun backToMain()
    }

    interface Intent{
        data class AddComponent(
            val componentData: ComponentData
        ): Intent
        data class DeleteComponent(
            val componentData: ComponentData
        ): Intent
        data class EditComponent(
            val componentData: ComponentData
        ): Intent

        data class Load(
            val userData: UserData
        ): Intent

        object Save: Intent
    }

    data class UIState(
        val components: List<ComponentData> = listOf(),
        val isLoading: Boolean = false,
        var index: Int = 0
    )
}