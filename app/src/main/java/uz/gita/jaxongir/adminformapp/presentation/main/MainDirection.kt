package uz.gita.jaxongir.adminformapp.presentation.main

import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.presentation.adduser.AddScreen
import uz.gita.jaxongir.adminformapp.presentation.components.ComponentScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject

interface MainDirection {
    suspend fun moveToAddScreen()
    suspend fun moveToComponentScreen(userData: UserData)
}

class MainDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
): MainDirection{
    override suspend fun moveToAddScreen() {
        appNavigator.addScreen(AddScreen())
    }

    override suspend fun moveToComponentScreen(userData: UserData) {
        appNavigator.addScreen(ComponentScreen(userData))
    }

}