package uz.gita.jaxongir.adminformapp.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.ui.components.DeleteDialog
import uz.gita.jaxongir.adminformapp.ui.components.ToolBarView
import uz.gita.jaxongir.adminformapp.ui.components.UserItem
import uz.gita.jaxongir.adminformapp.utils.myLog

class MainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: MainContract.ViewModel = getViewModel<MainViewModel>()
        MainScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )

    }
}

@Composable
fun MainScreenContent(
    uiState: State<MainContract.UIState>,
    onEventDispatcher: (MainContract.Intent) -> Unit
) {
    onEventDispatcher.invoke(MainContract.Intent.UpdateUserList)
    val item = UserData("", "", "")
    val currentSelectedItem = remember { mutableStateOf(item) }
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) DeleteDialog(
        onClickDelete = {
            myLog("Current: ${currentSelectedItem.value.userName}")
            onEventDispatcher.invoke(MainContract.Intent.DeleteUser(currentSelectedItem.value))
            showDialog.value = false
        },
        onClickCancel = { showDialog.value = false }
    )
    Box(modifier = Modifier.fillMaxSize()) {
        ToolBarView(text = "Users List")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp),
            contentPadding = PaddingValues(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.value.userList) {
                myLog("${it.userName}")
                currentSelectedItem.value = it
                UserItem(
                    model = it,
                    onClick = {
                        onEventDispatcher.invoke(
                            MainContract.Intent.MoveToComponentsScreen(it)
                        )
                    },
                    onClickDelete = { showDialog.value = true })
            }
        }


        FloatingActionButton(
            onClick = { onEventDispatcher(MainContract.Intent.MoveToAddScreen) },
            modifier = Modifier
                .padding(end = 25.dp, bottom = 25.dp)
                .size(60.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape),
            containerColor = Color(0xFF1285B9),
            contentColor = Color(0xFF1285B9)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier
                        .size(30.dp)
                        .align(
                            Alignment.Center
                        ), tint = Color.White
                )
            }

        }
    }
}