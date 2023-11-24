package uz.gita.jaxongir.adminformapp.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.adminformapp.R

class SplashScreen:AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SplashViewModel>()
        SplashContent()
    }
}

@Composable
fun SplashContent(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFFF3951))){

        Image(painter = painterResource(id = R.drawable.splash), contentDescription = "splash", modifier = Modifier.width(220.dp).height(249.dp).align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview(){
    SplashContent()
}