package com.example.cardguideapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cardguideapp.databinding.CarDetailItemBinding
import com.example.cardguideapp.domain.models.CarInformationModel

class CarDetailListAdapter(
    private val carInformationList: List<CarInformationModel>,
    private val context: Context
) :
    RecyclerView.Adapter<CarDetailListAdapter.CarDetailViewHolder>() {

    inner class CarDetailViewHolder(private val binding: CarDetailItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: CarInformationModel) {
            binding.apply {
                this.carImageView.setImageDrawable(
                    ResourcesCompat.getDrawable(context.resources, item.carImage, null)
                )
                this.carModelTextView.text = item.carModel
                this.carPriceTextView.text = item.costumerPrice.guideFormat()
                this.carRatingBar.rating = item.raiting.toFloat() }
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
        holder.bind(carInformationList[position])
    }

    fun Double.guideFormat(): String = "${this.div(1000)}k"
}
