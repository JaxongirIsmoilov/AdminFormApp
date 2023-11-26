package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

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
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ComponentViewModel @Inject constructor(
    private val direction: ComponentDirection,
    private val repository: Repository,
) : ViewModel(), Contracts.ViewModel {
    override val uiState = MutableStateFlow(Contracts.UIState())
    private var userId: String = ""
    private var ids = arrayListOf<String>()
    override fun eventDispatcher(intent: Contracts.Intent) {
        ids.addAll(uiState.value.savedIds)
        myLog("ids:${ids.size}")
        when (intent) {
            is Contracts.Intent.AddComponent -> {
                if (intent.componentData.idEnteredByUser != "") {
                    ids.add(intent.componentData.idEnteredByUser)
                    myLog("ids2:${ids.size}")
                    uiState.update {
                        it.copy(savedIds = ids)
                    }

                } else {
                    uiState.update {
                        it.copy(savedIds = ids)

                    }
                    myLog("ids3:${ids.size}")
                }
                myLog("${uiState.value.components.size}")
                viewModelScope.launch {
                    repository.addComponent(
                        intent.componentData.copy(locId = Date().time),
                        uiState.value.components.size
                    )
                        .onStart {
                            uiState.update { it.copy(isLoading = true) }
                        }
                        .onEach {
                            it.onSuccess {
                                uiState.update {
                                    it.copy(components = it.components)
                                }
                                if (intent.state) {
                                    direction.moveToPreviewScreenFromUserAdd(intent.userData)
                                } else {
                                    direction.moveToPreviewScreenFromMain(userId)
                                }
                            }
                                .onFailure {
                                }
                        }
                        .onCompletion {
                            uiState.update { it.copy(isLoading = false) }
                        }
                        .collect()

                    repository.getComponentsByUserId(userId).onEach {
                        it.onSuccess {
                            uiState.update { uiState ->
                                uiState.copy(components = it)
                            }
                        }
                            .onFailure {
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

                            }
                                .onFailure {
                                }
                        }
                        .onCompletion {
                            uiState.update { it.copy(isLoading = false) }
                        }
                        .collect()

                    repository.getComponentsByUserId(userId)
                        .onCompletion { }
                        .onEach {
                            it.onSuccess {
                                uiState.update { uiState ->
                                    uiState.copy(components = it)
                                }
                            }
                                .onFailure {
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

                            }
                                .onFailure {
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
                            }
                    }.collect()
                }
            }

            Contracts.Intent.Save -> {
                viewModelScope.launch {

                }
            }
        }
    }

}