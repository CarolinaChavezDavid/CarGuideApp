package com.example.cardguideapp.domain.models

import com.example.cardguideapp.R
import com.google.gson.annotations.SerializedName

data class CarInformationModel(
    var carImage: Int = R.drawable.ic_alpine_roadster,

    @SerializedName("model")
    var carModel: String,

    @SerializedName("customerPrice")
    var costumerPrice: Double,

    @SerializedName("make")
    var carMake: String,

    @SerializedName("marketPrice")
    var marketPrice: Double,

    @SerializedName("consList")
    var consList: List<String>,

    @SerializedName("prosList")
    var prosList: List<String>,

    @SerializedName("rating")
    var car_rating: Int,

    var isSelected: Boolean = false
)
