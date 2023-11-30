package uz.gita.jaxongir.adminformapp.ui.helper

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.KeyboardType
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageSizeEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageTypeEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts

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

    var ratioX: Int by remember {
        mutableStateOf(0)
    }


    var ratioY: Int by remember {
        mutableStateOf(0)
    }

    var sizeType: ImageSizeEnum by remember {
        mutableStateOf(ImageSizeEnum.AUTO)
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

    LazyColumn(content = {

        item {
            when (typeEnum) {
                ImageTypeEnum.LOCAL -> {

                }

                ImageTypeEnum.REMOTE -> {

                }

                ImageTypeEnum.NONE -> {

                }
            }
        }


        item {
            TextButton(onClick = {
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
                            ratioX = ratioX,
                            ratioY = ratioY,
                            customHeight = customHeight,
                            rowId = "",
                            backgroundColor = backgroundColor.toArgb(),
                            weight = weight
                        )
                    )
                )
            }) {
                Text(text = "Image qo'shish")
            }
        }
    })



}