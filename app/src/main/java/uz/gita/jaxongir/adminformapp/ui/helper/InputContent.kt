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
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.Conditions
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner

@Composable
fun InputContent(
    onSaveClickListener: (
        type: TextFieldType,
        maxLines: Int,
        maxLength: Int,
        minLength: Int,
        maxValue: Int,
        minValue: Int,
        required: Boolean,
        content: String,
        conditions: List<Conditions>,
    ) -> Unit,
    id: String,
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

        item{
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

        item{
            TextButton(
                onClick = {
                    onSaveClickListener.invoke(
                        type,
                        Integer.parseInt(maxLines),
                        Integer.parseInt(maxLength),
                        Integer.parseInt(minLength),
                        Integer.parseInt(maxValue),
                        Integer.parseInt(minValue),
                        true,
                        content,
                        conditions
                    )
                },
                modifier = Modifier
            ) {
                Text(text = "Componentni qoshish")
            }
        }

    }

}