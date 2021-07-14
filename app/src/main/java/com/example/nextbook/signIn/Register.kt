package com.example.nextbook.signIn

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import com.example.nextbook.databinding.ActivityRegisterBinding
import com.example.nextbook.signIn.model.data.RegisterationResponse
import com.example.nextbook.signIn.model.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //numbers of users on NextBook
        val numberOfUsers = "5 million"
        //spannable string for login
        val loinText = SpannableString("SignIn here to join our community of over $numberOfUsers people.")
        val spanColor = ForegroundColorSpan(Color.GREEN)
        loinText.setSpan(spanColor,0, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        binding.login.text = loinText
        //open login screen
        binding.login.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }

        //spannable stings for terms of use
        val termsOfUse = SpannableString("Terms of Services | Privacy Policy")
        binding.terms.text = termsOfUse

        //register user
        binding.register.setOnClickListener {

            val register = "register"
            val handler = binding.handler.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val fullName = binding.fullName.text.toString().trim()

            if (handler.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                Toast.makeText(applicationContext, "Missing field!", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            RetrofitClient.instance.registerUser(register, handler, password, fullName)
                .enqueue(object: Callback<RegisterationResponse>{
                    override fun onResponse(
                        call: Call<RegisterationResponse>,
                        response: Response<RegisterationResponse>
                    ) {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<RegisterationResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }
                })

        }

    }
}