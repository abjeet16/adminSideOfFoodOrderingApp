package com.example.adainfoodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adainfoodorderingapp.databinding.ActivityCreateNewUserBinding

class CreateNewUser : AppCompatActivity() {
    private val binding:ActivityCreateNewUserBinding by lazy {
        ActivityCreateNewUserBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener(){
            finish()
        }
    }
}