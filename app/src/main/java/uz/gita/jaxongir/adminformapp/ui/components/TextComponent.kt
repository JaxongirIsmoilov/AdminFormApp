package uz.gita.jaxongir.adminformapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.jaxongir.adminformapp.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextComponent(
    onClickDelete: () -> Unit,
    text:String
) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, Color(0xFFFF7686), RoundedCornerShape(12.dp))
                .background(Color(0x33C4C4C4))
                .padding(horizontal = 16.dp, vertical = 5.dp),
        ) {


            Spacer(modifier = Modifier.size(15.dp))


            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
            ) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        text = text,
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
                    .combinedClickable(onClick = { onClickDelete() })
            )
        }

    Spacer(modifier = Modifier.height(10.dp))
    }

@Preview(showBackground = true)
@Composable
fun getTextCompPrev(){
    TextComponent(onClickDelete = { /*TODO*/ }, text = "Quzimurod")
}