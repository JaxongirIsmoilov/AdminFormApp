package uz.gita.jaxongir.adminformapp.presentation.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.jaxongir.adminformapp.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(
    private val direction: PreviewDirection,
    private val repository: Repository
) : ViewModel(), PreviewContract.ViewModel {
    override val uiState = MutableStateFlow(PreviewContract.UiState())
    private var userId = ""
    init {
        loadData()
    }
    override fun onEventDispatcher(intent: PreviewContract.Intent) {
        when (intent) {
            is PreviewContract.Intent.MoveToComponentScreen -> {
                viewModelScope.launch {
                    userId = intent.userId
                    direction.moveToComponentsScreen(intent.userId)
                }
            }
            PreviewContract.Intent.LoadData -> {
                loadData()
            }
        }
    }

    private fun loadData(){
        repository.getComponentsByUserId(userId).onEach {
            it.onSuccess {ls->
                uiState.update { it.copy(ls) }
            }
        }.launchIn(viewModelScope)
    }
}