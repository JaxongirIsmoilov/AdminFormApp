package uz.gita.jaxongir.adminformapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.jaxongir.adminformapp.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val direction: MainDirection,
    private val repository: Repository
) : ViewModel(), MainContract.ViewModel {
    override val uiState = MutableStateFlow(MainContract.UIState())


    init {
        loadData()
    }

    override fun onEventDispatcher(intent: MainContract.Intent) {
        when (intent) {
            MainContract.Intent.MoveToAddScreen -> {
                viewModelScope.launch {
                    direction.moveToAddScreen()
                }
            }

            is MainContract.Intent.DeleteUser -> {
                repository.deleteUser(intent.userData).onEach {
                    repository.getUsers()
                        .onCompletion { uiState.update { it.copy(isLoading = false) } }
                        .onStart { uiState.update { it.copy(isLoading = true) } }
                        .onEach {
                            it.onSuccess { ls ->
                                uiState.update { it.copy(ls) }
                            }
                        }.launchIn(viewModelScope)
                }.launchIn(viewModelScope)
            }

            is MainContract.Intent.MoveToPreviewScreen -> {
                viewModelScope.launch {
                    direction.moveToPreviewScreen(intent.userData)
                }
            }
        }
    }

    private fun loadData() {
        repository.getUsers()
            .onCompletion { uiState.update { it.copy(isLoading = false) } }
            .onStart { uiState.update { it.copy(isLoading = true) } }
            .onEach {
                it.onSuccess { ls ->
                    uiState.update { it.copy(ls) }
                }
            }.launchIn(viewModelScope)
    }

}