package com.ss.fieldbuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.ss.fieldbuzz.Data.AppSession
import com.ss.fieldbuzz.Fragments.HomeFragment
import com.ss.fieldbuzz.Fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        init()
    }

    fun init()
    {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            val token = getString("Token", "Null")


            if (token == "Null")
            {
                val transaction = supportFragmentManager.beginTransaction()
                var fr = LoginFragment()
                transaction.replace(R.id.univarsal, fr)
                //transaction.addToBackStack(null)
                transaction.commit()
            }
            else
            {
                val transaction = supportFragmentManager.beginTransaction()
                var fr = HomeFragment()
                transaction.replace(R.id.univarsal, fr)
                //transaction.addToBackStack(null)
                transaction.commit()
                AppSession.token = token!!
            }

        }

    }
}