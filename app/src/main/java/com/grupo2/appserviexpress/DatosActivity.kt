package com.grupo2.appserviexpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.grupo2.appserviexpress.databinding.ActivityDatosBinding
import kotlinx.android.synthetic.main.activity_datos.*

class DatosActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val guardar = findViewById<Button>(R.id.btGuardar)



    lateinit var binding: ActivityDatosBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btGuardar.setOnClickListener {

            db.collection("clientes").document(etEmail.toString()).set(
                hashMapOf(
                    "clienteName" to etNombre.text.toString(),
                    "servicio" to etServicio.text.toString(),
                    "telefono" to etTelefono.text.toString(),
                    "valor" to etValor.text.toString()

                )
            )
        }



    }


}






