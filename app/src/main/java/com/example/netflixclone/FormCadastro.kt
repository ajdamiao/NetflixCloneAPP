package com.example.netflixclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.netflixclone.databinding.ActivityFormCadastroBinding
import com.example.netflixclone.databinding.ActivityFormLoginBinding
import kotlinx.android.synthetic.main.activity_form_cadastro.*

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val toolbar = binding.toolbarCadastro
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo))


    }
}