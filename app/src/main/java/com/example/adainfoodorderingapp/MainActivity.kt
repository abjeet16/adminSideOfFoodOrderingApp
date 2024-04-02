package com.example.adainfoodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adainfoodorderingapp.databinding.ActivityMain2Binding
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
        CheckForPendingOrder()
    }
    fun CheckForPendingOrder() {
        database = FirebaseDatabase.getInstance()
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
