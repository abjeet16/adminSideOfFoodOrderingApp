package com.example.adainfoodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adainfoodorderingapp.adapters.outforDeveryAdapter
import com.example.adainfoodorderingapp.databinding.ActivityOutForDeliveryBinding
import com.example.adainfoodorderingapp.model.orderDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class OutForDelivery : AppCompatActivity() {
    private val binding:ActivityOutForDeliveryBinding by lazy {
        ActivityOutForDeliveryBinding.inflate(layoutInflater)
    }
    private lateinit var database: FirebaseDatabase
    private var listOfCompleteOrderList:ArrayList<orderDetails> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //retrieve and display completed order
        retrieveCompletedOrderDetails()

        binding.backButton.setOnClickListener(){
            finish()
        }
    }

    private fun retrieveCompletedOrderDetails() {
        database = FirebaseDatabase.getInstance()
        val CompleteOrderReference = database.reference.child("CompletedOrder").orderByChild("currentTime")
        CompleteOrderReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // remove all old data in list
                listOfCompleteOrderList.clear()
                for (orderSnapShot in snapshot.children){
                    val completeOrder = orderSnapShot.getValue(orderDetails::class.java)
                    completeOrder?.let {
                        listOfCompleteOrderList.add(it)
                    }
                }
                setDataIntoRecyclerview()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setDataIntoRecyclerview() {
        val customerName = mutableListOf<String>()
        val paymentStatus = mutableListOf<Boolean>()

        for (order in listOfCompleteOrderList){
            order.userName?.let { customerName.add(it) }
            paymentStatus.add(order.orderRecived)
        }
        val adapter = outforDeveryAdapter(customerName,paymentStatus)
        binding.RecyclearView.adapter = adapter
        binding.RecyclearView.layoutManager = LinearLayoutManager(this)
    }
}