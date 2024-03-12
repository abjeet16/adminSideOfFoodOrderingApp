package com.example.adainfoodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adainfoodorderingapp.databinding.ActivityAdainProfilBinding

class AdainProfil : AppCompatActivity() {
    private val binding:ActivityAdainProfilBinding by lazy {
        ActivityAdainProfilBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        disableEdit()
        binding.backButton.setOnClickListener(){
            finish()
        }
        binding.edit.setOnClickListener(){
            enableEdit()
        }
        binding.saveInformation.setOnClickListener(){
            disableEdit()
        }

    }
    fun disableEdit(){
        binding.apply {
            name.isEnabled = false
            address.isEnabled = false
            phone.isEnabled = false
            password.isEnabled = false
            email.isEnabled = false
        }
    }
    fun enableEdit(){
        binding.apply {
            name.isEnabled = true
            address.isEnabled = true
            phone.isEnabled = true
            password.isEnabled = true
            email.isEnabled = true
        }
    }
}