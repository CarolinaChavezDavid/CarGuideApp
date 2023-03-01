package com.example.cardguideapp.presentation.contract

import com.example.cardguideapp.domain.models.CarInformationModel

interface CarGuideContract {
    interface View

    interface Presenter {
        fun getJsonParsedDataWithGson(): List<CarInformationModel>
        fun onMakeQueryChanged(filterString: String, carList: List<CarInformationModel>): List<CarInformationModel>
        fun onModelQueryChanged(filterString: String, carList: List<CarInformationModel>): List<CarInformationModel>
    }
}
