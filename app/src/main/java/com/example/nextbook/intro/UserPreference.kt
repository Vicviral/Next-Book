package com.example.nextbook.intro

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.nextbook.databinding.ActivityUserPrefernceBinding

class UserPreference : AppCompatActivity() {

    private lateinit var binding: ActivityUserPrefernceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPrefernceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener{
            val userPref = binding.preferences.selectedButtons
            val userPrefList = mutableListOf<String>()

            for (pref in userPref) {
                userPrefList.add(pref.text)
            }
            binding.array.text = "$userPrefList"
        }

    }
}