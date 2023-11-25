package uz.gita.jaxongir.adminformapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import uz.gita.jaxongir.adminformapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Priority() {

    var text: String by remember { mutableStateOf("savolga javob") }

    OutlinedTextField(value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        trailingIcon = {
            IconButton(
                onClick = {
                }
            ) {
                Image(
                    painter = painterResource(id = if (text=="") R.drawable.ic_error else R.drawable.ic_save),
                    contentDescription ="savol"
                )
            }
        },
        singleLine = true,
        label = { Text(text = "Savol") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFFF3951),
            unfocusedBorderColor = Color(0xFFFF7686),
        )
    )
}


@Composable
@Preview(showBackground = true)
fun preview(){
    Priority()
}
