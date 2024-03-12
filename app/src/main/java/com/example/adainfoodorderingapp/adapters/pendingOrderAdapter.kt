package com.example.adainfoodorderingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.adainfoodorderingapp.databinding.PendingorderlayoutBinding

class pendingOrderAdapter(private val CustomerName: MutableList<String>,
                          private val Quanity: MutableList<Int>,
                          private val foodImage: MutableList<Int>,
                          private val context:Context)
    :RecyclerView.Adapter<pendingOrderAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding:PendingorderlayoutBinding):RecyclerView.ViewHolder(binding.root) {
        private var OrderAccepted = false
        fun bind(position: Int) {
            val FoodImage = binding.FoodImage
            binding.apply {
                customerName.text = CustomerName[position]
                quantity.text = Quanity[position].toString()
                FoodImage.setImageResource(foodImage[position])

                acceptButton.apply {
                    if(!OrderAccepted){
                        text = "Accept"
                    }else{
                        text = "Dispatch"
                    }
                    setOnClickListener(){
                        if (!OrderAccepted){
                            text = "Dispatch"
                            OrderAccepted = true
                            showToast("Order Accepted")
                        }else{
                            CustomerName.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showToast("Order Dispatched")
                        }
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PendingorderlayoutBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = CustomerName.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
    private fun showToast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}