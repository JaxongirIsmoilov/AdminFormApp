package uz.gita.jaxongir.adminformapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.jaxongir.adminformapp.R
import uz.gita.jaxongir.adminformapp.data.model.UserData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserItem(
    model: UserData,
    onClickDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 15.dp)
            .fillMaxWidth()
            .height(84.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .combinedClickable(onClick = { onClick() }, onLongClick = { onClickDelete() })
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_img),
                contentDescription = "",
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.size(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
            ) {
                Text(
                    text = model.userName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = model.password,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
            }

        }
    }
}