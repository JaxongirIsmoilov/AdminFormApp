package uz.gita.jaxongir.adminformapp.presentation.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
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
    override fun onEventDispatcher(intent: PreviewContract.Intent) {
        when (intent) {
            is PreviewContract.Intent.MoveToComponentScreen -> {
                viewModelScope.launch {
                    direction.moveToComponentsScreen(intent.userId)
                }
            }

            is PreviewContract.Intent.LoadData -> {
                viewModelScope.launch {
                    repository.getComponentsByUserId(intent.userId).onEach {
                        it.onSuccess { ls ->
                            uiState.update { it.copy(compList = ls) }
                        }
                    }.collect()
                }
            }

            is PreviewContract.Intent.DeleteComponent -> {
                viewModelScope.launch {
                    repository.deleteComponent(intent.componentData).onEach {
                        it.onSuccess {
                            repository.getComponentsByUserId(intent.componentData.userId).onEach {
                                it.onSuccess { ls ->
                                    uiState.update { it.copy(compList = ls) }
                                }
                            }.collect()
                        }
                    }.collect()
                }
            }
        }
    }
}