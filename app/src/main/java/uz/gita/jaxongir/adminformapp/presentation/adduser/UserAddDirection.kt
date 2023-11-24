package uz.gita.jaxongir.adminformapp.presentation.adduser

import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.presentation.components.ComponentScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject

interface UserAddDirection {
    suspend fun moveToComponentScreen(data: UserData)
}

class UserAddDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : UserAddDirection {
    override suspend fun moveToComponentScreen(data: UserData) {
        appNavigator.replaceScreen(ComponentScreen(data))
    }

}