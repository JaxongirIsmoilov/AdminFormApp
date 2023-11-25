package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.jaxongir.adminformapp.domain.repository.Repository
import uz.gita.jaxongir.adminformapp.utils.myLog
import javax.inject.Inject

@HiltViewModel
class ComponentViewModel @Inject constructor(
    private val direction: ComponentDirection,
    private val repository: Repository,
) : ViewModel(), Contracts.ViewModel {
    override val uiState = MutableStateFlow(Contracts.UIState())

    private var userId: String = ""
    private var locId: Int = 0
    override fun eventDispatcher(intent: Contracts.Intent) {
        when (intent) {
            is Contracts.Intent.AddComponent -> {
                viewModelScope.launch {
                    repository.addComponent(intent.componentData, locId++)
                        .onStart {
                            uiState.update { it.copy(isLoading = true) }
                        }
                        .onEach {
                            it.onSuccess {
                                Log.d("TTT", "eventDispatcher: $it")
                                uiState.update {
                                    myLog("list size vm: ${it.components.size}")
                                    it.copy(components = it.components) }
                                direction.backToComponent()
                            }
                                .onFailure {
                                    Log.d("TTT", "eventDispatcher: ${it.message}")
                                }
                        }
                        .onCompletion {
                            uiState.update { it.copy(isLoading = false) }
                        }
                        .collect()

                    repository.getComponentsByUserId(userId).onEach {
                        it.onSuccess {
                            locId = it.size
                            uiState.update { uiState ->
                                uiState.copy(components = it)
                            }
                        }
                            .onFailure {
                                Log.d("TTT", "eventDispatcher: ${it.message}")
                            }
                    }.collect()
                }
            }

            is Contracts.Intent.Load -> {
                userId = intent.userId
            }

            is Contracts.Intent.DeleteComponent -> {
                viewModelScope.launch {
                    repository.deleteComponent(intent.componentData)
                        .onStart {
                            uiState.update { it.copy(isLoading = true) }
                        }
                        .onEach {
                            it.onSuccess {
                                    Log.d("TTT", "eventDispatcher: $it")
                                }
                                .onFailure {
                                    Log.d("TTT", "eventDispatcher: ${it.message}")
                                }
                        }
                        .onCompletion {
                            uiState.update { it.copy(isLoading = false) }
                        }
                        .collect()

                    repository.getComponentsByUserId(userId).onEach {
                        it
                            .onSuccess {
                                uiState.update { uiState ->
                                    uiState.copy(components = it)
                                }
                            }
                            .onFailure {
                                Log.d("TTT", "eventDispatcher: ${it.message}")
                            }
                    }.collect()
                }
            }

            is Contracts.Intent.EditComponent -> {
                viewModelScope.launch {
                    repository.editComponent(intent.componentData)
                        .onStart {
                            uiState.update { it.copy(isLoading = true) }
                        }.onEach {
                            it.onSuccess {
                                    Log.d("TTT", "eventDispatcher: $it")
                                }
                                .onFailure {
                                    Log.d("TTT", "eventDispatcher: ${it.message}")
                                }
                        }
                        .onCompletion {
                            uiState.update { it.copy(isLoading = false) }
                        }
                        .collect()

                    repository.getComponentsByUserId(userId).onEach {
                        it
                            .onSuccess {
                                uiState.update { uiState ->
                                    uiState.copy(components = it)
                                }
                            }
                            .onFailure {
                                Log.d("TTT", "eventDispatcher: ${it.message}")
                            }
                    }.collect()
                }
            }

            Contracts.Intent.Save -> {
                viewModelScope.launch {
                    direction.backToComponent()
                }
            }
        }
    }

}