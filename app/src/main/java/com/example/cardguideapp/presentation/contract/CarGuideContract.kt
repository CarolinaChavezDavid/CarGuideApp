package com.example.cardguideapp.presentation.contract

import android.content.Context
import com.example.cardguideapp.domain.models.CarInformationModel

interface CarGuideContract {
    interface View {}

    interface Presenter{
        fun getJsonParsedData(context: Context): List<CarInformationModel>
    }
}
