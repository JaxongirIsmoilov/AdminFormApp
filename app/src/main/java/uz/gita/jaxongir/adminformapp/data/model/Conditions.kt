package uz.gita.jaxongir.adminformapp.data.model

import java.io.Serializable

data class Conditions(
    val id: String,
    val value: String,
    val operator: String
):Serializable