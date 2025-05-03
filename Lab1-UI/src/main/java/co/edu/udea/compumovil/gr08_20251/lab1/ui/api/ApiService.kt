package co.edu.udea.compumovil.gr08_20251.lab1.ui.api

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET

interface CountryApi {
    @GET("v3.1/region/americas")
    suspend fun getCountries(): List<Country>
}

interface CityApi {
    @GET("city")
    suspend fun getCities(): List<City>
}

object RetrofitInstance {
    val apiCounty: CountryApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    val apiCity: CityApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api-colombia.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApi::class.java)
    }
}