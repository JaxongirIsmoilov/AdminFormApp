package uz.gita.jaxongir.adminformapp.ui.helper

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import uz.gita.jaxongir.adminformapp.data.enums.ImageSizeEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageTypeEnum
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts

@Composable
fun ImageComponent(
    onEventDispatcher: (Contracts.Intent) -> Unit,
    userId: String,
    idEnteredByUser: String,
    isRequired: Boolean,
    rowId: String,
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

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    when (typeEnum) {
        ImageTypeEnum.GALLERY -> {

        }

        ImageTypeEnum.REMOTE -> {

        }

        ImageTypeEnum.NONE -> {

        }
    }
}