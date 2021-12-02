package com.grupo2.appserviexpress

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.SnapshotParser
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.grupo2.appserviexpress.databinding.FragmentServiciosBinding
import com.grupo2.appserviexpress.databinding.ItemListaclientesBinding


class ServiciosFragment : Fragment() {

    private lateinit var mBinding: FragmentServiciosBinding

    private lateinit var mFirebaseAdapter: FirebaseRecyclerAdapter<Cliente,ClienteHolder>
    private lateinit var mLayoutManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentServiciosBinding.inflate(inflater, container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query = FirebaseDatabase.getInstance().reference.child("clientes")
        val option =
        FirebaseRecyclerOptions.Builder<Cliente>().setQuery(query, SnapshotParser {
            val cliente = it.getValue(Cliente::class.java)
            cliente!!.id=it.key!!
            cliente
        }).build()
        //FirebaseRecyclerOptions.Builder<Cliente>()
            //.setQuery(query,Cliente::class.java).build()

        mFirebaseAdapter = object : FirebaseRecyclerAdapter<Cliente,ClienteHolder>(option){

            private lateinit var mContext: Context
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteHolder {
                mContext = parent.context

                val view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_listaclientes,parent,false)
                return ClienteHolder(view)


            }

            override fun onBindViewHolder(holder: ClienteHolder, position: Int, model: Cliente) {

                val cliente = getItem(position)

                with(holder){
                    setListener(cliente)

                    binding.txtNombre.text = cliente.clienteName
                    binding.txtTipoServicio.text = cliente.servicio
                    binding.txtTelefono.text = cliente.telefono
                    binding.txtValor.text = cliente.valor


                }

            }

            override fun onDataChanged() {
                super.onDataChanged()
                mBinding.progressBar.visibility = View.GONE
            }

            override fun onError(error: DatabaseError) {
                super.onError(error)
                Toast.makeText(mContext,error.message,Toast.LENGTH_SHORT).show()
            }

        }
        mLayoutManager = LinearLayoutManager(context)

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mFirebaseAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        mFirebaseAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mFirebaseAdapter.startListening()
    }
    private fun deleteCliente(cliente: Cliente){
        val databaseReference = FirebaseDatabase.getInstance().reference.child("clientes")
        databaseReference.child(cliente.id).removeValue()

    }

    inner class ClienteHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemListaclientesBinding.bind(view)

        fun setListener(cliente: Cliente){
            binding.btnDelete.setOnClickListener { deleteCliente(cliente) }

        }

    }


}