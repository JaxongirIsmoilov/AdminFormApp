package uz.gita.jaxongir.adminformapp.presentation.preview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.ui.previewitems.DatePickerPreview
import uz.gita.jaxongir.adminformapp.ui.previewitems.InputField
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner
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
    userData: UserData
) {
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
                            .height(56.dp)
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
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 56.dp)
                    ) {
                        uiState.value.compList.forEach { data ->
                            when (data.type) {
                                ComponentEnum.Spinner -> {
                                    item {
                                        Column(
                                            modifier = Modifier
                                                .padding(
                                                    horizontal = 10.dp, vertical = 15.dp
                                                )
                                                .fillMaxWidth()
                                        ) {
                                            Text(
                                                text = "Spinner",
                                                fontSize = 20.sp,
                                                modifier = Modifier
                                                    .padding(bottom = 10.dp)
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            SampleSpinner(
                                                list = data.variants,
                                                preselected = data.variants.first(),
                                                onSelectionChanged = {},
                                                content = data.content,
                                                modifier = Modifier.padding(bottom = 10.dp)
                                            )
                                        }

                                    }
                                }

                                ComponentEnum.Selector -> {
                                    item {
                                        Column(
                                            modifier = Modifier
                                                .padding(
                                                    horizontal = 10.dp, vertical = 15.dp
                                                )
                                                .fillMaxWidth()
                                        ) {
                                            Spacer(modifier = Modifier.size(10.dp))
                                            Text(
                                                text = "Selector ",
                                                modifier = Modifier.align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.size(10.dp))
                                            SelectorItem(data.content, data.variants, data){
                                                onEventDispatcher.invoke(PreviewContract.Intent.DeleteComponent(data))
                                            }
                                            Spacer(modifier = Modifier.size(10.dp))
                                        }
                                    }
                                }

                                ComponentEnum.SampleText -> {
                                    item {
                                        Column(
                                            modifier = Modifier
                                                .padding(horizontal = 10.dp, vertical = 15.dp)
                                                .fillMaxWidth()
                                        ) {
                                            Spacer(modifier = Modifier.size(10.dp))
                                            Text(
                                                text = "Text View",
                                                modifier = Modifier.align(Alignment.CenterHorizontally)
                                            )
                                            Text(
                                                text = data.content,
                                                fontSize = 22.sp,
                                                modifier = Modifier
                                                    .padding(bottom = 10.dp)
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                        }

                                    }
                                }

                                ComponentEnum.Input -> {
                                    item {
                                        Column(
                                            modifier = Modifier
                                                .padding(horizontal = 10.dp, vertical = 15.dp)
                                                .fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = "Text field")
                                            InputField(
                                                textFieldType = data.textFieldType,
                                                maxLines = data.maxLines,
                                                maxLength = data.maxLength,
                                                minLength = data.minLength,
                                                maxValue = data.maxValue,
                                                minValue = data.minValue,
                                                question = data.content
                                            )
                                            Spacer(modifier = Modifier.fillMaxWidth())

                                        }
                                    }
                                }

                                ComponentEnum.Dater -> {
                                    item {
                                        Column(
                                            modifier = Modifier
                                                .padding(horizontal = 10.dp, vertical = 15.dp)
                                                .fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = "Text field")
//                                            DatePicker(content = data.content)
                                            Spacer(modifier = Modifier.fillMaxWidth())
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
                                userData.userId
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
