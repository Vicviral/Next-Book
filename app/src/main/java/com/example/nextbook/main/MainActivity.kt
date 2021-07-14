package com.example.nextbook.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.nextbook.R
import com.example.nextbook.databinding.ActivityMainBinding
import com.example.nextbook.main.fragments.ChatFragment
import com.example.nextbook.main.fragments.DiscussionFragment
import com.example.nextbook.main.fragments.adapter.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Bottom navigation view
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setUpBottomNavigationMenu(navController)

    }

    private fun setUpBottomNavigationMenu(navController: NavController) {
        binding.mainBottomNav.let {
            NavigationUI.setupWithNavController(it, navController)
        }

        val badge = binding.mainBottomNav.getOrCreateBadge(R.id.chatFragment)
        badge.isVisible = true
        badge.number = 9

//
    }
}