package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComponentViewModel @Inject constructor(
    private val direction: Contracts.Direction
):ViewModel() {
}