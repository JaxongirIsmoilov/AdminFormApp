package uz.gita.jaxongir.adminformapp.presentation.adduser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.data.request.UserRequest
import uz.gita.jaxongir.adminformapp.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class UserAddViewModel @Inject constructor(
    private val direction: UserAddDirection,
    private val appRepository: Repository
) : UserAddContract.ViewModel, ViewModel() {
    override fun onEventDispatcher(event: UserAddContract.Event) = intent {
        when (event) {
            is UserAddContract.Event.AddUser -> {
                appRepository.addUser(UserRequest(userName = event.username, event.password))
                    .onStart { postSideEffect(UserAddContract.SideEffect.ProgressState(true)) }
                    .onCompletion { postSideEffect(UserAddContract.SideEffect.ProgressState(false)) }
                    .onEach {
                        it.onSuccess {
                            val userId = it
                            postSideEffect(UserAddContract.SideEffect.ShowToast("User muvaffaqiyatli qo'shildi!"))
                            direction.moveToPreview(
                                UserData(
                                    userId,
                                    event.username,
                                    event.password
                                )
                            )
                        }

                        it.onFailure {
                            postSideEffect(UserAddContract.SideEffect.ShowToast(it.message?:"Something wrong"))
                            postSideEffect(UserAddContract.SideEffect.ProgressState(false))
                        }
                        postSideEffect(UserAddContract.SideEffect.ProgressState(false))
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

    override val container =
        container<UserAddContract.UiState, UserAddContract.SideEffect>(UserAddContract.UiState.InitialState)
}