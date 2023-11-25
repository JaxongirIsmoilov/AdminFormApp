package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextContent(
    onSaveListener: (idComponent: String, text: String) -> Unit,
    id: String
) {
    var text: String by remember {
        mutableStateOf("")
    }
    var idComponent: String by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = idComponent,
            onValueChange = { idComponent = it },
            modifier = Modifier
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .height(56.dp),
            label = { Text("Xohlasangiz id kiritng:") }
        )
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
                .height(56.dp),
            label = { Text("Text View uchun text") }
        )

        Button(
            onClick = { onSaveListener.invoke(id, text) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7686))

        ) {
            Text(text = "Text View ni qo'shish", modifier = Modifier.padding(horizontal = 10.dp))
        }


    }
}