package uz.gita.jaxongir.adminformapp.ui.previewitems

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.R
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    textFieldType: TextFieldType,
    maxLines: Int,                      //tipi text bogan input bosa maximal qatorla soni
    maxLength: Int,                     //tipi text bogan input bosa maximal uzunligi
    minLength: Int,                     //tipi text bogan input bosa minimal uzunligi
    maxValue: Int,                      //tipi number bogan inputga qiymatni maximal chegarasi
    minValue: Int,
    question: String,
    componentData: ComponentData,
    deleteComp: (ComponentData) -> Unit,
) {
    var value by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFFF7686), RoundedCornerShape(12.dp))
            .background(Color(0x33C4C4C4))
.padding(horizontal = 16.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (textFieldType) {
            TextFieldType.Email -> {
                TextField(
                    value = value, onValueChange = {
                        value = it
                    },
                    modifier = Modifier
                        .weight(1f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF3951),
                        unfocusedBorderColor = Color(0xFF7686)
                    ),
                    //                maxLength = maxLength,
                    maxLines = maxLines,
//                minLength = minLength,
//                minValue = minValue,
//                maxValue = maxValue,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = { Text(text = question) }
                )
            }

            TextFieldType.Number -> {
                TextField(
                    value = value, onValueChange = {
                        value = it
                    },
                    modifier = Modifier
                        .weight(1f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF3951),
                        unfocusedBorderColor = Color(0xFF7686)
                    ),
                    //                maxLength = maxLength,
                    maxLines = maxLines,
//                minLength = minLength,
//                minValue = minValue,
//                maxValue = maxValue,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = question) }
                )
            }

            TextFieldType.Phone -> {
                TextField(
                    value = value, onValueChange = {
                        value = it
                    },
                    modifier = Modifier
                        .weight(1f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF3951),
                        unfocusedBorderColor = Color(0xFF7686)
                    ),
//                maxLength = maxLength,
                    maxLines = maxLines,
//                minLength = minLength,
//                minValue = minValue,
//                maxValue = maxValue,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    label = { Text(text = question) }
                )
            }

            TextFieldType.Text -> {
                TextField(
                    value = value, onValueChange = {
                        value = it
                    },
                    modifier = Modifier
                        .weight(1f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF3951),
                        unfocusedBorderColor = Color(0xFF7686)
                    ),

//                maxLength = maxLength,
                    maxLines = maxLines,
//                minLength = minLength,
//                minValue = minValue,
//                maxValue = maxValue,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = question) }
                )
            }
        }
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
}

//@Preview(showBackground = true)
//@Composable
//fun getTextFieldPreview() {
//    InputField(TextFieldType.Text, 0, 0, 0, 0, 0, "")
//}