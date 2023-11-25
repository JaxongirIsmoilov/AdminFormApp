package uz.gita.jaxongir.adminformapp.presentation.preview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.ui.components.DatePicker
import uz.gita.jaxongir.adminformapp.ui.components.InputField
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
import uz.gita.jaxongir.adminformapp.ui.components.SelectorItem
import uz.gita.jaxongir.adminformapp.ui.components.ToolBarView

class PreviewScreen(private val userData: UserData) : AndroidScreen() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        val viewModel: PreviewViewModel = getViewModel()
        PreviewScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PreviewScreenContent(
    uiState: State<PreviewContract.UiState>,
    onEventDispatcher: (PreviewContract.Intent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ToolBarView(text = "Components")

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 56.dp)
        ) {
            uiState.value.compList.forEach { data ->
                when (data.type) {
                    ComponentEnum.Spinner -> {
                        item {
                            SampleSpinner(
                                list = data.variants,
                                preselected = data.variants.first(),
                                onSelectionChanged = {},
                                content = data.content
                            )
                        }
                    }

                    ComponentEnum.Selector -> {
                        item {
                            SelectorItem(data.content, data.variants)
                        }
                    }

                    ComponentEnum.SampleText -> {
                        item {
                            Text(text = data.content)
                        }
                    }

                    ComponentEnum.Input -> {
                        item {
                            InputField(
                                textFieldType = data.textFieldType,
                                maxLines = data.maxLines,
                                maxLength = data.maxLength,
                                minLength = data.minLength,
                                maxValue = data.maxValue,
                                minValue = data.minValue,
                                question = data.content
                            )
                        }
                    }

                    ComponentEnum.Dater -> {
                        item {
                            DatePicker(content = data.content)
                        }
                    }
                }
            }

            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(onClick = {
                        onEventDispatcher.invoke(
                            PreviewContract.Intent.MoveToComponentScreen(
                                uiState.value.compList.first().userId
                            )
                        )
                    }) {
                        Text(text = "Edit", color = Color.White)
                    }
                }
            }

        }
    }
}