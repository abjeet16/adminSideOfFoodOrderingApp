package com.example.adainfoodorderingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adainfoodorderingapp.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var fireBaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var Email:String
    private lateinit var password:String
    private lateinit var googleSignInClient: GoogleSignInClient

    private val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fireBaseAuth = Firebase.auth
        databaseReference = Firebase.database.reference
        val googleSignOption= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this,googleSignOption)

        binding.GoSignUp.setOnClickListener(){
            startActivity(Intent(this,signIn::class.java))
        }

        //google login
        binding.googleLogin.setOnClickListener(){
            val signIntent = googleSignInClient.signInIntent
            launcher.launch(signIntent)
        }
        binding.LoginButton.setOnClickListener(){
            Email = binding.EmailLoginPage.text.toString().trim()
            password = binding.Password.text.toString().trim()

            if (Email.isBlank()||password.isBlank()){
                Toast.makeText(this,"fill out details",Toast.LENGTH_SHORT).show()
            }else{
                loginUserAccount(Email,password)
            }
        }
    }

    private fun loginUserAccount(email: String, password: String) {
        fireBaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(){
            task->
            if (task.isSuccessful){
                val user : FirebaseUser? = fireBaseAuth.currentUser
                updateUI(user)
            }else{
                Toast.makeText(this,"check email and password",Toast.LENGTH_SHORT).show()
            }
        }
    }
    //sign in with Google
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if (task.isSuccessful){
                val account : GoogleSignInAccount = task.result
                val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                fireBaseAuth.signInWithCredential(credential).addOnCompleteListener(){
                    task->
                    if(task.isSuccessful){
                        Toast.makeText(this,"login Done",Toast.LENGTH_SHORT).show()
                        updateUI(user = null)
                    }else{
                        Toast.makeText(this,"something went Wrong",Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"something went Wrong",Toast.LENGTH_SHORT).show()
            }
        }
    }
    //open main screen if user is already signed in
    override fun onStart() {
        super.onStart()
        val currentUser =  fireBaseAuth.currentUser
        if (currentUser!=null)
            updateUI(user = null)
    }
    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}