package com.example.nextbook.signIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.clear
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import com.example.nextbook.main.MainActivity
import com.example.nextbook.databinding.ActivityLoginBinding
import com.example.nextbook.signIn.model.data.LoginResponse
import com.example.nextbook.signIn.model.RetrofitClient
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding  = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //saving user info with data store
        dataStore = createDataStore(name = "loginPref")

        //Login user
        binding.btnLogin.setOnClickListener {

            val login = "login"
            val handler = binding.handler.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (handler.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Empty Field!", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            RetrofitClient.instance.userLogin(login, handler, password)
                .enqueue(object: Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.body()?.status!!) {

                            lifecycleScope.launch {
                                save(
                                    binding.handler.text.toString(),
                                    binding.password.text.toString()
                                )

                                val intent = Intent(applicationContext, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            }

                        }else {
                            binding.login.text = response.body()?.status.toString()
                        }

                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    //save user info
    private suspend fun save(handler: String, password: String) {
        val dataStoreKey = preferencesKey<String>(handler)
        dataStore.edit { loginPref ->
            loginPref[dataStoreKey] = password
        }
    }

    //read user password
    private suspend fun read(handler: String): String? {
        val dataStoreKey = preferencesKey<String>(handler)
        val preferences = dataStore.data.first()

        return preferences[dataStoreKey]
    }

    //clear user data
    private suspend fun clearUserData() {
        dataStore.edit {
            it.clear()
        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}