package uz.gita.jaxongir.adminformapp.ui.components

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageSizeEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageTypeEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts
import uz.gita.jaxongir.adminformapp.utils.toDp

@Composable
fun ImageComponent(
    onEventDispatcher: (Contracts.Intent) -> Unit,
    userId: String,
    idEnteredByUser: String,
    isRequired: Boolean
) {
    var imageUri: Uri? by remember { mutableStateOf(null) }
    var imageHeight: String by remember { mutableStateOf("") }
    var imageRatioX: String by remember { mutableStateOf("") }
    var imageRatioY: String by remember { mutableStateOf("") }
    var sizeType by remember { mutableStateOf(ImageSizeEnum.AUTO) }
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
                .height(imageHeight.toDp())
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
                    "From Ethernet" -> {
                        imageType = ImageTypeEnum.REMOTE
                    }

                    "From Gallery" -> {
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
        }

        SampleSpinner(
            list = listOf(
                ImageSizeEnum.RATIO.title,
                ImageSizeEnum.AUTO.title,
                ImageSizeEnum.CUSTOM.title

            ),
            preselected = ComponentEnum.SampleText.content,
            onSelectionChanged = {
                when (it) {
                    "Auto" -> {
                        sizeType = ImageSizeEnum.AUTO
                    }

                    "Custom" -> {
                        sizeType = ImageSizeEnum.CUSTOM
                    }

                    "Ratio" -> {
                        sizeType = ImageSizeEnum.RATIO
                    }
                }
            },
            content = "Component o'lchamini kiriting:"
        )
        when (sizeType) {
            ImageSizeEnum.AUTO -> {

            }

            ImageSizeEnum.CUSTOM -> {

                OutlinedTextField(
                    value = imageHeight,
                    onValueChange = { imageHeight = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            ImageSizeEnum.RATIO -> {
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedTextField(
                    value = imageRatioX,
                    onValueChange = { imageRatioX = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedTextField(
                    value = imageRatioY,
                    onValueChange = { imageRatioY = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
        Button(onClick = {
            launcher.launch("image/*")
        }) {
            Text(text = "Image gallery", modifier = Modifier.padding(horizontal = 10.dp))
        }


        Spacer(modifier = Modifier.size(10.dp))

        Button(onClick = {
            onEventDispatcher.invoke(
                Contracts.Intent.AddComponent(
                    ComponentData(
                        "",
                        userId,
                        0,
                        idEnteredByUser,
                        "",
                        TextFieldType.Text,
                        0, 0, 0, 0, 0, false, listOf(),
                        listOf(),
                        listOf(),
                        listOf(),
                        listOf(),
                        ComponentEnum.Image,
                        isRequired,
                        imageUri.toString(),


                        )
                )
            )
        }) {
            Text(text = "Save Image", modifier = Modifier.padding(horizontal = 10.dp))
        }
    }

}
