package uz.gita.jaxongir.adminformapp.ui.previewitems

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageTypeEnum
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
import uz.gita.jaxongir.adminformapp.utils.toDp

@Composable
fun ImagePreview(
    data: ComponentData,
    onClickDelete: () -> Unit
) {
    var imageUri: Uri? by remember { mutableStateOf(null) }
    var imageType by remember { mutableStateOf(ImageTypeEnum.GALLERY) }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    var textUri by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = imageUri,
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(data.customHeight.toDp())
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
        )
        SampleSpinner(
            list = listOf(
                ImageTypeEnum.REMOTE.type,
                ImageTypeEnum.GALLERY.type
            ),
            preselected = ComponentEnum.SampleText.content,
            onSelectionChanged = {
                when (it) {
                    ImageTypeEnum.REMOTE.type -> {
                        imageType = ImageTypeEnum.REMOTE
                    }

                    ImageTypeEnum.GALLERY.type -> {
                        imageType = ImageTypeEnum.GALLERY
                    }

                }
            },
            content = "Image turini tanlang:"
        )
        Spacer(modifier = Modifier.size(10.dp))
        when (imageType) {
            ImageTypeEnum.GALLERY -> {
                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Upload from gallery")
                }
            }


            ImageTypeEnum.REMOTE -> {
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedTextField(
                    value = textUri, onValueChange = { textUri = it }, modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .fillMaxWidth(), label = { Text(text = "Input exist uri") }
                )
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = { imageUri = textUri.toUri() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Upload from Ethernet")
                }

            }

            ImageTypeEnum.NONE -> {

            }
        }
    }
}