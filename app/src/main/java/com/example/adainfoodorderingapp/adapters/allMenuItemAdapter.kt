package com.example.adainfoodorderingapp.adapters

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adainfoodorderingapp.allMenuItem
import com.example.adainfoodorderingapp.databinding.AllitemmenulayoutBinding
import com.example.adainfoodorderingapp.model.AllMenu
import com.google.firebase.database.DatabaseReference

class allMenuItemAdapter(
    private val context: allMenuItem,
    private val menulist: ArrayList<AllMenu>,
    databaseReference: DatabaseReference
)
    :RecyclerView.Adapter<allMenuItemAdapter.MyCartViewHolder>() {

    private val quantityOfItem = IntArray(menulist.size){1}
    inner class MyCartViewHolder(private val binding:AllitemmenulayoutBinding)
        :RecyclerView.ViewHolder(binding.root){
        private val Image = binding.foodImage
        fun bind(position: Int) {
            val quantity = quantityOfItem[position]
            val menuItem = menulist[position]
            val uriString = menuItem.foodImage
            val uri = Uri.parse(uriString)
            binding.foodName.text = menuItem.foodName
            binding.foodPrice.text = menuItem.foodPrice
            Glide.with(context).load(uri).into(binding.foodImage)
            binding.quantityCartItem.text = quantity.toString()

            binding.AddItem.setOnClickListener(){
                addQuantity()
            }
            binding.DeleteItem.setOnClickListener(){
                deleteQuantity()
            }
            binding.reduseItem.setOnClickListener(){
                reduseQuantity()
            }
        }
        fun reduseQuantity(){
            if (quantityOfItem[adapterPosition]>1) {
                quantityOfItem[adapterPosition]--
                binding.quantityCartItem.text = quantityOfItem[adapterPosition].toString()
            }
        }
        fun deleteQuantity(){
            menulist.removeAt(adapterPosition)
            menulist.removeAt(adapterPosition)
            menulist.removeAt(adapterPosition)
            notifyItemRemoved(adapterPosition)
            notifyItemChanged(adapterPosition,menulist.size)
        }
        fun addQuantity(){
            if (quantityOfItem[adapterPosition]<10) {
                quantityOfItem[adapterPosition]++
                binding.quantityCartItem.text = quantityOfItem[adapterPosition].toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartViewHolder {
            val binding = AllitemmenulayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false)
            return MyCartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menulist.size
    }

    override fun onBindViewHolder(holder: MyCartViewHolder, position: Int) {
        holder.bind(position)
    }
}