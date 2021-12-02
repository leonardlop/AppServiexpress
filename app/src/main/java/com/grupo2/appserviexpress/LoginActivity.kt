package com.grupo2.appserviexpress

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var textUser: EditText
    private lateinit var textPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textUser=findViewById(R.id.textUser)
        textPassword=findViewById(R.id.textPassword)
        progressBar=findViewById(R.id.progressBar2)
        auth= FirebaseAuth.getInstance()

    }
    fun register(view:View){
        startActivity(Intent(this,RegisterActivity::class.java))

    }
    fun login(view:View){
        loginUser()

    }
    private fun loginUser(){
        val user:String=textUser.text.toString()
        val password:String=textPassword.text.toString()

        if(!TextUtils.isEmpty(user)&& !TextUtils.isEmpty(password)){
              progressBar.visibility=View.VISIBLE
            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        textUser.setText("")
                        textPassword.setText("")
                        action()

                        }else{
                        Toast.makeText(this,"Error en la autenticacion",Toast.LENGTH_LONG).show()
                        }



                    }
                }
        }
    private fun action(){
        startActivity(Intent(this,MainActivity::class.java))

    }
    }

