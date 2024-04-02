package com.example.adainfoodorderingapp.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adainfoodorderingapp.databinding.PendingorderlayoutBinding

class pendingOrderAdapter(private val CustomerName: MutableList<String>,
                          private val Quanity: MutableList<String>,
                          private val foodImage: MutableList<String>,
                          private val context:Context,
                          private val itemClicked:OnItemClicked
    )
    :RecyclerView.Adapter<pendingOrderAdapter.ViewHolder>() {
        interface OnItemClicked{
            fun OnItemClickListener(position:Int)
            fun OnItemAcceptClickListner(position: Int)
            fun OnItemDispatchClickListner(position: Int)
        }
    inner class ViewHolder(private val binding:PendingorderlayoutBinding):RecyclerView.ViewHolder(binding.root) {
        private var OrderAccepted = false
        fun bind(position: Int) {
            binding.apply {
                customerName.text = CustomerName[position]
                quantity.text = Quanity[position]
                val uriString = foodImage[position]
                val uri = Uri.parse(uriString)
                Glide.with(context).load(uri).into(binding.FoodImage)

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
                            itemClicked.OnItemAcceptClickListner(position)
                        }else{
                            CustomerName.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showToast("Order Dispatched")
                            itemClicked.OnItemDispatchClickListner(position)
                        }
                    }
                }
                binding.cardView.setOnClickListener{
                    itemClicked.OnItemClickListener(position)
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