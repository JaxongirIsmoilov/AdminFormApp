package uz.gita.jaxongir.adminformapp.presentation.preview

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import uz.gita.jaxongir.adminformapp.R
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageTypeEnum
import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.ui.helper.TextComponent
import uz.gita.jaxongir.adminformapp.ui.previewitems.DatePickerPreview
import uz.gita.jaxongir.adminformapp.ui.previewitems.InputField
import uz.gita.jaxongir.adminformapp.ui.previewitems.SampleSpinnerPreview
import uz.gita.jaxongir.adminformapp.ui.previewitems.SelectorItem

class PreviewScreen(private val userData: UserData) : AndroidScreen() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        val viewModel: PreviewViewModel = getViewModel()

        viewModel.onEventDispatcher(PreviewContract.Intent.LoadData(userData.userId))

        PreviewScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher,
            userData
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PreviewScreenContent(
    uiState: State<PreviewContract.UiState>,
    onEventDispatcher: (PreviewContract.Intent) -> Unit,
    userData: UserData,
) {

    val density = LocalDensity.current
    val weight = LocalConfiguration.current.screenWidthDp

    Box(modifier = Modifier.fillMaxSize()) {

        if (uiState.value.compList.isEmpty()) {
            Text(
                text = "Hozircha bu userga componentalar qoshilmagan. Qo'shish uchun  buttonni ni bosing!",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                color = Color(0xFFFF3951)
            )
            Button(
                onClick = {
                    onEventDispatcher.invoke(
                        PreviewContract.Intent.MoveToComponentScreen(
                            userData.userId
                        )
                    )
                },
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7686))

            ) {
                Text(text = "Yaratish", modifier = Modifier.padding(horizontal = 10.dp))
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(Color(0xFFff7686))
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Componentalar",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }


                    LazyColumn(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(top = 10.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        uiState.value.compList.forEach { data ->
                            when (data.type) {
                                ComponentEnum.Spinner -> {
                                    if (data.rowId == "") {
                                        item {
                                            SampleSpinnerPreview(
                                                list = data.variants,
                                                preselected = data.variants.firstOrNull()?:"",
                                                onSelectionChanged = {},
                                                content = data.content,
                                                componentData = data,
                                                modifier = Modifier,
                                                deleteComp = {
                                                    onEventDispatcher.invoke(
                                                        PreviewContract.Intent.DeleteComponent(
                                                            data
                                                        )
                                                    )

                                                }
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }

                                    }
                                }

                                ComponentEnum.Selector -> {
                                    if (data.rowId == "") {
                                        item {
                                            Column {
                                                SelectorItem(
                                                    question = data.content,
                                                    list = data.variants,
                                                    componentData = data,
                                                    modifier = Modifier,
                                                    deleteComp = {
                                                        onEventDispatcher.invoke(
                                                            PreviewContract.Intent.DeleteComponent(
                                                                data
                                                            )
                                                        )
                                                    }
                                                )
                                                Spacer(modifier = Modifier.height(10.dp))
                                            }
                                        }
                                    }
                                }

                                ComponentEnum.SampleText -> {
                                    if (data.rowId == "") {
                                        item {

                                            TextComponent(
                                                onClickDelete = {
                                                    onEventDispatcher.invoke(
                                                        PreviewContract.Intent.DeleteComponent(data)
                                                    )
                                                },
                                                componentData = data
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }
                                    }
                                }

                                ComponentEnum.Input -> {
                                    if (data.rowId == "") {
                                        item {
                                            Column(modifier = Modifier.fillMaxWidth()) {
                                                if (data.isRequired) {
                                                    Text(
                                                        text = "Required field!",
                                                        modifier = Modifier.align(
                                                            Alignment.CenterHorizontally
                                                        )
                                                    )

                                                }
                                                InputField(
                                                    textFieldType = data.textFieldType,
                                                    maxLines = data.maxLines,
                                                    maxLength = data.maxLength,
                                                    minLength = data.minLength,
                                                    maxValue = data.maxValue,
                                                    minValue = data.minValue,
                                                    question = data.content,
                                                    data, modifier = Modifier,
                                                    deleteComp = {
                                                        onEventDispatcher.invoke(
                                                            PreviewContract.Intent.DeleteComponent(
                                                                data
                                                            )
                                                        )
                                                    }
                                                )
                                                Spacer(modifier = Modifier.height(10.dp))
                                            }
                                        }
                                    }
                                }

                                ComponentEnum.Dater -> {
                                    if (data.rowId == "") {
                                        item {
                                            DatePickerPreview(
                                                componentData = data,
                                                content = data.content,
                                                deleteComp = {
                                                    onEventDispatcher.invoke(
                                                        PreviewContract.Intent.DeleteComponent(
                                                            data
                                                        )
                                                    )
                                                },
                                                isEnabled = true
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }
                                    }
                                }

                                ComponentEnum.Image -> {
                                    if (data.rowId == "") {
                                        val height = when (data.customHeight) {
                                            "w/3" -> {
                                                with(density) { weight.dp / 3 }
                                            }

                                            "w/2" -> {
                                                with(density) { weight.dp / 2 }
                                            }

                                            "w" -> {
                                                with(density) { weight.dp }
                                            }

                                            "2w" -> {
                                                with(density) { weight.dp * 2 }
                                            }

                                            else -> {
                                                0.dp
                                            }

                                        }
                                        item {
                                            if (data.imageType == ImageTypeEnum.LOCAL) {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .background(
                                                            color = Color(
                                                                data.backgroundColor.red,
                                                                data.backgroundColor.green,
                                                                data.backgroundColor.blue

                                                            )
                                                        )
                                                )
                                                {
                                                    AsyncImage(
                                                        model = data.imgUri,
                                                        contentDescription = null,
                                                        modifier = Modifier
                                                            .then(
                                                                if (data.ratioX != 0) {
                                                                    Modifier.aspectRatio(data.ratioX.toFloat() / data.ratioY.toFloat())
                                                                } else if (data.customHeight != "") {
                                                                    Modifier.height(height = height)
                                                                } else {
                                                                    Modifier
                                                                }
                                                            )
                                                    )
                                                }
                                            } else {
                                                var uri by remember {
                                                    mutableStateOf("")
                                                }
                                                Column(
                                                    Modifier
                                                        .fillMaxWidth()
                                                        .background(
                                                            color = Color(
                                                                data.backgroundColor.red,
                                                                data.backgroundColor.green,
                                                                data.backgroundColor.blue
                                                            )
                                                        )) {
                                                    OutlinedTextField(
                                                        value = uri,
                                                        onValueChange = {
                                                            uri = it
                                                        },
                                                        singleLine = true,
                                                        label = {
                                                            Text(text = "Rasm Uri kiriting")
                                                        },
                                                        modifier = Modifier.fillMaxWidth(),
                                                        colors = OutlinedTextFieldDefaults.colors(
                                                            focusedBorderColor = Color(0xFFFF3951),
                                                            unfocusedBorderColor = Color(0xFFFF7686)
                                                        ),
                                                        maxLines = 1,
                                                    )

                                                    AsyncImage(
                                                        model = Uri.parse(uri),
                                                        contentDescription = null,
                                                        modifier = Modifier
                                                            .then(
                                                                if (data.ratioX != 0) {
                                                                    Modifier.aspectRatio(data.ratioX.toFloat() / data.ratioY.toFloat())
                                                                } else if (data.customHeight != "") {
                                                                    Modifier.height(height = height)
                                                                } else {
                                                                    Modifier
                                                                }
                                                            ),
                                                        error = painterResource(id = R.drawable.errorimage)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }

                                ComponentEnum.LazyRow -> {
                                    item {
                                        Row(modifier = Modifier.fillMaxWidth()) {
                                            uiState.value.compList.filter { 
                                                it.rowId == data.idEnteredByUser
                                            }.forEach {
                                                when (it.type) {
                                                    ComponentEnum.Selector -> {
                                                        Box(modifier = Modifier.weight(it.weight.toFloat())) {
                                                            SelectorItem(
                                                                question = data.content,
                                                                list = data.variants,
                                                                componentData = data,
                                                                modifier = Modifier,
                                                                deleteComp = {
                                                                    onEventDispatcher.invoke(
                                                                        PreviewContract.Intent.DeleteComponent(
                                                                            data
                                                                        )
                                                                    )
                                                                }
                                                            )
                                                        }
                                                    }

                                                    ComponentEnum.SampleText -> {
                                                        Box(modifier = Modifier.weight(it.weight.toFloat())) {
                                                            TextComponent(
                                                                onClickDelete = {
                                                                    onEventDispatcher.invoke(
                                                                        PreviewContract.Intent.DeleteComponent(data)
                                                                    )
                                                                },
                                                                componentData = data
                                                            )
                                                            Spacer(modifier = Modifier.height(10.dp))
                                                        }
                                                    }

                                                    ComponentEnum.Spinner -> {
                                                        Box(modifier = Modifier.weight(it.weight.toFloat())) {
                                                            SampleSpinnerPreview(
                                                                list = data.variants,
                                                                preselected = data.variants[0],
                                                                onSelectionChanged = {},
                                                                content = data.content,
                                                                componentData = data,
                                                                modifier = Modifier,
                                                                deleteComp = {
                                                                    onEventDispatcher.invoke(
                                                                        PreviewContract.Intent.DeleteComponent(
                                                                            data
                                                                        )
                                                                    )

                                                                }
                                                            )
                                                        }
                                                    }

                                                    ComponentEnum.Input -> {
                                                            Box(modifier = Modifier.weight(it.weight.toFloat())) {
                                                                var txtField by remember {
                                                                    mutableStateOf("")
                                                                }
                                                                OutlinedTextField(
                                                                    value = txtField,
                                                                    onValueChange = {
                                                                        txtField = it
                                                                    }
                                                                )
                                                            }

                                                        }

                                                    ComponentEnum.Dater -> {
                                                            Box(modifier = Modifier.weight(it.weight.toFloat())) {
                                                                DatePickerPreview(
                                                                    componentData = data,
                                                                    content = data.content,
                                                                    deleteComp = {
                                                                        onEventDispatcher.invoke(
                                                                            PreviewContract.Intent.DeleteComponent(
                                                                                data
                                                                            )
                                                                        )
                                                                    },
                                                                    isEnabled = true
                                                                )
                                                            }
                                                    }

                                                    else -> {

                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.size(56.dp))
                        }
                    }
                }
                Button(
                    onClick = {
                        onEventDispatcher.invoke(
                            PreviewContract.Intent.MoveToComponentScreen(
                                userId = userData.userId
                            )
                        )
                    },
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .align(Alignment.BottomCenter),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7686))
                ) {
                    Text(text = "Tahrirlash", modifier = Modifier.padding(horizontal = 10.dp))
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState", "NewApi")
@Preview
@Composable
fun PreviewScreenPreview() {
    PreviewScreenContent(mutableStateOf(PreviewContract.UiState()), {}, UserData("", "", ""))
}