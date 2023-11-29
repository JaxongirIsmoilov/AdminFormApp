package uz.gita.jaxongir.adminformapp.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts

@Composable
fun ImageComponentFromGallery(
    onEventDispatcher: (Contracts.Intent) -> Unit,
    height: Dp,
) {
    var imageUri: Uri? by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    AsyncImage(
        model = imageUri,
        contentDescription = null,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop,
    )
    Button(onClick = {
        launcher.launch("image/*")
    }) {
        Text(text = "Image gallery", modifier = Modifier.padding(horizontal = 10.dp))
    }
}

@Composable
fun ImageComponentFromRemote(
    onEventDispatcher: (Contracts.Intent) -> Unit,
    height: Dp,
) {
    var textUri: String by remember { mutableStateOf<String>("") }
    var imageUri: String by remember { mutableStateOf("") }
    var customHeight: String by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = imageUri.toUri(),
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .width(100.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(
            value = textUri, onValueChange = { textUri = it }, modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(10.dp))

        Button(onClick = { imageUri = textUri }) {
            Text(text = "Upload Image", modifier = Modifier.padding(horizontal = 10.dp))
        }
        Spacer(modifier = Modifier.size(10.dp))
    }

}

