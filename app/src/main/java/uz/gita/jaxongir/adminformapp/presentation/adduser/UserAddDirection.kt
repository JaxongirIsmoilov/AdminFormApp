package uz.gita.jaxongir.adminformapp.presentation.adduser

import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.ComponentScreen
import uz.gita.jaxongir.adminformapp.presentation.preview.PreviewScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject

interface UserAddDirection {
    suspend fun moveToPreview(userData: UserData)
}

class UserAddDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : UserAddDirection {
    override suspend fun moveToPreview(userData: UserData) {
        appNavigator.replaceScreen(PreviewScreen(userData))
    }
}