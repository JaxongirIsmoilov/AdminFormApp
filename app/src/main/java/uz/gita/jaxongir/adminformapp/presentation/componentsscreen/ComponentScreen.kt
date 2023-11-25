package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
import uz.gita.jaxongir.adminformapp.ui.components.ToolBarView
import uz.gita.jaxongir.adminformapp.ui.helper.InputContent
import uz.gita.jaxongir.adminformapp.ui.helper.SelectorContent
import uz.gita.jaxongir.adminformapp.ui.helper.SpinnerContent
import uz.gita.jaxongir.adminformapp.ui.helper.TextContent

class ComponentScreen(private val userId: String) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: Contracts.ViewModel = getViewModel<ComponentViewModel>()

        viewModel.eventDispatcher(Contracts.Intent.Load(userId))

        MainContent(uiState = viewModel.uiState.collectAsState(), onEventDispatcher = viewModel::eventDispatcher)
    }

    @Composable
    fun MainContent(
        uiState: State<Contracts.UIState>,
        onEventDispatcher: (Contracts.Intent) -> Unit,
    ) {
        var type by remember {
            mutableStateOf(ComponentEnum.SampleText)
        }

        var id by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ToolBarView(text = "Componenta qoshish")

            Spacer(modifier = Modifier.size(12.dp))

            SampleSpinner(
                list = listOf(
                    ComponentEnum.Spinner.content,
                    ComponentEnum.SampleText.content,
                    ComponentEnum.Dater.content,
                    ComponentEnum.Input.content,
                    ComponentEnum.Selector.content
                ),
                preselected = ComponentEnum.SampleText.content,
                onSelectionChanged = {
                    when (it) {
                        "Spinner" -> {
                            type = ComponentEnum.Spinner
                        }

                        "Selector" -> {
                            ComponentEnum.Selector
                        }

                        "Dater" -> {
                            ComponentEnum.Dater
                        }

                        "SampleText" -> {
                            ComponentEnum.SampleText
                        }

                        else -> {
                            ComponentEnum.Input
                        }

                    }
                },
                content = "Qoshmoqchi bo'lgan component tipini kirit"
            )

            Spacer(modifier = Modifier.size(12.dp))

            OutlinedTextField(
                value = id,
                onValueChange = {
                    id = it
                },
                label = {
                    Text(text = "Ixtiyorga qarab id qoshing")
                }
            )

            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            ){
                when (type) {
                    ComponentEnum.Input -> {
                        InputContent(
                            onSaveClickListener = {
                                onEventDispatcher.invoke(
                                    Contracts.Intent.AddComponent(
                                        ComponentData(
                                            id = "",
                                            userId = userId,
                                            locId = 0,
                                            idEnteredByUser = id,
                                            type = type,
                                            content =

                                        )
                                    )
                                )
                            },
                            id = id
                        )
                    }

                    ComponentEnum.SampleText -> {
                        TextContent()
                    }

                    ComponentEnum.Dater -> {

                    }

                    ComponentEnum.Selector -> {

                    }

                    ComponentEnum.Spinner -> {
                        SpinnerContent(clickListener = {}, id = id)
                    }
                }
            }


        }
    }
}