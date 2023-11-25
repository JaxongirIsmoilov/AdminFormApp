package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.Conditions
import uz.gita.jaxongir.adminformapp.ui.components.DialogSpinner
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
import uz.gita.jaxongir.adminformapp.ui.helper.InputContent
import uz.gita.jaxongir.adminformapp.ui.helper.SelectorContent
import uz.gita.jaxongir.adminformapp.ui.helper.SpinnerContent

class ComponentScreen(private val userId: String) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: Contracts.ViewModel = getViewModel<ComponentViewModel>()

        viewModel.eventDispatcher(Contracts.Intent.Load(userId))

        MainContent(
            userId = userId,
            uiState = viewModel.uiState.collectAsState(),
            onEventDispatcher = viewModel::eventDispatcher
        )
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun MainContent(
    userId: String,
    uiState: State<Contracts.UIState>,
    onEventDispatcher: (Contracts.Intent) -> Unit,
) {
    var type by remember {
        mutableStateOf(ComponentEnum.SampleText)
    }

    var id by remember {
        mutableStateOf("")
    }

    var content by remember {
        mutableStateOf("")
    }

    var conditions by remember {
        mutableStateOf(arrayListOf<Conditions>())
    }
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) DialogSpinner(
        uiState.value.savedIds, uiState.value.savedIds.first()
    ) { onEventDispatcher.invoke(Contracts.Intent.Save) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFff7686))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material3.Text(
                text = "Componenta Qo'shish",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                showDialog.value = true
            }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3951))) {
                Text(text = "</>", fontSize = 18.sp, color = White)
            }
        }

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {

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
                            type = ComponentEnum.Selector
                        }

                        "Dater" -> {
                            type = ComponentEnum.Dater
                        }

                        "SampleText" -> {
                            type = ComponentEnum.SampleText
                        }

                        else -> {
                            type = ComponentEnum.Input
                        }

                    }
                },
                content = "Qoshmoqchi bo'lgan component tipini kiriting"
            )

            Spacer(modifier = Modifier.size(12.dp))

            OutlinedTextField(
                value = id,
                onValueChange = {
                    id = it
                },
                label = {
                    Text(text = "Ixtiyorga qarab id qoshing")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFF3951),
                    unfocusedBorderColor = Color(0xFFFF7686)
                )
            )

            Spacer(modifier = Modifier.size(12.dp))

            OutlinedTextField(
                value = content,
                onValueChange = {
                    content = it
                },
                label = {
                    Text(text = "Conetnt kiriting")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFF3951),
                    unfocusedBorderColor = Color(0xFFFF7686)
                )
            )

            Spacer(modifier = Modifier.size(36.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (type) {
                    ComponentEnum.Input -> {
                        InputContent(
                            onEventListener = onEventDispatcher::invoke,
                            id = id,
                            userId = userId,
                            content = content
                        )
                    }

                    ComponentEnum.SampleText -> {
                        TextButton(
                            onClick = {
                                onEventDispatcher.invoke(
                                    Contracts.Intent.AddComponent(
                                        ComponentData(
                                            userId = userId,
                                            locId = 0,
                                            idEnteredByUser = id,
                                            content = content,
                                            textFieldType = TextFieldType.Text,
                                            maxLines = 0,
                                            maxLength = 0,
                                            minLength = 0,
                                            maxValue = 0,
                                            minValue = 0,
                                            isMulti = false,
                                            variants = listOf(),
                                            selected = listOf(),
                                            conditions = listOf(),
                                            type = ComponentEnum.SampleText,
                                            id = ""
                                        )
                                    )
                                )
                            },
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.TopCenter)
                        ) {
                            Text(text = "Componentni qoshish")
                        }
                    }

                    ComponentEnum.Dater -> {
                        TextButton(
                            onClick = {
                                onEventDispatcher.invoke(
                                    Contracts.Intent.AddComponent(
                                        ComponentData(
                                            userId = userId,
                                            locId = 0,
                                            idEnteredByUser = id,
                                            content = content,
                                            textFieldType = TextFieldType.Text,
                                            maxLines = 0,
                                            maxLength = 0,
                                            minLength = 0,
                                            maxValue = 0,
                                            minValue = 0,
                                            isMulti = false,
                                            variants = listOf(),
                                            selected = listOf(),
                                            conditions = listOf(),
                                            type = ComponentEnum.Dater,
                                            id = ""
                                        )
                                    )
                                )
                            },
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.TopCenter)
                        ) {
                            Text(text = "Componentni qoshish")
                        }

                    }

                    ComponentEnum.Selector -> {
                        SelectorContent(
                            onEventListener = onEventDispatcher::invoke,
                            id = id,
                            userId = userId,
                            content = content
                        )
                    }

                    ComponentEnum.Spinner -> {
                        SpinnerContent(
                            onEventListener = onEventDispatcher::invoke,
                            id = id,
                            userId = userId,
                            content = content
                        )
                    }
                }
            }
        }


    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun ComponentScreenPreview() {
    MainContent("1", mutableStateOf(Contracts.UIState())) {}
}

