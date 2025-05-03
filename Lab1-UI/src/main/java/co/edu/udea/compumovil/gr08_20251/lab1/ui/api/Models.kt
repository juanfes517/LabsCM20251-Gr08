package co.edu.udea.compumovil.gr08_20251.lab1.ui.api

data class CountryName(
    val common: String
)

data class Country(
    val name: CountryName
)

data class City(
    val id: Int,
    val name: String
)