package uz.gita.jaxongir.adminformapp.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperatorsDailog(
    handleSelected: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
            onCancel()
        }

    ) {
        var id by remember {
            mutableStateOf("")
        }
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Boglanish uchun mavjud bo'lgan id ni kiriting!")
                OutlinedTextField(
                    value = id,
                    onValueChange = { id = it },
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .fillMaxWidth(), label = { Text(text = "component id") }
                )
                Text(
                    text = "Bitta shart tanlang!",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                )
                Spacer(modifier = Modifier.size(30.dp))

                SampleSpinner(
                    list = listOf(">", "==", "!=", "<"),
                    preselected = ">",
                    onSelectionChanged = { selection -> handleSelected.invoke(selection, id) },
                    content = "Tanlang!"
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { onCancel() },
                        modifier = Modifier
                            .background(Color.White),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466))
                    ) {
                        Text(text = "Cancel", color = Color(0xFFFFFFFF))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { onCancel() },
                        modifier = Modifier
                            .background(Color.White),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466))
                    ) {
                        Text(text = "Submit", color = Color(0xFFFFFFFF))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OpertatorsContent() {

}