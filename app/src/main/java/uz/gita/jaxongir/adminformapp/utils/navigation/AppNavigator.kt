package uz.gita.jaxongir.adminformapp.utils.navigation

import cafe.adriel.voyager.androidx.AndroidScreen

typealias MyScreen = AndroidScreen


interface AppNavigator {
    suspend fun addScreen(screen: MyScreen)
    suspend fun replaceScreen(screen: MyScreen)
    suspend fun back()
    suspend fun backUntil(screen: MyScreen)
    suspend fun backToRoot()
    suspend fun replaceAll(screen: MyScreen)
}