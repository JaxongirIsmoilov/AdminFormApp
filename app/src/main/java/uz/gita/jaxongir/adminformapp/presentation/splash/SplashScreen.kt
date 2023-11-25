package uz.gita.jaxongir.adminformapp.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.adminformapp.R

class SplashScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        getViewModel<SplashViewModel>()
        SplashContent()
    }
}

@Composable
fun SplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFF7686))
    ) {

        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "splash",
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .align(Alignment.Center)
        )
        Text(
            text = "Connect",
            color = Color.White,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.align(Alignment.Center).padding(top = 320.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    SplashContent()
}