package uz.gita.jaxongir.adminformapp.presentation.adduser

import org.orbitmvi.orbit.ContainerHost


interface UserAddContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    interface Event {
        data class AddUser(val username: String, val password: String) : Event
    }

    interface UiState {
        object InitialState : UiState
    }

    interface SideEffect {
        data class ProgressState(val state: Boolean) : SideEffect
        data class ShowToast(val message: String) : SideEffect
    }
}