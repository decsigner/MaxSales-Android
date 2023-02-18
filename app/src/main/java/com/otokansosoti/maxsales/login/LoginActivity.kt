package com.otokansosoti.maxsales.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.common.MaskEditUtil
import com.otokansosoti.maxsales.databinding.ActivityLoginBinding
import com.otokansosoti.maxsales.root.RootActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val cpfEditText = binding.cpfEditText
        cpfEditText.addTextChangedListener(MaskEditUtil.mask(cpfEditText, MaskEditUtil.FORMAT_CPF))
        viewModel = createViewModel()
        viewModel.autoLogin(this)
        configureActions()
        setObservables()
    }

    fun setObservables() {
        viewModel.showToastError().observe(this) { loginError(it) }
        viewModel.showHomeActivity().observe(this) { showHomeActivity() }
    }

    fun configureActions() {
        val loginButton: Button = binding.loginButton
        loginButton.setOnClickListener {
            val cpf = binding.cpfEditText.text
            val password = binding.passwordEditText.text

            if(cpf.isNullOrEmpty())  {
                Toast.makeText(this, "Preencha o CPF", Toast.LENGTH_SHORT).show()
            }

            if (password.isNullOrEmpty()) {
                Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show()
            }

            if (!cpf.isNullOrEmpty() && !password.isNullOrEmpty()) {
                viewModel.login(this, cpf.toString(), password.toString())
            }
        }

        val registerButton: Button = binding.registerButton
        registerButton.setOnClickListener{
            val whatsUrl = "https://api.whatsapp.com/send?phone=5511939466858"
            val whatsIntent = Intent(Intent.ACTION_VIEW)
            whatsIntent.setData(Uri.parse(whatsUrl))
            startActivity(whatsIntent)
        }
    }

    fun createViewModel(): LoginViewModel {
        val factory = LoginViewModelFacotry(application)
        return ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    private fun loginError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showHomeActivity() {
        val rootIntent = Intent(this, RootActivity::class.java)
        startActivity(rootIntent)
    }
}