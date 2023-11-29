package uz.gita.jaxongir.adminformapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageSizeEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts

@Composable
fun ImageComponent(
    onEventDispatcher: (Contracts.Intent) -> Unit,
    userId: String,
    idEnteredByUser: String,
    isRequired: Boolean
) {

    var imageHeight: String by remember { mutableStateOf("") }
    var imageRatioX: String by remember { mutableStateOf("") }
    var imageRatioY: String by remember { mutableStateOf("") }
    var sizeType by remember { mutableStateOf(ImageSizeEnum.AUTO) }


    Column(modifier = Modifier.fillMaxWidth()) {
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
                        "",
                    )
                )
            )
        }) {
            Text(text = "Save Image", modifier = Modifier.padding(horizontal = 10.dp))
        }
    }

}


