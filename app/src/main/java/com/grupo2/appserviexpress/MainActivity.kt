package com.grupo2.appserviexpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.grupo2.appserviexpress.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding

    private lateinit var mActiveFragment : Fragment
    private lateinit var mFragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupBottomNav()
    }
    private fun setupBottomNav(){
        mFragmentManager = supportFragmentManager

        val homeFragment = HomeFragment()
        val turnosFragment = TurnosFragment()
        val serviciosFragment = ServiciosFragment()
        val mensajesFragment = MensajesFragment()
        val menuFragment = MenuFragment()

        mActiveFragment = homeFragment

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment,menuFragment,MenuFragment::class.java.name)
            .hide(menuFragment).commit()
        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment,mensajesFragment,MensajesFragment::class.java.name)
            .hide(mensajesFragment).commit()
        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment,serviciosFragment,ServiciosFragment::class.java.name)
            .hide(serviciosFragment).commit()
        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment,turnosFragment,TurnosFragment::class.java.name)
            .hide(turnosFragment).commit()
        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment,homeFragment,HomeFragment::class.java.name).commit()

        mBinding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(homeFragment).commit()
                    mActiveFragment = homeFragment
                    true

                }
                R.id.action_turnos -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(turnosFragment).commit()
                    mActiveFragment = turnosFragment
                    true

                }
                R.id.action_servicios -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(serviciosFragment).commit()
                    mActiveFragment = serviciosFragment
                    true

                }
                R.id.action_mensajes -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(mensajesFragment).commit()
                    mActiveFragment = mensajesFragment
                    true

                }
                R.id.action_Menu -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(menuFragment).commit()
                    mActiveFragment = menuFragment
                    true

                }
                else -> false

            }
        }


    }
}