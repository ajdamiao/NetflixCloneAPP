package com.example.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.netflixclone.databinding.ActivityFormLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

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

        binding.btEntrar.setOnClickListener()
        {
            val email = binding.editEmail.text.toString();
            val senha = binding.editSenha.text.toString();
            val mensagemError = binding.mensagemErro

            if (email.isEmpty() || senha.isEmpty()) {
                mensagemError.setText("Campo de email ou senha nao preenchidos!")
            } else {
                autenticarUsuario()
            }
        }
    }

    private fun autenticarUsuario()
    {
        val email = binding.editEmail.text.toString();
        val senha = binding.editSenha.text.toString();
        val mensagemError = binding.mensagemErro

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show()
                irParaTelaDeFilmes()
            }
        }.addOnFailureListener {

            var erro = it;

            when
            {
                erro is FirebaseAuthInvalidCredentialsException -> mensagemError.setText("Email ou senha incorretos.")
                erro is FirebaseNetworkException -> mensagemError.setText("Sem conexão com a internet")
                else -> mensagemError.setText("Erro ao fazer login.")
            }
        }
    }

    private fun verficiarUsuarioLogado()
    {
        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if(usuarioLogado != null)
        {
            irParaTelaDeFilmes()
        }
    }


    private fun irParaTelaDeFilmes()
    {
        val intent = Intent(this,ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }

}