package uz.gita.jaxongir.adminformapp.ui.helper

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import io.mhssn.colorpicker.ColorPickerDialog
import io.mhssn.colorpicker.ColorPickerType
import uz.gita.jaxongir.adminformapp.R
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageSizeEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageTypeEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
import uz.gita.jaxongir.adminformapp.utils.toDp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImageComponent(
    onEventDispatcher: (Contracts.Intent) -> Unit,
    userId: String,
    idEnteredByUser: String,
    typeEnum: ImageTypeEnum,
) {
    var imageUri: Uri? by remember {
        mutableStateOf(null)
    }

    var ratioX: String by remember {
        mutableStateOf("0")
    }


    var ratioY: String by remember {
        mutableStateOf("0")
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
        mutableStateOf("0")
    }

    var showDialog by remember {
        mutableStateOf(false)
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
                    Spacer(modifier = Modifier.size(12.dp))

                    SampleSpinner(
                        list = listOf(
                            "w/3", "w/2", "w", "2w"
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
                            onValueChange = {
                                if (it.length <= 2) {
                                    ratioX = it
                                }
                            },
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
                            onValueChange = {
                                if(it.length <= 2 ) {
                                    ratioY = it
                                }
                            },
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
            IconButton(onClick = { showDialog = true }) {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "color picker"
                )
            }
        }
        item {
            Spacer(
                modifier = Modifier
                    .size(25.dp)
                    .background(backgroundColor)
            )
        }

        item {
            Spacer(modifier = Modifier.height(5.dp))
            when (typeEnum) {
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


                    Button(
                        onClick = {
                            Log.d("TTT", "ImageComponent: $imageUri")
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
                                        weight = weight,
                                        imageType = typeEnum
                                    )
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp),
                        enabled = (sizeType == ImageSizeEnum.RATIO && ratioX != "0" && ratioY != "0" && ratioX.isNotEmpty() && ratioY.isNotEmpty()) || sizeType == ImageSizeEnum.CUSTOM || sizeType == ImageSizeEnum.AUTO
                    ) {
                        Text(text = "Image qo'shish")
                    }
                }

                ImageTypeEnum.REMOTE -> {
                    Spacer(modifier = Modifier.height(5.dp))

                    Spacer(modifier = Modifier.height(5.dp))
                    Button(
                        onClick = {
                            onEventDispatcher.invoke(
                                Contracts.Intent.AddComponent(
                                    ComponentData(
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
                                        imgUri = "",
                                        ratioX = ratioX.toInt(),
                                        ratioY = ratioY.toInt(),
                                        customHeight = customHeight,
                                        rowId = "",
                                        backgroundColor = backgroundColor.toArgb(),
                                        weight = weight,
                                        imageType = typeEnum
                                    )
                                )
                            )
                        }
                    ) {
                        Text(text = "Image qo'shish")
                    }

                }

                ImageTypeEnum.NONE -> {

                }
            }
        }


    })

    ColorPickerDialog(
        show = showDialog,
        type = ColorPickerType.Circle(),
        properties = DialogProperties(),
        onDismissRequest = {
            showDialog = false
        },
        onPickedColor = {
            showDialog = false
            backgroundColor = it
        },
    )


}