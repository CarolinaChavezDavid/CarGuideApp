package com.example.cardguideapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cardguideapp.databinding.CarDetailItemBinding
import com.example.cardguideapp.domain.models.CarInformationModel
import com.example.cardguideapp.presentation.contract.DetailCardListener

class CarDetailListAdapter(
    private var carInformationList: List<CarInformationModel>,
    private val context: Context,
    private val carListener: DetailCardListener
) : RecyclerView.Adapter<CarDetailListAdapter.CarDetailViewHolder>() {

    inner class CarDetailViewHolder(private val binding: CarDetailItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: CarInformationModel, position: Int) {
            binding.apply {
                if (item.isSelected) {
                    this.proConsLinearLayout.visibility = View.VISIBLE
                }
                this.carImageView.setImageDrawable(
                    ResourcesCompat.getDrawable(context.resources, item.carImage, null)
                )
                this.carModelTextView.text = item.carModel
                this.carPriceTextView.text = item.costumerPrice.guideFormat()
                this.carRatingBar.rating = item.car_rating.toFloat()
                this.prosListRecyclerView.apply {
                    adapter = ProConsAdapter(item.prosList)
                    layoutManager = LinearLayoutManager(context)
                }
                this.consListRecyclerView.apply {
                    adapter = ProConsAdapter(item.consList)
                    layoutManager = LinearLayoutManager(context)
                }
                this.carDetailItemCardView.setOnClickListener {
                    carListener.updateListSelection(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarDetailViewHolder =
        CarDetailViewHolder(
            CarDetailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = carInformationList.size

    override fun onBindViewHolder(holder: CarDetailViewHolder, position: Int) {
        holder.bind(carInformationList[position], position)
    }
    fun carListUpdated(newList: List<CarInformationModel>) {
        carInformationList = newList
        notifyDataSetChanged()
    }

    fun Double.guideFormat(): String = "Price: ${this.div(1000)}k"
}
