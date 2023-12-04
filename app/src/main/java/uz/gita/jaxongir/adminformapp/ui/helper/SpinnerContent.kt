package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts

@Composable
fun SpinnerContent(
    onEventListener: (Contracts.Intent) -> Unit,
    connectedIds: List<String>,
    connectedValues: List<String>,
    operators: List<String>,
    id: String,
    userId: String,
    content: String,
    rowId: String,
    inValues: List<String>,
    isTrue: Boolean
) {
    var variants by remember {
        mutableStateOf(listOf<String>())
    }

    val newVariants = arrayListOf<String>()
    newVariants.addAll(variants)

    var weight by remember {
        mutableStateOf("0")
    }

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {

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

        variants.forEachIndexed { index, s ->
            item {
                OutlinedTextField(
                    value = newVariants[index],
                    onValueChange = {
                        newVariants[index] = it
                        variants = newVariants
                    },
                    label = { Text(text = "${index + 1} - variant") },
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
                    newVariants.add("")
                    variants = newVariants
                },
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Text(text = "Variant qo'shish")

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
                                textFieldType = TextFieldType.Text,
                                maxLines = 0,
                                maxLength = 0,
                                minLength = 0,
                                maxValue = 0,
                                minValue = 0,
                                isMulti = false,
                                variants = variants,
                                selected = listOf(),
                                connectedIds = connectedIds,
                                connectedValues = connectedValues,
                                operators = operators,
                                type = ComponentEnum.Spinner,
                                id = "",
                                rowId = rowId,
                                weight = if (weight == "0f") "" else weight,
                                inValues = inValues
                            )
                        )
                    )
                },
                modifier = Modifier.wrapContentWidth(),
                enabled = (weight != "0" || rowId == "") && isTrue
            ) {
                Text(text = "Componentni qoshish")
            }
        }
    }
}