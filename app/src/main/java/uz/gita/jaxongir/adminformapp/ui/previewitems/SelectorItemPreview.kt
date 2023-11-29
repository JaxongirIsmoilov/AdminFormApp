package uz.gita.jaxongir.adminformapp.ui.previewitems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.sp
import uz.gita.jaxongir.adminformapp.R
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.ui.helper.CheckBoxItem

@Composable
fun SelectorItem(
    question: String,
    list: List<String>,
    componentData: ComponentData,
    deleteComp : (ComponentData) -> Unit
) {
    val selectedItem = remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = question,
                fontSize = 22.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            if (componentData.isRequired) {
                Text(
                    text = "Required field!"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
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
        Spacer(modifier = Modifier.size(8.dp))
        list.forEach {
            CheckBoxItem(title = it)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
