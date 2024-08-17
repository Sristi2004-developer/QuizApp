package com.example.authentication.activities.otheractivities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.authentication.R
import com.example.authentication.databinding.ActivityLoginBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth=FirebaseAuth.getInstance()


        binding.btnLogin.setOnClickListener{
            val userName:String =binding.userName.text.toString()
            val password:String=binding.password.text.toString()

            if(userName.isEmpty()||password.isEmpty()){
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(userName,password)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this,"Login Failed : ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                        }

                    }
            }
        }
        binding.buttonSide.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }
    }
}