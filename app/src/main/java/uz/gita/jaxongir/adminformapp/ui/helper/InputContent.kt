package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.Conditions
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
import uz.gita.jaxongir.adminformapp.utils.myLog

@Composable
fun InputContent(
    onEventListener: (Contracts.Intent) -> Unit,
    onSaveClickListener: (
        Contracts.Intent,
    ) -> Unit = {},
    id: String,
    userId: String
) {
    var type by remember {
        mutableStateOf(TextFieldType.Text)
    }

    var content by remember {
        mutableStateOf("")
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

    var conditions by remember {
        mutableStateOf(mutableListOf<Conditions>())
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
                        TextField(
                            value = maxLines,
                            onValueChange = {
                                maxLines = it
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Qatorla soni") }
                        )

                        TextField(
                            value = maxLength,
                            onValueChange = {
                                maxLength = it
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Max length") }
                        )

                        TextField(
                            value = minLength.toString(),
                            onValueChange = {
                                minLength = it
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Min length = ") }
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

                        TextField(
                            value = maxLength.toString(),
                            onValueChange = {
                                maxLength = it
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Max length") }
                        )

                        TextField(
                            value = minLength.toString(),
                            onValueChange = {
                                minLength = it
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Min length = ") }
                        )
                    }
                }

                "Number" -> {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        TextField(
                            value = maxValue.toString(),
                            onValueChange = {
                                maxValue = it
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Max Value = ") }
                        )

                        TextField(
                            value = minValue.toString(),
                            onValueChange = {
                                minValue = it
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Min Value = ") }
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
            TextButton(
                onClick = {
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
                                conditions = listOf(),
                                type = ComponentEnum.Input, id = ""
                            )
                        )
                    )
                },
                modifier = Modifier
            ) {
                Text(text = "Componentni qoshish")
            }
        }
    }

}