package com.example.cardguideapp.presentation.presenter

import android.content.res.AssetManager
import com.example.cardguideapp.R
import com.example.cardguideapp.domain.models.CarInformationModel
import com.example.cardguideapp.presentation.contract.CarGuideContract
import org.json.JSONArray

class CarGuidePresenter(private val assetManager: AssetManager) : CarGuideContract.Presenter {
    override fun getJsonParsedDataWithGson(): List<CarInformationModel> {
        var carList = mutableListOf<CarInformationModel>()

        val jsonData = assetManager.open("car_list.json").bufferedReader().use { it.readText() }
        val array = JSONArray(jsonData)

        for (i in 0 until array.length()) {
            val car = array.getJSONObject(i)
            carList.add(
                CarInformationModel(
                    carModel = car.getString("model"),
                    costumerPrice = car.getDouble("customerPrice"),
                    carMake = car.getString("make"),
                    marketPrice = car.getDouble("customerPrice"),
                    consList = getProsConsList(car.getJSONArray("consList")),
                    prosList = getProsConsList(car.getJSONArray("prosList")),
                    car_rating = car.getInt("rating")
                )
            )
        }
        carList.apply { this.first().isSelected = true }
        return setCardImages(carList)
    }

    fun setCardImages(cars: List<CarInformationModel>): List<CarInformationModel> {
        var imagesList = listOf(
            R.drawable.ic_range_rover,
            R.drawable.ic_alpine_roadster,
            R.drawable.ic_bmw_330i,
            R.drawable.ic_mercedez_benz_gcl
        )

        imagesList.forEachIndexed { index, i ->
            cars.apply {
                this[index].carImage = i
            }
        }

        return cars
    }

    private fun getProsConsList(array: JSONArray): List<String> {
        var stringsList = mutableListOf<String>()
        for (i in 0 until array.length()) {
            var newString = array.getString(i)
            if (newString.isNotEmpty()) stringsList.add(newString)
        }
        return stringsList
    }
}
