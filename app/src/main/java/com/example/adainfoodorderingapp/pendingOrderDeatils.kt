package com.example.adainfoodorderingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adainfoodorderingapp.databinding.ActivityPendingOrderDeatilsBinding
import com.example.adainfoodorderingapp.model.orderDetails

class pendingOrderDeatils : AppCompatActivity() {
    private val binding:ActivityPendingOrderDeatilsBinding by lazy {
        ActivityPendingOrderDeatilsBinding.inflate(layoutInflater)
    }
    private var userName:String?=null
    private var address:String?=null
    private var phoneNumber:String?=null
    private var totalPrize:String?=null
    private var foodNames : MutableList<String> = mutableListOf()
    private var foodPrices : MutableList<String> = mutableListOf()
    private var foodQuantity : MutableList<Int> = mutableListOf()
    private var foodImage : MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener(){
            finish()
        }
        getDataFromIntent()
    }

    private fun getDataFromIntent() {
        val receivedOrderDetails = intent.getParcelableExtra<orderDetails>("userOrderDetails")
        if (receivedOrderDetails!=null){
            userName = receivedOrderDetails.userName
            foodNames = receivedOrderDetails.foodNames!!
            foodImage = receivedOrderDetails.foodImage!!
            foodQuantity = receivedOrderDetails.foodQuantity!!
            address = receivedOrderDetails.Address
            phoneNumber = receivedOrderDetails.phoneNumber
            foodPrices = receivedOrderDetails.foodPrice!!
            totalPrize = receivedOrderDetails.totalPrice

            setUserDetails()
            setAdapter()
        }
    }

    private fun setAdapter() {
        binding.RecyclearView.layoutManager = LinearLayoutManager(this)
        val adapter =
        binding.RecyclearView.adapter =
    }

    private fun setUserDetails() {
        binding.name.text = userName
        binding.addressEditText.text = address
        binding.phone.text = phoneNumber
    }
}