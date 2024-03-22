package com.example.adainfoodorderingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adainfoodorderingapp.databinding.ActivitySignInBinding
import com.example.adainfoodorderingapp.model.userModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class signIn : AppCompatActivity() {
    private lateinit var fireBaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var Email:String
    private lateinit var name:String
    private lateinit var Location:String
    private lateinit var NameofRestaurant:String
    private lateinit var password:String

    private val binding:ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //auth And database initializing
        fireBaseAuth = Firebase.auth
        databaseReference = Firebase.database.reference

        val locationList = arrayOf("agra","mysore","delhi","goa")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationList)
        binding.locationListofcity.setAdapter(adapter)

        binding.GoLoginPage.setOnClickListener(){
            startActivity(Intent(this,Login::class.java))
        }
        binding.CreateAccountButton.setOnClickListener(){
                name = binding.Name.text.toString().trim()
                Email = binding.email.text.toString().trim()
                password = binding.Password.text.toString().trim()
                NameofRestaurant = binding.nameOfRestaurant.text.toString().trim()
                if (name.isBlank()||Email.isBlank()||password.isBlank()||NameofRestaurant.isBlank()){
                    Toast.makeText(this,"fill out the details",Toast.LENGTH_SHORT).show()
                }else{
                    createAccount(Email,password)
                }
        }
    }

    private fun createAccount(email: String, password: String) {
        fireBaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(){
            task->
            if (task.isSuccessful){
                Toast.makeText(this,"account created",Toast.LENGTH_SHORT).show()
                saveUserData()
                startActivity(Intent(this,Login::class.java))
                finish()
            }else{
                Toast.makeText(this,"error while creating",Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount: Failure",task.exception)
            }
        }
    }
    //saving data into database
    private fun saveUserData() {
        val user = userModel(name,password,Email,NameofRestaurant)
        val UserId: String = FirebaseAuth.getInstance().currentUser!!.uid
        //saving user data in firebase
        databaseReference.child("Admin User").child(UserId).setValue(user)
    }
}