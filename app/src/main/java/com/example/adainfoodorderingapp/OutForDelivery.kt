package com.example.adainfoodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adainfoodorderingapp.adapters.allMenuItemAdapter
import com.example.adainfoodorderingapp.adapters.outforDeveryAdapter
import com.example.adainfoodorderingapp.databinding.ActivityOutForDeliveryBinding
import com.example.adainfoodorderingapp.databinding.OutfordeverylayoutBinding
import java.util.ArrayList

class OutForDelivery : AppCompatActivity() {
    private val binding:ActivityOutForDeliveryBinding by lazy {
        ActivityOutForDeliveryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener(){
            finish()
        }
        val customerName = arrayListOf("Abjeet","Rahul","sujeet","amith","jisnu")
        val paymentStatus = arrayListOf("Not Received","Received","Not Received","Received","Received")
        binding.RecyclearView.layoutManager = LinearLayoutManager(this)
        val adapter = outforDeveryAdapter(customerName,paymentStatus)
        binding.RecyclearView.adapter =adapter
    }
}