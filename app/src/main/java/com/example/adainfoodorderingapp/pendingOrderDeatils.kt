package com.example.adainfoodorderingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adainfoodorderingapp.adapters.order_details_Adapter
import com.example.adainfoodorderingapp.adapters.pendingOrderAdapter
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
    private var foodNames : ArrayList<String> = arrayListOf()
    private var foodPrices : ArrayList<String> = arrayListOf()
    private var foodQuantity : ArrayList<Int> = arrayListOf()
    private var foodImage : ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener(){
            finish()
        }
        getDataFromIntent()
    }

    private fun getDataFromIntent() {
        val receivedOrderDetails = intent.getSerializableExtra("userOrderDetails") as orderDetails
        receivedOrderDetails?.let { orderDetails ->
                userName = receivedOrderDetails.userName
                foodNames = receivedOrderDetails.foodNames as ArrayList<String>
                foodImage = receivedOrderDetails.foodImage as ArrayList<String>
                foodQuantity = receivedOrderDetails.foodQuantity as ArrayList<Int>
                address = receivedOrderDetails.Address
                phoneNumber = receivedOrderDetails.phoneNumber
                foodPrices = receivedOrderDetails.foodPrice as ArrayList<String>
                totalPrize = receivedOrderDetails.totalPrice

                setUserDetails()
                setAdapter()
        }
    }

    private fun setAdapter() {
        binding.RecyclearView.layoutManager = LinearLayoutManager(this)
        val adapter = order_details_Adapter(this,foodNames,foodImage,foodQuantity,foodPrices)
        binding.RecyclearView.adapter = adapter
    }

    private fun setUserDetails() {
        binding.name.text = userName
        binding.addressEditText.text = address
        binding.phone.text = phoneNumber
    }
}