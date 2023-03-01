package com.example.cardguideapp.presentation.presenter

import android.content.Context
import com.example.cardguideapp.R
import com.example.cardguideapp.domain.models.CarInformationModel
import com.example.cardguideapp.presentation.contract.CarGuideContract
import org.json.JSONArray
import org.json.JSONObject

class CarGuidePresenter : CarGuideContract.Presenter {

    override fun getJsonParsedData(context: Context): List<CarInformationModel> {
        var carList = mutableListOf<CarInformationModel>()
        val jsonData = context.resources.openRawResource(
            context.resources.getIdentifier(
                "car_list",
                "raw",
                context.packageName
            )
        ).bufferedReader().use { it.readText() }

        val jsonOutput = JSONObject(jsonData)
        val cars = jsonOutput.getJSONArray("") as JSONArray
        for (i in 0 until cars.length()) {
            carList.add(
                CarInformationModel(
                    carModel = cars.getJSONObject(i).getString("model"),
                    costumerPrice = cars.getJSONObject(i).getDouble("customerPrice"),
                    carMake = cars.getJSONObject(i).getString("make"),
                    marketPrice = cars.getJSONObject(i).getDouble("customerPrice"),
                    consList = listOf(),
                    prosList = listOf(),
                    raiting = cars.getJSONObject(i).getInt("rating")
                )
            )
        }
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
            stringsList.add(
                array.getJSONObject(i).get("").toString()
            )
        }
        return stringsList
    }
}
