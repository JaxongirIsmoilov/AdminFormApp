package uz.gita.jaxongir.adminformapp.presentation.preview

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.UserData

interface PreviewContract {

    interface ViewModel{
        val uiState : StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    interface Intent{
        data class MoveToComponentScreen(val userData: UserData) : Intent
        data class DeleteComponent(
            val componentData: ComponentData
        ) : Intent
        data class LoadData(
            val userId: String
        ): Intent
    }

    data class UiState(
        val compList : List<ComponentData> = emptyList()
    )
}