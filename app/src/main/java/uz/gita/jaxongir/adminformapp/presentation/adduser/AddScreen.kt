package uz.gita.jaxongir.adminformapp.presentation.adduser

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.jaxongir.adminformapp.R
import uz.gita.jaxongir.adminformapp.ui.theme.AdminFormAppTheme
import uz.gita.jaxongir.adminformapp.ui.theme.Purple80

class AddScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: UserAddContract.ViewModel = getViewModel<UserAddViewModel>()
        var progressState by remember {
            mutableStateOf(false)
        }

        val context = LocalContext.current
        viewModel.collectSideEffect() {
            when (it) {
                is UserAddContract.SideEffect.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is UserAddContract.SideEffect.ProgressState -> {
                    progressState = it.state
                }
            }
        }
        UserAddScreenContent(
            onEventDispatcher = viewModel::onEventDispatcher,
            isVisibleProgress = progressState
        )
    }
}

@Composable
fun UserAddScreenContent(
    onEventDispatcher: (UserAddContract.Event) -> Unit,
    isVisibleProgress: Boolean
) {
    var username: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Box(){
            Image(
                painter = painterResource(id = R.drawable.register),
                contentDescription = "",
                modifier = Modifier
                    .width(188.dp)
                    .height(80.dp)
            )

            Text(text = "Get Started",  fontSize = 36.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = username, onValueChange = {
                username = it
            }, modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .height(58.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Username") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFF3951),
                unfocusedBorderColor = Color(0xFFFF7686),
            )
        )

        var isPasswordVisible by remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .height(58.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    Image(
                        painter = painterResource(id = if (isPasswordVisible) R.drawable.key1 else R.drawable.key2),
                        contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
                    )
                }
            },
            singleLine = true,
            label = {
                Text(text = "Password")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFF3951),
                unfocusedBorderColor = Color(0xFFFF7686),
            )
        )
        Spacer(modifier = Modifier.height(72.dp))

        Button(
            onClick = {
                onEventDispatcher.invoke(UserAddContract.Event.AddUser(username.trim(), password.trim()))
            }, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                (if (password.length > 3 && username.length > 3) Color(0xFFFF3951) else Color(
                    0xFFFF7686
                ))
            )
        ) {

            if (isVisibleProgress) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(2.dp),
                    color = Purple80,
                    strokeWidth = 4.dp
                )
            } else {
                Text(text = "Add User", fontSize = 22.sp)
            }
        }

    }
}


@Preview
@Composable
fun AddScreenPrev() {
    AdminFormAppTheme() {
        UserAddScreenContent(onEventDispatcher = {}, isVisibleProgress = false)
    }
}