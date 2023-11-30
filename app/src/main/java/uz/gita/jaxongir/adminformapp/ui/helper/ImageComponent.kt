package uz.gita.jaxongir.adminformapp.ui.helper

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
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
    isRequired: Boolean,
    rowId: String,
    typeEnum: ImageTypeEnum,
    weight: String
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

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    when (typeEnum) {
        ImageTypeEnum.LOCAL -> {


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
                            isMulti = false,
                            variants = listOf(),
                            type = ComponentEnum.Image,
                            imgUri = imageUri.toString(),
                            ratioX = ratioX,
                            ratioY = ratioY,
                            customHeight = customHeight,
                            rowId = rowId,
                            backgroundColor = backgroundColor.toArgb(),
                            weight = weight


                        )
                    )
                )
            }) {

            }
        }

        ImageTypeEnum.REMOTE -> {

        }

        ImageTypeEnum.NONE -> {

        }
    }
}