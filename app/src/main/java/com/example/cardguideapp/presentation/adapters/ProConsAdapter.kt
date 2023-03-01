package com.example.cardguideapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardguideapp.databinding.ProsConsItemBinding

class ProConsAdapter(private val proConsList: List<String>) : RecyclerView.Adapter<ProConsAdapter.ProConsAdapterViewHolder>() {

    inner class ProConsAdapterViewHolder(private val binding: ProsConsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(proCon: String) {
            if(proCon.isNotEmpty()) binding.proConTextView.text = proCon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProConsAdapterViewHolder = ProConsAdapterViewHolder(
        ProsConsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = proConsList.size

    override fun onBindViewHolder(holder: ProConsAdapterViewHolder, position: Int) {
        holder.bind(proConsList[position])
    }
}
