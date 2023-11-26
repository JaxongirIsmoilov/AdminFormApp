package uz.gita.jaxongir.adminformapp.presentation.componentsscreen


import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.presentation.preview.PreviewScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ComponentDirection {
    suspend fun moveToPreviewScreenFromUserAdd(userData: UserData)
    suspend fun moveToPreviewScreenFromMain(userId: String)
}

@Singleton
class ComponentDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : ComponentDirection {
    override suspend fun moveToPreviewScreenFromUserAdd(userData: UserData) {
        navigator.replaceScreen(PreviewScreen(userData))
    }

    override suspend fun moveToPreviewScreenFromMain(userId: String) {
        navigator.back()
    }




}