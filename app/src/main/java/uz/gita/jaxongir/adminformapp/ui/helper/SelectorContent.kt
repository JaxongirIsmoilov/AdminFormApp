package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts
import java.sql.RowId

@Composable
fun SelectorContent(
    onEventListener: (Contracts.Intent) -> Unit,
    connectedIds: List<String>,
    connectedValues: List<String>,
    operators: List<String>,
    id: String,
    content: String,
    userId: String,
    rowId: String
) {
    var variants by remember {
        mutableStateOf(listOf<String>())
    }

    var checkBoxState by remember {
        mutableStateOf(false)
    }

    var weight by remember {
        mutableStateOf("0f")
    }

    val newVariants = arrayListOf<String>()
    newVariants.addAll(variants)

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
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkBoxState,
                    onCheckedChange = { checkBoxState = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFFFF3159),
                        uncheckedColor = Color(0xFFFF7686),
                    ))
                Spacer(modifier = Modifier.size(10.dp))
                androidx.compose.material.Text(
                    text = "Is Required",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (checkBoxState) Color(0xFFFF3159) else Color(0xFFFF7686)
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
                                type = ComponentEnum.Selector,
                                id = "",
                                isRequired = checkBoxState,
                                rowId = rowId,
                                weight = if(weight == "0f") "" else weight
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