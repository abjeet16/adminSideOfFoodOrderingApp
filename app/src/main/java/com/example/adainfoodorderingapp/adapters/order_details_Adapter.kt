package com.example.adainfoodorderingapp.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adainfoodorderingapp.databinding.OrderPendingLayoutBinding

class order_details_Adapter(private var context: Context,
                            private var FoodName:ArrayList<String>,
                            private var FoodImage:ArrayList<String>,
                            private var FoodQuantity:ArrayList<Int>,
                            private var FoodPrice:ArrayList<String>,) :
    RecyclerView.Adapter<order_details_Adapter.ViewHolder>() {
    inner class ViewHolder(private val binding: OrderPendingLayoutBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                foodName.text = FoodName[position]
                foodPrice.text = FoodPrice[position]
                quantity.text = FoodQuantity[position].toString()
                val uriString = FoodImage[position]
                val uri = Uri.parse(uriString)
                Glide.with(context).load(uri).into(binding.foodImage)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OrderPendingLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = FoodName.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}