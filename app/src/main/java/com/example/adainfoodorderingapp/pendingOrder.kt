package com.example.adainfoodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adainfoodorderingapp.adapters.pendingOrderAdapter
import com.example.adainfoodorderingapp.databinding.ActivityPendingOrderBinding

class pendingOrder : AppCompatActivity() {
    private val binding:ActivityPendingOrderBinding by lazy {
        ActivityPendingOrderBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener(){
            finish()
        }
        val CustomerName = listOf("abjeet","sujeet","amith","rahul")
        val Quanity = listOf(1,6,8,2)
        val FoodImage = listOf(R.drawable.burger,R.drawable.cake,R.drawable.sandwich,R.drawable.frys)
        val adapter = pendingOrderAdapter(CustomerName.toMutableList(),Quanity.toMutableList(),FoodImage.toMutableList(),this)
        binding.RecyclearView.layoutManager = LinearLayoutManager(this)
        binding.RecyclearView.adapter = adapter
    }
}