package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import io.mhssn.colorpicker.ColorPickerDialog
import io.mhssn.colorpicker.ColorPickerType
import io.mhssn.colorpicker.ext.toHex
import uz.gita.jaxongir.adminformapp.R
import java.util.Locale

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ColorPickerItem(
    onClickDelete: () -> Unit
) {
    var color by remember {
        mutableStateOf(Color.White)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(BorderStroke(2.dp, Color(0xFFFF7686)), RoundedCornerShape(12.dp))
            .clickable {
                showDialog = true
            }

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(color = Color(0x33D1D1D1))
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
            ) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        text = "#${color.toHex().substring(2).uppercase(Locale.getDefault())}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                }

            }
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.cancel),
                contentDescription = "",
                modifier = Modifier
                    .size(34.dp)
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { onClickDelete() }
            )
        }
    }

    ColorPickerDialog(
        show = showDialog,
        type = ColorPickerType.Circle(),
        properties = DialogProperties(),
        onDismissRequest = {
            showDialog = false
        },
        onPickedColor = {
            showDialog = false
            color = it
        },
    )
}
//    ColorPicker(
//        type = ColorPickerType.Circle(showAlphaBar = false, showBrightnessBar = false),
//        onPickedColor = {
//            color = it
//        },
//    )
@Preview
@Composable
fun ColorPreview() {
    ColorPickerItem {}
}