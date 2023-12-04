package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner

@Composable
fun InputContent(
    onEventListener: (Contracts.Intent) -> Unit,
    id: String,
    userId: String,
    content: String,
    connectedIds: List<String>,
    connectedValues: List<String>,
    operators: List<String>,
    rowId: String,
    inValues: List<String>,
    isTrue: Boolean
) {
    var type by remember {
        mutableStateOf(TextFieldType.Text)
    }

    var maxLines by remember {
        mutableStateOf("1")
    }

    var maxLength by remember {
        mutableStateOf("0")
    }

    var minLength by remember {
        mutableStateOf("0")
    }

    var maxValue by remember {
        mutableStateOf("0")
    }

    var minValue by remember {
        mutableStateOf("0")
    }

    var checkBoxState by remember {
        mutableStateOf(false)
    }

    var weight by remember {
        mutableStateOf("0")
    }

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            SampleSpinner(
                list = listOf(
                    TextFieldType.Text.content,
                    TextFieldType.Email.content,
                    TextFieldType.Number.content,
                    TextFieldType.Phone.content
                ),
                preselected = TextFieldType.Text.content,
                onSelectionChanged = {
                    when (it) {
                        "Text" -> {
                            type = TextFieldType.Text
                        }

                        "Email" -> {
                            type = TextFieldType.Email
                        }

                        "Number" -> {
                            type = TextFieldType.Number
                        }

                        else -> {
                            type = TextFieldType.Phone
                        }
                    }
                },
                content = "Tipini kiriting"
            )

        }

        item {
            when (type.content) {

                "Text" -> {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = maxLines,
                            onValueChange = {
                                maxLines = it
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Qatorla soni") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFFF3951),
                                unfocusedBorderColor = Color(0xFFFF7686)
                            )
                        )

                        OutlinedTextField(
                            value = minLength.toString(),
                            onValueChange = {
                                minLength = it
                            }, singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Min length = ") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFFF3951),
                                unfocusedBorderColor = Color(0xFFFF7686)
                            )
                        )

                        OutlinedTextField(
                            value = maxLength,
                            onValueChange = {
                                maxLength = it
                            }, singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Max length") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFFF3951),
                                unfocusedBorderColor = Color(0xFFFF7686)
                            )
                        )


                    }
                }

                "Phone" -> {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                    }
                }

                "Number" -> {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = minValue.toString(),
                            onValueChange = {
                                minValue = it
                            }, singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Min Value = ") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFFF3951),
                                unfocusedBorderColor = Color(0xFFFF7686)
                            )
                        )

                        OutlinedTextField(
                            value = maxValue.toString(),
                            onValueChange = {
                                maxValue = it
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Max Value ") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFFF3951),
                                unfocusedBorderColor = Color(0xFFFF7686)
                            )
                        )
                    }
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        maxLength = "1"
                        maxLines = "1"
                        maxValue = "0"
                        minLength = "0"
                        minValue = "0"
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkBoxState,
                    onCheckedChange = { checkBoxState = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFFFF3159),
                        uncheckedColor = Color(0xFFFF7686),
                    )
                )
                Spacer(modifier = Modifier.size(10.dp))
                androidx.compose.material.Text(
                    text = "Is Required",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (checkBoxState) Color(0xFFFF3159) else Color(0xFFFF7686)
                )
            }
        }

        if (rowId != "") {
            item {
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
        }

        item {
            TextButton(
                onClick = {
                    if (minValue.toInt() < maxValue.toInt()) {
                        onEventListener.invoke(
                            Contracts.Intent.AddComponent(
                                ComponentData(
                                    userId = userId,
                                    locId = 0,
                                    idEnteredByUser = id,
                                    content = content,
                                    textFieldType = type,
                                    maxLines = Integer.parseInt(maxLines),
                                    maxLength = Integer.parseInt(maxLength),
                                    minLength = Integer.parseInt(minLength),
                                    maxValue = Integer.parseInt(maxValue),
                                    minValue = Integer.parseInt(minValue),
                                    isMulti = false,
                                    variants = listOf(),
                                    selected = listOf(),
                                    connectedIds = connectedIds,
                                    connectedValues = connectedValues,
                                    operators = operators,
                                    type = ComponentEnum.Input, id = "",
                                    isRequired = checkBoxState,
                                    rowId = rowId,
                                    weight = if (weight == "0f") "" else weight,
                                    inValues = inValues
                                )
                            )

                        )
                    }
                },
                modifier = Modifier,
                enabled = (weight != "0" || rowId == "") && isTrue
            ) {
                Text(text = "Componentni qoshish")
            }
        }
    }
}


@Preview
@Composable
fun InputContentPreview() {

}