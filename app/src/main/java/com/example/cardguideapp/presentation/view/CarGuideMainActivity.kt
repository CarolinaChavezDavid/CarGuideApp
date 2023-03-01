package com.example.cardguideapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardguideapp.databinding.ActivityMainBinding
import com.example.cardguideapp.domain.models.CarInformationModel
import com.example.cardguideapp.presentation.adapters.CarDetailListAdapter
import com.example.cardguideapp.presentation.contract.CarGuideContract
import com.example.cardguideapp.presentation.presenter.CarGuidePresenter

class CarGuideMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var carList: List<CarInformationModel>
    private lateinit var presenter: CarGuideContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CarGuidePresenter()
        carList = presenter.getJsonParsedData(this)
        setContentView()
    }

    private fun setContentView() {
        binding.carDetailInformationRecyclerView.apply {
            adapter = CarDetailListAdapter(carList, context)
            layoutManager = LinearLayoutManager(context)
        }
    }
}
