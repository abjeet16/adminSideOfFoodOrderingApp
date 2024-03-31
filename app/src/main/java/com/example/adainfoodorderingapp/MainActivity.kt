package com.example.adainfoodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adainfoodorderingapp.databinding.ActivityMain2Binding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMain2Binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }
    private lateinit var firebaseAuth: FirebaseAuth
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
    }
}