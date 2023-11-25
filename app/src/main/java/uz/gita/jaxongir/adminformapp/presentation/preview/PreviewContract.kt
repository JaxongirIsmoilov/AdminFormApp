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
        data class MoveToComponentScreen(val userId : String) : Intent

        object LoadData : Intent
    }

    data class UiState(val compList : List<ComponentData> = emptyList())
}