package com.example.adainfoodorderingapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adainfoodorderingapp.databinding.ActivityAddnewItemBinding
import com.example.adainfoodorderingapp.model.AllMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class addnewItem : AppCompatActivity() {
    private lateinit var foodName:String
    private lateinit var foodPrice:String
    private lateinit var foodDiscrebtion:String
    private lateinit var foodIngredent:String
    private var foodImage: Uri? = null
    private lateinit var fireBaseAuth:FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private val binding:ActivityAddnewItemBinding by lazy {
        ActivityAddnewItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fireBaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.backButton.setOnClickListener(){
            finish()
        }

        binding.addItemButton.setOnClickListener(){
            foodName = binding.itemName.text.toString().trim()
            foodPrice = "Rs"+binding.itemPrice.text.toString().trim()
            foodDiscrebtion =  binding.ItemDescription.text.toString().trim()
            foodIngredent = binding.ItemIngredients.text.toString().trim()
            if (!(foodName.isBlank()||foodPrice.isBlank()||foodDiscrebtion.isBlank()||foodIngredent.isBlank())){
                UploadData()
                Toast.makeText(this,"item Added",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Fill all the Details",Toast.LENGTH_SHORT).show()
            }
        }
        binding.itemImageEditText.setOnClickListener(){
            pickImage.launch("image/*")
        }
        binding.FoodImageView.setOnClickListener(){
            pickImage.launch("image/*")
        }
    }

    val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){uri->
        if (uri != null){
            binding.FoodImageView.setImageURI(uri)
            foodImage = uri
        }
    }
    private fun UploadData(){
        val MenuRef = database.getReference("menu")
        val newItemKey = MenuRef.push().key

        if (foodImage != null){
            val storageRef = FirebaseStorage.getInstance().reference
            val imageref = storageRef.child("menu_images/${newItemKey}.jpg")
            val uploadTask = imageref.putFile(foodImage!!)

            uploadTask.addOnSuccessListener {
                imageref.downloadUrl.addOnSuccessListener {
                    downloadUrl->
                    //creating new menu item
                    val newItem = AllMenu(
                        foodName= foodName,
                        foodPrice = foodPrice,
                        foodDiscribtion = foodDiscrebtion,
                        foodIngradent = foodIngredent,
                        foodImage = downloadUrl.toString()
                    )
                    newItemKey?.let {
                        key->
                        MenuRef.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(this,"data uploaded",Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener{
                            Toast.makeText(this,"data uploaded failed",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Image upload Failed", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"select food Image",Toast.LENGTH_SHORT).show()
        }
    }
}