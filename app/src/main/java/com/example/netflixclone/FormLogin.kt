package com.example.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.netflixclone.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.txtTelaCadastro.setOnClickListener()
        {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }


    }
}