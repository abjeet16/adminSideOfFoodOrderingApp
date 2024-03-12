package com.example.adainfoodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adainfoodorderingapp.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMain2Binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
    }
}