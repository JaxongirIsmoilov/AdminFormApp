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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageTypeEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.ui.components.DialogSpinner
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
import uz.gita.jaxongir.adminformapp.ui.helper.ImageComponent
import uz.gita.jaxongir.adminformapp.ui.helper.InputContent
import uz.gita.jaxongir.adminformapp.ui.helper.SelectorContent
import uz.gita.jaxongir.adminformapp.ui.helper.SpinnerContent
import uz.gita.jaxongir.adminformapp.ui.previewitems.InputChipExample

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

    val selectedOperators by remember {
        mutableStateOf(arrayListOf<String>())
    }
    val selectedIds by remember {
        mutableStateOf(arrayListOf<String>())
    }
    val selectedValues by remember {
        mutableStateOf(arrayListOf<String>())
    }

    var rowId by remember {
        mutableStateOf("")
    }

    var inValues by remember {
        mutableStateOf(arrayListOf<String>())
    }


    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        DialogSpinner(
            savedIdList = uiState.value.savedIds,
            onSaveClick = { componentId, selectedOperator, value ->
                selectedIds.add(componentId)
                selectedOperators.add(selectedOperator)
                selectedValues.add(value)
                onEventDispatcher.invoke(Contracts.Intent.SaveSelectedIds(selectedOperator))
            },
            onClickCancel = {
                showDialog.value = false
            },
            components = uiState.value.components,
            type = type
        ) { id1, operator, values ->
            selectedIds.add(id1)
            selectedOperators.add(operator)
            inValues.addAll(values)

        }
    }



    Box(modifier = Modifier.fillMaxSize()) {
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
                    .padding(horizontal = 16.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material3.Text(
                    text = "Componenta Qo'shish",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = {
                        showDialog.value = true
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(color = Color(0xFFFF3951))
                ) {
                    Text(text = "</>", fontSize = 18.sp, color = White)
                }
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                Spacer(modifier = Modifier.height(3.dp))

                SampleSpinner(
                    list = listOf(
                        ComponentEnum.Spinner.content,
                        ComponentEnum.SampleText.content,
                        ComponentEnum.Dater.content,
                        ComponentEnum.Input.content,
                        ComponentEnum.LazyRow.content,
                        ComponentEnum.Image.content,
                        ComponentEnum.Selector.content,
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

                            "LazyRow" -> {
                                type = ComponentEnum.LazyRow
                            }

                            "Image" -> {
                                type = ComponentEnum.Image
                            }

                            else -> {
                                type = ComponentEnum.Input
                            }

                        }
                    },
                    content = "Qoshmoqchi bo'lgan component tipini kiriting"
                )

                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = id,
                    onValueChange = {
                        id = it
                    },
                    singleLine = true,
                    label = {
                        Text(text = "Ixtiyorga qarab id qoshing")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF3951),
                        unfocusedBorderColor = Color(0xFFFF7686)
                    ), maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.None
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = content,
                    onValueChange = {
                        content = it
                    },
                    label = {
                        Text(text = "Sarlavhani kiriting")
                    }, singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF3951),
                        unfocusedBorderColor = Color(0xFFFF7686)
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                if (uiState.value.rowId.isNotEmpty() && type != ComponentEnum.LazyRow) {
                    SampleSpinner(
                        list = uiState.value.rowId,
                        preselected = rowId,
                        onSelectionChanged = {
                            rowId = it
                        },
                        content = "Gorizontal qatorga qoshish uchun id tanlang"
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (type) {
                        ComponentEnum.Input -> {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                InputContent(
                                    onEventListener = onEventDispatcher::invoke,
                                    connectedIds = selectedIds,
                                    connectedValues = selectedValues,
                                    operators = selectedOperators,
                                    id = id,
                                    userId = userId,
                                    content = content,
                                    rowId = rowId,
                                    inValues = inValues,
                                    isTrue = !uiState.value.isLoading
                                )
                            }
                        }

                        ComponentEnum.SampleText -> {
                            var weight by remember {
                                mutableStateOf("0")
                            }

                            if (rowId != "") {
                                OutlinedTextField(
                                    value = weight,
                                    onValueChange = {
                                        if (it != "") {
                                            if (it.toFloat() > 1.1) {
                                                weight = "1"
                                                return@OutlinedTextField
                                            }

                                            if (it.toFloat() < 0) {
                                                weight = "0"
                                                return@OutlinedTextField
                                            }

                                            weight = it
                                        } else {
                                            weight = ""
                                        }
                                    },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    label = { Text(text = "Weight") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFFFF3951),
                                        unfocusedBorderColor = Color(0xFFFF7686)
                                    )
                                )
                            }

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
                                                connectedIds = selectedIds,
                                                connectedValues = selectedValues,
                                                operators = selectedOperators,
                                                type = ComponentEnum.SampleText,
                                                id = "",
                                                isRequired = false,
                                                imgUri = "",
                                                ratioY = 0,
                                                ratioX = 0,
                                                customHeight = "W",
                                                rowId = rowId,
                                                backgroundColor = Transparent.toArgb(),
                                                weight = weight,
                                                inValues = inValues
                                            )
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(5.dp),
                                enabled = (weight != "0" || rowId == "") && !uiState.value.isLoading
                            ) {
                                Text(text = "Componentni qoshish")
                            }
                        }

                        ComponentEnum.Dater -> {
                            var weight by remember {
                                mutableStateOf("0f")
                            }

                            if (rowId != "") {
                                OutlinedTextField(
                                    value = weight,
                                    onValueChange = {
                                        if (it != "") {
                                            if (it.toFloat() > 1.1) {
                                                weight = "1"
                                                return@OutlinedTextField
                                            }

                                            if (it.toFloat() < 0) {
                                                weight = "0"
                                                return@OutlinedTextField
                                            }

                                            weight = it
                                        } else {
                                            weight = ""
                                        }
                                    },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    label = { Text(text = "Weight") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFFFF3951),
                                        unfocusedBorderColor = Color(0xFFFF7686)
                                    )
                                )
                            }

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
                                                connectedIds = selectedIds,
                                                connectedValues = selectedValues,
                                                operators = selectedOperators,
                                                type = ComponentEnum.Dater,
                                                id = "",
                                                isRequired = false,
                                                imgUri = "",
                                                ratioY = 0,
                                                ratioX = 0,
                                                customHeight = "W",
                                                rowId = rowId,
                                                backgroundColor = Transparent.toArgb(),
                                                weight = weight,
                                                inValues = inValues
                                            )
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .wrapContentSize(),
                                enabled = (weight != "0" || rowId == "") && !uiState.value.isLoading
                            ) {
                                Text(text = "Componentni qoshish")
                            }

                        }

                        ComponentEnum.Selector -> {
                            SelectorContent(
                                onEventListener = onEventDispatcher::invoke,
                                id = id,
                                connectedIds = selectedIds,
                                connectedValues = selectedValues,
                                operators = selectedOperators,
                                userId = userId,
                                content = content,
                                rowId = rowId,
                                isTrue = !uiState.value.isLoading
                            )
                        }

                        ComponentEnum.Spinner -> {
                            SpinnerContent(
                                onEventListener = onEventDispatcher::invoke,
                                connectedIds = selectedIds,
                                connectedValues = selectedValues,
                                operators = selectedOperators,
                                id = id,
                                userId = userId,
                                content = content,
                                rowId = rowId,
                                inValues = inValues,
                                isTrue = !uiState.value.isLoading
                            )
                        }

                        ComponentEnum.LazyRow -> {
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
                                                connectedIds = selectedIds,
                                                connectedValues = selectedValues,
                                                operators = selectedOperators,
                                                type = ComponentEnum.LazyRow,
                                                id = "",
                                                isRequired = false,
                                                imgUri = "",
                                                ratioY = 0,
                                                ratioX = 0,
                                                customHeight = "W",
                                                rowId = rowId,
                                                backgroundColor = Transparent.toArgb(),
                                                weight = ""
                                            )
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .wrapContentSize(),
                                enabled = id.isNotEmpty() && !uiState.value.isLoading
                            ) {
                                Text(text = "Componentni qoshish")
                            }
                        }

                        ComponentEnum.Image -> {
                            var imageTypeEnum by remember {
                                mutableStateOf(ImageTypeEnum.LOCAL)
                            }

                            SampleSpinner(
                                list = listOf(ImageTypeEnum.LOCAL.type, ImageTypeEnum.REMOTE.type),
                                preselected = "Rasmni qay tarzda joylashni tanlang",
                                onSelectionChanged = {
                                    when (it) {
                                        ImageTypeEnum.LOCAL.type -> {
                                            imageTypeEnum = ImageTypeEnum.LOCAL
                                        }

                                        ImageTypeEnum.REMOTE.type -> {
                                            imageTypeEnum = ImageTypeEnum.REMOTE
                                        }
                                    }
                                },
                                content = "Rasmni qay tarzda joylashni tanlang"
                            )

                            ImageComponent(
                                onEventDispatcher = onEventDispatcher::invoke,
                                userId = userId,
                                idEnteredByUser = id,
                                typeEnum = imageTypeEnum
                            )
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    if (uiState.value.selectedIdsList.isNotEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(0xFFFFC1C8))
                        ) {
                            LazyRow(verticalAlignment = Alignment.CenterVertically) {
                                item { Spacer(modifier = Modifier.size(10.dp)) }

                                item {
                                    uiState.value.selectedIdsList.forEachIndexed { index, s ->
                                        InputChipExample(
                                            text = s.toString(),
                                            condition = selectedValues[index]
                                        ) {}
                                    }
                                }
                                item { Spacer(modifier = Modifier.size(10.dp)) }
                            }
                        }
                    }

                }
            }
        }
    }
}