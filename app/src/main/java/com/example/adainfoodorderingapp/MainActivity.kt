package com.example.adainfoodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adainfoodorderingapp.databinding.ActivityMain2Binding
import com.example.adainfoodorderingapp.model.orderDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMain2Binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }
    private lateinit var database:FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var completedOrderReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        CheckForPendingOrder()
        CheckForCompletedOrder()
        TotalEarning()

        binding.addToMenu.setOnClickListener(){
            startActivity(Intent(this,addnewItem::class.java))
        }
        binding.AllItemMenu.setOnClickListener(){
            startActivity(Intent(this,allMenuItem::class.java))
        }
        binding.DispatchOrder.setOnClickListener(){
            startActivity(Intent(this,OutForDelivery::class.java))
        }
        binding.Profile.setOnClickListener(){
            startActivity(Intent(this,AdainProfil::class.java))
        }
        binding.CreateNewUser.setOnClickListener(){
            startActivity(Intent(this,CreateNewUser::class.java))
        }
        binding.pendingOrder.setOnClickListener(){
            startActivity(Intent(this,pendingOrder::class.java))
        }
        binding.LogOut.setOnClickListener(){
            firebaseAuth.signOut()
            startActivity(Intent(this,Login::class.java))
            finish()
        }
    }

    private fun TotalEarning() {
        var listOfTotalPay = mutableListOf<Int>()
        var PriceInString:Int
         completedOrderReference= database.reference.child("CompletedOrder")
        completedOrderReference.addListenerForSingleValueEvent(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (orderSnapShot in snapshot.children){
                    var completeOrder = orderSnapShot.getValue(orderDetails::class.java)
                    completeOrder?.totalPrice?.let {i->
                        PriceInString = i.drop(2).toInt()
                        listOfTotalPay.add(PriceInString)
                    }
                }
                binding.totalEarning.text = "Rs"+listOfTotalPay.sum().toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    private fun CheckForCompletedOrder() {
        var CompletedOrderReference = database.reference.child("CompletedOrder")
        var CompletedOrderCount = 0
        CompletedOrderReference.addListenerForSingleValueEvent(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                CompletedOrderCount = snapshot.childrenCount.toInt()
                binding.CompletedOrderCount.text = CompletedOrderCount.toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun CheckForPendingOrder() {
        var pendingOrderReference = database.reference.child("order details")
        var pendingOrderItemCount = 0
        pendingOrderReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                pendingOrderItemCount = snapshot.childrenCount.toInt()
                binding.pendingOrderCount.text = pendingOrderItemCount.toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}
