package uz.gita.jaxongir.adminformapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
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

            MainContract.Intent.UpdateUserList -> {
                loadData()
            }

            is MainContract.Intent.DeleteUser -> {
                repository.deleteUser(intent.userData)
            }

            is MainContract.Intent.MoveToComponentsScreen -> {
                viewModelScope.launch {
                    direction.moveToComponentScreen(intent.userData)
                }
            }
        }
    }

    private fun loadData() {
        repository.getUsers().onEach {
            it.onSuccess { ls ->
                uiState.update { it.copy(ls) }
            }
        }
    }

}