package com.example.adainfoodorderingapp.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adainfoodorderingapp.databinding.AllitemmenulayoutBinding
import com.example.adainfoodorderingapp.databinding.OutfordeverylayoutBinding

class outforDeveryAdapter(private val CustomerName:ArrayList<String>
                            ,private val PaymentStatus:ArrayList<String>)
    : RecyclerView.Adapter<outforDeveryAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: OutfordeverylayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                customerName.text = CustomerName[position]
                paymentSatus.text = PaymentStatus[position]
                val colormap = mapOf("Not Received" to Color.RED,"Received" to Color.GREEN)
                paymentSatus.setTextColor(colormap[PaymentStatus[position]]?:Color.GRAY)
                deliveryStatus.backgroundTintList = ColorStateList.valueOf(colormap[PaymentStatus[position]]?:Color.GRAY)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OutfordeverylayoutBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = CustomerName.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}