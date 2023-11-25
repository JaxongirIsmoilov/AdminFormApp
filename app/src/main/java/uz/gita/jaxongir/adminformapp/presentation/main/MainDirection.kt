package uz.gita.jaxongir.adminformapp.presentation.main

import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.presentation.adduser.AddScreen
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.ComponentScreen
import uz.gita.jaxongir.adminformapp.presentation.preview.PreviewScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject

interface MainDirection {
    suspend fun moveToAddScreen()
    suspend fun moveToComponentScreen(userId: String)
    suspend fun moveToPreviewScreen(userData: UserData)
}

class MainDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
): MainDirection{
    override suspend fun moveToAddScreen() {
        appNavigator.addScreen(AddScreen())
    }

    override suspend fun moveToComponentScreen(userId: String) {
        appNavigator.addScreen(ComponentScreen(userId))
    }

    override suspend fun moveToPreviewScreen(userData: UserData) {
        appNavigator.addScreen(PreviewScreen(userData))
    }

}