package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.ui.previewitems.InputChipExample

@Composable
fun ConditionsContent(
    list: List<String>,
    modifier: Modifier
) {
    var variants by remember {
        mutableStateOf(listOf<String>())
    }

    val newVariants = arrayListOf<String>()
    newVariants.addAll(variants)

    var selected by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.size(10.dp))
        LazyRow(verticalAlignment = Alignment.CenterVertically) {
            variants.forEachIndexed { index, s ->
                item {
                    list.forEach {
                        InputChipExample(text = it) {

                        }
                        Spacer(modifier = Modifier.size(5.dp))
                    }
                }
            }

        }
        Spacer(modifier = Modifier.size(10.dp))
    }
}