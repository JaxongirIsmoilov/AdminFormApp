package uz.gita.jaxongir.adminformapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun DialogSpinnerPreview() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogSpinner(
    onSaveClick: (String, String, String) -> Unit,
    onClickCancel: () -> Unit,
) {


    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
            onClickCancel()
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(1.dp, Color(0xFFFF3951), RoundedCornerShape(12.dp))
        ) {
            var value by remember {
                mutableStateOf("")
            }

            var id by remember {
                mutableStateOf("")
            }
            var selectesValue by remember {
                mutableStateOf("")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 12.dp, end = 12.dp, bottom = 10.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Mavjud bo'lgan componenta id kiriting!")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedTextField(
                    value = id, onValueChange = {
                        id = it
                    },
                    modifier = Modifier
                        .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF3951),
                        unfocusedBorderColor = Color(0xFFFF7686),
                    ), label = { Text(text = "Component id") }
                )
//                ExposedDropdownMenuBox(
//                    expanded = expanded,
//                    onExpandedChange = {
//                        expanded = !expanded
//                    },
//                ) {
//                    androidx.compose.material3.TextField(
//                        readOnly = true,
//                        value = selected,
//                        onValueChange = { },
//                        label = { Text("Label") },
//                        trailingIcon = {
//                            androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon(
//                                expanded = expanded
//                            )
//                        },
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = Color(0xFFFF3951),
//                            unfocusedBorderColor = Color(0xFFFF7686),
//                        ),
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    ExposedDropdownMenu(
//                        expanded = expanded,
//                        onDismissRequest = {
//                            expanded = false
//                        }
//                    ) {
//                        list.forEach { selectionOption ->
//                            DropdownMenuItem(
//                                text = { Text(text = selectionOption) },
//                                onClick = {
//                                    selectedOptionText = selectionOption
//                                    expanded = false
//                                }
//                            )
//                        }
//                    }
//                }


                SampleSpinner(
                    list = listOf(">", "<", "==", "!="),
                    preselected = ">",
                    onSelectionChanged = { selection -> selectesValue = selection },
                    content = "Tanlang!"
                )
                Spacer(modifier = Modifier.size(10.dp))

                OutlinedTextField(
                    value = value, onValueChange = {
                        value = it
                    },
                    modifier = Modifier
                        .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF3951),
                        unfocusedBorderColor = Color(0xFFFF7686),
                    ), label = { Text(text = "value") }
                )

                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        onSaveClick.invoke(id, selectesValue, value)
                        onClickCancel()
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .height(56.dp)
                        .width(100.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466))
                ) {
                    Text(text = "Saqlash", color = Color(0xFFFFFFFF))
                }

            }

        }
    }
}




