package com.otokansosoti.maxsales.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.databinding.ActivityRootBinding
import com.otokansosoti.maxsales.fragment.ContactsFragment
import com.otokansosoti.maxsales.fragment.home.HomeFragment
import com.otokansosoti.maxsales.fragment.profile.ProfileFragment

class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        bottomNavigation = binding.bottomNavigation
        bottomNavigation.setOnNavigationItemSelectedListener(navListener)

        supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, HomeFragment()).commit()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {

        lateinit var selectedFragment: Fragment
        when (it.itemId) {
            R.id.home -> {
                selectedFragment = HomeFragment()
            }
            R.id.profile -> {
                selectedFragment = ProfileFragment()
            }
            R.id.about -> {
                selectedFragment = ContactsFragment()
            }
        }

        supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, selectedFragment).commit()
        true
    }
}