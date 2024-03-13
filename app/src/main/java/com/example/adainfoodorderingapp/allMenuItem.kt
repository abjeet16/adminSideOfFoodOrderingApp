package com.example.adainfoodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adainfoodorderingapp.adapters.allMenuItemAdapter
import com.example.adainfoodorderingapp.databinding.ActivityAllMenuItemBinding
import com.example.adainfoodorderingapp.model.AllMenu
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class allMenuItem : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private var menuItems: ArrayList<AllMenu> = ArrayList()

    private val binding:ActivityAllMenuItemBinding by lazy {
        ActivityAllMenuItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().reference
        retrivemenuItem()
        binding.backButton.setOnClickListener(){
            finish()
        }
    }

    private fun retrivemenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef:DatabaseReference = database.reference.child("menu")
        foodRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //clear old menu
                menuItems.clear()

                //loop for each food item
                for (foodSnapShot in snapshot.children){
                    val menuItem = foodSnapShot.getValue(AllMenu::class.java)
                    menuItem?.let {
                        menuItems.add(it)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DatabaseError", "Error:${error.message}")
            }
        })
    }

    private fun setAdapter() {
        val adapter = allMenuItemAdapter(
            this,menuItems,databaseReference
        )
        binding.RecyclearView.layoutManager = LinearLayoutManager(this)
        binding.RecyclearView.adapter = adapter
    }
}