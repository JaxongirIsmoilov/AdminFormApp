package uz.gita.jaxongir.adminformapp.ui.previewitems

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.R
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner

@Composable
fun SampleSpinnerPreview(
    list: List<String>,
    preselected: String,
    onSelectionChanged: (selection: String) -> Unit,
    content: String,
    componentData: ComponentData,
    deleteComp : (ComponentData)-> Unit,
    modifier: Modifier=Modifier
) {

    var selected by remember { mutableStateOf(preselected) }
    var expanded by remember { mutableStateOf(false) }

    Box {
        Column {
            Row {
                OutlinedTextField(
                    value = (selected),
                    onValueChange = { },
                    label = { Text(text = content) },
                    modifier = Modifier.weight(1f),
                    trailingIcon = { Icon(Icons.Outlined.ArrowDropDown, null) },
                    readOnly = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF3951),
                        unfocusedBorderColor = Color(0xFFFF7686),
                    )
                )
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = {
                    deleteComp(componentData)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "",
                        tint = Color(0xFFFF3951),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            DropdownMenu(
                modifier = Modifier.wrapContentWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                list.forEach { entry ->
                    DropdownMenuItem(
                        modifier = Modifier.wrapContentWidth(),
                        onClick = {
                            selected = entry
                            expanded = false
                            onSelectionChanged(entry)
                        },
                        text = {
                            Text(
                                text = (entry),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.Start)
                            )
                        }
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(end = 65.dp)
                .clickable(
                    onClick = { expanded = !expanded }
                )
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSimple() {
//    SampleSpinner(listOf("Malle", "Isfan"), "Isfan", {}, Modifier,{})
//}