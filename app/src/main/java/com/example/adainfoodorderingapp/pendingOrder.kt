package com.example.adainfoodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adainfoodorderingapp.adapters.pendingOrderAdapter
import com.example.adainfoodorderingapp.databinding.ActivityPendingOrderBinding
import com.example.adainfoodorderingapp.model.orderDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pendingOrder : AppCompatActivity(),pendingOrderAdapter.OnItemClicked {
    private val binding:ActivityPendingOrderBinding by lazy {
        ActivityPendingOrderBinding.inflate(layoutInflater)
    }
    private var listOfName:MutableList<String> = mutableListOf()
    private var listOfPrice:MutableList<String> = mutableListOf()
    private var listOfImage:MutableList<String> = mutableListOf()
    private var listOfOrderItem:MutableList<orderDetails> = mutableListOf()
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("order details")
        getallOrderDetails()

        binding.backButton.setOnClickListener(){
            finish()
        }
    }

    private fun getallOrderDetails() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (orderSnapShot in snapshot.children){
                    val orderDetails = orderSnapShot.getValue(orderDetails::class.java)
                    orderDetails?.let {
                        listOfOrderItem.add(it)
                    }
                }
                addDataToRecyclerview()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun addDataToRecyclerview() {
        for (orderItem in listOfOrderItem){
            //adding data to separate lists
            orderItem.userName?.let { listOfName.add(it) }
            orderItem.totalPrice?.let { listOfPrice.add(it) }
            orderItem.foodImage?.filterNot { it.isEmpty() }?.forEach{
                listOfImage.add(it)
            }
        }
        setAdapter()
    }

    private fun setAdapter() {
        binding.RecyclearView.layoutManager = LinearLayoutManager(this)
        val adapter = pendingOrderAdapter(listOfName,listOfPrice,listOfImage,this,this)
        binding.RecyclearView.adapter = adapter
    }

    override fun OnItemClickListener(position: Int) {
        val intent = Intent(this,pendingOrderDeatils::class.java)
        val userOrderDetails = listOfOrderItem[position]
        intent.putExtra("userOrderDetails",userOrderDetails)
        startActivity(intent)
    }
}