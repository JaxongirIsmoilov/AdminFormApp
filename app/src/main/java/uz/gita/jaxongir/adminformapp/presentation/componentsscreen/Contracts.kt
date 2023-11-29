package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import java.sql.RowId

interface Contracts {
    interface ViewModel {
        val uiState: StateFlow<UIState>

        fun eventDispatcher(intent: Intent)
    }

    interface Intent {
        data class AddComponent(
            val componentData: ComponentData
        ) : Intent

        data class DeleteComponent(
            val componentData: ComponentData
        ) : Intent

        data class EditComponent(
            val componentData: ComponentData
        ) : Intent

        data class Load(
            val userId: String
        ) : Intent

        object Save : Intent

        object LoadComponentId : Intent
        data class SaveSelectedIds(val selectedValues: String) : Intent
    }

    data class UIState(
        val components: List<ComponentData> = listOf(),
        val isLoading: Boolean = false,
        var index: Int = 0,
        val savedIds: List<String> = listOf(),
        val selectedOperators: String = "",
        val selectedIdsList: List<String> = listOf(),
        val rowId: List<String> = listOf()
    )
}