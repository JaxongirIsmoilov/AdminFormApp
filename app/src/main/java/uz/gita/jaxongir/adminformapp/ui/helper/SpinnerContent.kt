package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
) {
    var variants by remember {
        mutableStateOf(listOf<String>())
    }

    val newVariants = arrayListOf<String>()
    newVariants.addAll(variants)

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
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
                                id = ""
                            )
                        )
                    )
                }, modifier = Modifier.wrapContentWidth()
            ) {
                Text(text = "Componentni qoshish")
            }
        }
    }
}