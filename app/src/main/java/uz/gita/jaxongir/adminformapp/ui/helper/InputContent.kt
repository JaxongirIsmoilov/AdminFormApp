package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.Conditions
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner

@OptIn(ExperimentalMaterial3Api::class)
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
        conditions: List<Conditions>
    ) -> Unit,
    id: String,
) {
    var type by remember {
        mutableStateOf(TextFieldType.Text)
    }
    var id by remember {
        mutableStateOf("")
    }

    var label by remember { mutableStateOf("") }

    var maxLines by remember { mutableStateOf("") }
    var minSize by remember { mutableStateOf("") }
    var maxSize by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFF7686))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFF7686))
        ) {
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
                        TextFieldType.Text.content -> {
                            type = TextFieldType.Text
                        }

                        TextFieldType.Email.content -> {
                            type = TextFieldType.Email
                        }

                        TextFieldType.Number.content -> {
                            type = TextFieldType.Number
                        }

                        TextFieldType.Phone.content -> {
                            type = TextFieldType.Phone
                        }
                    }
                },
                content = "Text Field turi:",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                when (type) {
                    TextFieldType.Text -> {
                        Column(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = id, onValueChange = {
                                    id = it
                                },
                                modifier = Modifier
                                    .padding(horizontal = 15.dp, vertical = 10.dp)
                                    .fillMaxWidth()
                                    .background(Color(0x33C4C4C4)),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFFFF3951),
                                    unfocusedBorderColor = Color(0xFFFF7686)
                                ), label = { Text(text = "Istasangiz id kiriting") }
                            )
                            OutlinedTextField(
                                value = label, onValueChange = {
                                    label = it
                                },
                                modifier = Modifier
                                    .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                                    .fillMaxWidth()
                                    .background(Color(0x33C4C4C4)),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFFFF3951),
                                    unfocusedBorderColor = Color(0xFFFF7686)
                                ), label = { Text(text = "Text Field uchun label kiriting:") }
                            )
                            OutlinedTextField(
                                value = maxLines,
                                onValueChange = {
                                    maxLines = it
                                },
                                modifier = Modifier
                                    .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                                    .fillMaxWidth()
                                    .background(Color(0x33C4C4C4)),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFFFF3951),
                                    unfocusedBorderColor = Color(0xFFFF7686)
                                ),
                                label = { Text(text = "Max Lines") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            OutlinedTextField(
                                value = minSize, onValueChange = {
                                    minSize = it
                                },
                                modifier = Modifier
                                    .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                                    .fillMaxWidth()
                                    .background(Color(0x33C4C4C4)),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFFFF3951),
                                    unfocusedBorderColor = Color(0xFFFF7686)
                                ), label = { Text(text = "Min length") }
                            )
                            OutlinedTextField(
                                value = maxSize, onValueChange = {
                                    maxSize = it
                                },
                                modifier = Modifier
                                    .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                                    .fillMaxWidth()
                                    .background(Color(0x33C4C4C4)),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFFFF3951),
                                    unfocusedBorderColor = Color(0xFFFF7686)
                                ), label = { Text(text = "Max length") }
                            )

                        }
                    }

                    else -> {}
                }
            }
        }

        Button(onClick = {
            onSaveClickListener.invoke(
                type,
                maxLines.toInt(),
                minSize.toInt(),
                maxSize.toInt(),
                0,
                0,
                false,
                "",
                listOf()
            )
        }, modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(text = "Componentani qo'shish", modifier = Modifier.padding(horizontal = 10.dp))
        }
    }
}

