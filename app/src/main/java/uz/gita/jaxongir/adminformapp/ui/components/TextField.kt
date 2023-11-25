package uz.gita.jaxongir.adminformapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    question: String
) {
    var value by remember {
        mutableStateOf("Hello world")
    }
    OutlinedTextField(
        value = value, onValueChange = {
            value= it
        },
        modifier = Modifier
            .fillMaxWidth().background(Color(0x33C4C4C4)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF3951),
            unfocusedBorderColor = Color(0xFFFF7686)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun getTextFieldPreview(){
    InputField("Question")
}