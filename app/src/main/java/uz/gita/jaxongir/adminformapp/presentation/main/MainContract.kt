package uz.gita.jaxongir.adminformapp.presentation.main

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.adminformapp.data.model.UserData

interface MainContract {
    interface ViewModel {

        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }

    interface Intent {
        object MoveToAddScreen : Intent

        object UpdateUserList : Intent

        data class DeleteUser(val userData: UserData) : Intent

        data class MoveToComponentsScreen(val userData: UserData) : Intent

        data class MoveToPreviewScreen(val userData: UserData) : Intent
    }

    data class UIState(
        val userList: List<UserData> = emptyList()
    )


}