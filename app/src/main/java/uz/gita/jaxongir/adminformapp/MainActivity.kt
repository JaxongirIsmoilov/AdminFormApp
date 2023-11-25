package uz.gita.jaxongir.adminformapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.jaxongir.adminformapp.presentation.adduser.AddScreen
import uz.gita.jaxongir.adminformapp.presentation.splash.SplashScreen
import uz.gita.jaxongir.adminformapp.ui.theme.AdminFormAppTheme
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigationHandler
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var handler: AppNavigationHandler

    @SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdminFormAppTheme {
                Navigator(screen = SplashScreen()) { navigate ->
                    handler.uiNavigator
                        .onEach { it.invoke(navigate) }
                        .launchIn(lifecycleScope)
                    CurrentScreen()
                }


            }
        }

    }
}

