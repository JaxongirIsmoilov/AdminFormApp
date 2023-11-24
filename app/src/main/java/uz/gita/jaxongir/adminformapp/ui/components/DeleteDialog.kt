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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteDialog(
    onClickDelete: () -> Unit,
    onClickCancel: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
            onClickCancel()
        }
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .background(Color(0xFFFE3951)),
            shape = MaterialTheme.shapes.large
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Dou you really want to delete this user",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.size(30.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { onClickCancel() },
                        modifier = Modifier
                            .background(Color.White)) {
                        Text(text = "Cancel", color = Color(0xFFFD3951))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = { onClickDelete() },
                        modifier = Modifier
                            .background(Color.White)) {
                        Text(text = "Delete",  color = Color(0xFFFD3951))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun getDeleteDialogPreview(){
    DeleteDialog(onClickDelete = { /*TODO*/ }) {

    }
}
