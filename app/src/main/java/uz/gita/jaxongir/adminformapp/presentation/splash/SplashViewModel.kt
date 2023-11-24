package uz.gita.jaxongir.adminformapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashDirection: SplashDirection
):ViewModel() {
    init {
        viewModelScope.launch {
            delay(1500L)
            splashDirection.moveToUsersScreen()
        }
    }
}