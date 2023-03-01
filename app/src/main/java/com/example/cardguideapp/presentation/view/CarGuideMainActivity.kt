package com.example.cardguideapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardguideapp.R
import com.example.cardguideapp.databinding.ActivityMainBinding
import com.example.cardguideapp.domain.models.CarInformationModel
import com.example.cardguideapp.presentation.adapters.CarDetailListAdapter
import com.example.cardguideapp.presentation.contract.CarGuideContract
import com.example.cardguideapp.presentation.contract.DetailCardListener
import com.example.cardguideapp.presentation.presenter.CarGuidePresenter

class CarGuideMainActivity : AppCompatActivity(), DetailCardListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var carList: List<CarInformationModel>
    private lateinit var carAdapter: CarDetailListAdapter

    private val presenter: CarGuideContract.Presenter by lazy {
        CarGuidePresenter(assets)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        carList = presenter.getJsonParsedDataWithGson()
        carAdapter = CarDetailListAdapter(carList, this, this as DetailCardListener)
        setContentView()
        setFilters()
    }

    private fun setContentView() {
        binding.carDetailInformationRecyclerView.apply {
            adapter = carAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun updateListSelection(position: Int) {
        carList.apply {
            this.forEach { car ->
                car.isSelected = false
            }
            this[position].isSelected = true
        }

        carAdapter = CarDetailListAdapter(carList, this, this as DetailCardListener)
        binding.carDetailInformationRecyclerView.adapter = carAdapter
        carAdapter.notifyDataSetChanged()
    }

    private fun setFilters() {
        val makeFilterEditText = binding.textMakeInputField
        makeFilterEditText.clearFocus()
        makeFilterEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textMakeInputFieldLayout.hint = ""
            } else {
                binding.textMakeInputFieldLayout.hint = getString(R.string.label_hint_make)
            }
        }
        makeFilterEditText.doOnTextChanged { text, start, before, count ->
            val query = text.toString()
            filterMakeWithQuery(query)
        }
        val modelFilterEditText = binding.textModelInputField

        modelFilterEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textModelInputFieldLayout.hint = ""
            } else {
                binding.textModelInputFieldLayout.hint = getString(R.string.label_hint_model)
            }
        }

        modelFilterEditText.doOnTextChanged { text, start, before, count ->
            val query = text.toString()
            filterModelWithQuery(query)
        }
    }

    private fun filterMakeWithQuery(query: String) {
        carAdapter = CarDetailListAdapter(
            presenter.onMakeQueryChanged(query, carList),
            this,
            this as DetailCardListener
        )
        binding.carDetailInformationRecyclerView.adapter = carAdapter
        carAdapter.notifyDataSetChanged()
    }

    private fun filterModelWithQuery(query: String) {
        carAdapter = CarDetailListAdapter(
            presenter.onModelQueryChanged(query, carList),
            this,
            this as DetailCardListener
        )
        binding.carDetailInformationRecyclerView.adapter = carAdapter
        carAdapter.notifyDataSetChanged()
    }
}
