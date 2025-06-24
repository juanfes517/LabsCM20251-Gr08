package co.edu.udea.compumovil.gr08_20251.lab2.retrofit.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto (
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)