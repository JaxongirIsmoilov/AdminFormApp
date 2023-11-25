package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.firebase.components.ComponentContainer
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
import uz.gita.jaxongir.adminformapp.ui.components.ToolBarView
import uz.gita.jaxongir.adminformapp.ui.helper.InputContent
import uz.gita.jaxongir.adminformapp.ui.helper.SpinnerContent
import uz.gita.jaxongir.adminformapp.ui.helper.TextContent
import uz.gita.jaxongir.adminformapp.utils.myLog

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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (type) {
                    ComponentEnum.Input -> {
                        myLog("Input")
                        InputContent(
                            onEventListener = onEventDispatcher::invoke,
                            id = id,
                            userId = userId
                        )
                    }

                    ComponentEnum.SampleText -> {
//                        TextContent({}, id = id, )
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

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun ComponentScreenPreview() {
    MainContent("1", mutableStateOf(Contracts.UIState())) {}
}

