package com.example.cardguideapp.domain.models

import com.example.cardguideapp.R

data class CarInformationModel(
    var carImage: Int = R.drawable.ic_alpine_roadster,
    var carModel: String,
    var costumerPrice: Double,
    var carMake: String,
    var marketPrice: Double,
    var consList: List<String>,
    var prosList: List<String>,
    var raiting: Int
)
