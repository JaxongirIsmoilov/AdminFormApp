package uz.gita.jaxongir.adminformapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectorItem(
    question: String ="",
    list: List<String> = emptyList()
) {

    val selectedItem = remember { mutableStateOf<String?>(null) }
    var checkboxState by remember { mutableStateOf(List(10) { false }) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "data.question",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(20.dp))

        Row(modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)) {
            Checkbox(
                checked = true,
                onCheckedChange = { state ->
                    selectedItem.value = null
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFFFF7686),
                    uncheckedColor = Color(0x33c4c4c4),
                )
            )

        }
    }
}
@Preview(showBackground = true)
@Composable
fun getSelectorPreview() {
    SelectorItem()
}