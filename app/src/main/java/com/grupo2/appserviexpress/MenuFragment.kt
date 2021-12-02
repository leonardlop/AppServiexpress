package com.grupo2.appserviexpress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.grupo2.appserviexpress.databinding.FragmentMenuBinding
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment() {

    private lateinit var mBinding: FragmentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentMenuBinding.inflate(inflater,container,false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvEmail.text= FirebaseAuth.getInstance().currentUser?.email

        btnLogout.setOnClickListener { cerrar()}




    }

    private fun cerrar() {
       System.exit(0)
    }


}