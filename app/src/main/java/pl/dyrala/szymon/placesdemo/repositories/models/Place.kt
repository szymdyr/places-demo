package pl.dyrala.szymon.placesdemo.repositories.models

data class Place (
    val id: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
)