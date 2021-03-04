package com.example.netflixclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.netflixclone.databinding.ActivityFormCadastroBinding
import com.example.netflixclone.databinding.ActivityFormLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_form_cadastro.*

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // escondi a barra com nome do app e chamei a funcao toolbar
        supportActionBar!!.hide()
        toolBar();

        binding.btCadastrar.setOnClickListener()
        {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem_erro = binding.mensagemErro

            if(email.isEmpty() || senha.isEmpty())
            {

                mensagem_erro.setText("Campo de email ou senha vazio!")
            }
            else
            {
                cadastrarUsuario()
            }
        }

    }

    private fun toolBar()
    {
        val toolbar = binding.toolbarCadastro
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo))
    }

   private fun cadastrarUsuario()
   {
       val email = binding.editEmail.text.toString()
       val senha = binding.editSenha.text.toString()
       val mensagemErro = binding.mensagemErro

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(this,"Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.editEmail.setText("")
                binding.editSenha.setText("")
                binding.mensagemErro.setText("")
            }
        }.addOnFailureListener {

            var erro = it;

            when
            {
                erro is FirebaseAuthWeakPasswordException -> mensagemErro.setText("A senha conter no mínimo 6 caracteres.")
                erro is FirebaseAuthUserCollisionException -> mensagemErro.setText("Email ja cadastrado.")
                erro is FirebaseNetworkException -> mensagemErro.setText("Sem conexão com a internet.")
                else -> mensagemErro.setText("Erro ao cadastrar usuário")
            }
        }
   }
}