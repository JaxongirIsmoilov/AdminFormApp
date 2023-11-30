package uz.gita.jaxongir.adminformapp.ui.helper

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
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
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
import uz.gita.jaxongir.adminformapp.utils.toDp

@Composable
fun ImageComponent(
    onEventDispatcher: (Contracts.Intent) -> Unit,
    userId: String,
    idEnteredByUser: String,
) {
    var imageUri: Uri? by remember {
        mutableStateOf(null)
    }

    var ratioX: String by remember {
        mutableStateOf("")
    }

    var imageType by remember { mutableStateOf(ImageTypeEnum.LOCAL) }


    var ratioY: String by remember {
        mutableStateOf("")
    }

    var sizeType: ImageSizeEnum by remember {
        mutableStateOf(ImageSizeEnum.AUTO)
    }
    var textUri by remember {
        mutableStateOf("")
    }

    var customHeight by remember {
        mutableStateOf("")
    }

    var backgroundColor by remember {
        mutableStateOf(Color.Transparent)
    }

    var weight by remember {
        mutableStateOf("0f")
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    AsyncImage(
        model = imageUri,
        contentDescription = null,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(customHeight.toDp())
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop,
    )

    LazyColumn(content = {
        item {
            SampleSpinner(
                list = listOf(
                    ImageTypeEnum.REMOTE.type,
                    ImageTypeEnum.LOCAL.type
                ),
                preselected = ComponentEnum.SampleText.content,
                onSelectionChanged = {
                    when (it) {
                        "From Ethernet" -> {
                            imageType = ImageTypeEnum.REMOTE
                        }

                        "From Gallery" -> {
                            imageType = ImageTypeEnum.LOCAL
                        }

                    }
                },
                content = "Image turini tanlang:"
            )
        }

        item {
            Spacer(modifier = Modifier.height(5.dp))
            when (imageType) {
                ImageTypeEnum.LOCAL -> {
                    Button(
                        onClick = { launcher.launch("image/*") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466)),
                    ) {
                        Text(text = "Upload from gallery")
                    }
                    AsyncImage(
                        model = imageUri,
                        contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop,
                    )


                }

                ImageTypeEnum.REMOTE -> {
                    Spacer(modifier = Modifier.height(5.dp))
                    OutlinedTextField(
                        value = textUri, onValueChange = { textUri = it }, modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth(), label = { Text(text = "Input exist uri") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFF3951),
                            unfocusedBorderColor = Color(0xFFFF7686)
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Button(
                        onClick = { imageUri = textUri.toUri() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466)),
                    ) {
                        Text(text = "Upload from Ethernet")
                    }

                }

                ImageTypeEnum.NONE -> {

                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(5.dp))
            SampleSpinner(
                list = listOf(
                    ImageSizeEnum.RATIO.title,
                    ImageSizeEnum.AUTO.title,
                    ImageSizeEnum.CUSTOM.title

                ),
                preselected = ImageSizeEnum.AUTO.title,
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
        }
        item {
            Spacer(modifier = Modifier.height(5.dp))
            when (sizeType) {
                ImageSizeEnum.AUTO -> {

                }

                ImageSizeEnum.CUSTOM -> {

                    SampleSpinner(
                        list = listOf(
                            "w/3", "w/2","w","2w"
                        ),
                        preselected = ComponentEnum.SampleText.content,
                        onSelectionChanged = {
                            when (it) {
                                "w/3" -> {
                                   customHeight = it
                                }

                                "w/2" -> {
                                    customHeight = it

                                }
                                "w" -> {
                                    customHeight = it

                                }
                                "2w" -> {
                                    customHeight = it

                                }

                            }
                        },
                        content = "Image turini tanlang:"
                    )
                }

                ImageSizeEnum.RATIO -> {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {

                        OutlinedTextField(
                            value = ratioX,
                            onValueChange = { ratioX = it },
                            modifier = Modifier
                                .width(120.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFFF3951),
                                unfocusedBorderColor = Color(0xFFFF7686)
                            ),
                            label = {
                                Text(text = "ratio x")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedTextField(
                            value = ratioY,
                            onValueChange = { ratioY = it },
                            modifier = Modifier
                                .width(120.dp),
                            label = {
                                Text(text = "ratio y")
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFFF3951),
                                unfocusedBorderColor = Color(0xFFFF7686)
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }
        }


        item {
            Spacer(modifier = Modifier.height(5.dp))
            Button(onClick = {
                onEventDispatcher.invoke(
                    Contracts.Intent.UploadPhoto(
                        componentData = ComponentData(
                            id = "",
                            userId = userId,
                            locId = 0L,
                            idEnteredByUser = idEnteredByUser,
                            content = "",
                            textFieldType = TextFieldType.Text,
                            maxLines = 0,
                            maxLength = 0,
                            maxValue = 0,
                            minValue = 0,
                            minLength = 0,
                            isRequired = true,
                            isMulti = false,
                            variants = listOf(),
                            type = ComponentEnum.Image,
                            imgUri = imageUri.toString(),
                            ratioX = ratioX.toInt(),
                            ratioY = ratioY.toInt(),
                            customHeight = customHeight,
                            rowId = "",
                            backgroundColor = backgroundColor.toArgb(),
                            weight = weight
                        )
                    )
                )
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp)
                ) {
                Text(text = "Image qo'shish")
            }
        }
        item {
            Button(onClick = {

            }, modifier = Modifier
                .height(50.dp)
                .width(50.dp)) {
                ColorPickerItem {

                }

            }
        }
    })



}