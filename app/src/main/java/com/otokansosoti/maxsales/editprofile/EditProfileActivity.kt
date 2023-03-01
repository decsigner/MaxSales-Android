package com.otokansosoti.maxsales.editprofile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.otokansosoti.maxsales.common.MaskEditUtil
import com.otokansosoti.maxsales.databinding.ActivityEditProfileBinding
import com.otokansosoti.maxsales.fragment.profile.ProfileViewModel
import com.otokansosoti.maxsales.fragment.profile.ProfileViewModelFactory

class EditProfileActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var viewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = createViewModel()
        setupAction()
        setupObservables()
        setupValue()
    }

    private fun createViewModel() : EditProfileViewModel {
        val factory = EditProfileViewModelFactory(application)
        return ViewModelProvider(this, factory).get(EditProfileViewModel::class.java)
    }

    private fun setupValue() {
        val name = intent.getStringExtra("XName")
        val email = intent.getStringExtra("XEmail")

        binding.nameEditText.setText(name)
        binding.emailEditText.setText(email)
    }

    private fun setupObservables() {
        viewModel.showToast().observe(this) { showToast(it) }
        viewModel.backToProfile().observe(this) { backToProfile() }
    }

    private fun setupAction() {
        val saveButton: Button = binding.saveButton
        saveButton.setOnClickListener {
            val model = UpdateProfileModel(
                binding.nameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
            viewModel.updateProfile(this, model)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun backToProfile() {
        finish()
    }
}