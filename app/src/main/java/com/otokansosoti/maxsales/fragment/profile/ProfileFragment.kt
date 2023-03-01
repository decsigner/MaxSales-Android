package com.otokansosoti.maxsales.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.otokansosoti.maxsales.common.popToRoot
import com.otokansosoti.maxsales.databinding.FragmentProfileBinding
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.fragment.profile.adapter.ProfileAdapter

//import com.otokansosoti.maxsales.editprofile.EditProfileActivity

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = createViewModel()

        setupObservables()
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        setupActions()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadProfile(this.requireContext())
    }

    private fun createViewModel() : ProfileViewModel {
        val factory = ProfileViewModelFactory(requireActivity().application)
        return ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
    }

    private fun setupObservables() {
        viewModel.showToastError().observe(this.viewLifecycleOwner) { showToast(it) }
        viewModel.bindingProfile().observe(this.viewLifecycleOwner) { setupProfileUser(it) }
        viewModel.bindingPurchases().observe(this.viewLifecycleOwner) { setupPurchasesList(it) }
    }

    private fun setupActions() {
        val logoutButton: Button = binding.logoutButton
        logoutButton.setOnClickListener {
            viewModel.logoutUser(this.requireContext(), binding.cpfLabel.text.toString())
            this.context?.popToRoot()
        }

        val editProfileButton: Button = binding.editButton
        editProfileButton.setOnClickListener {
//            val intent = Intent(this.requireContext(), EditProfileActivity::class.java)
//            intent.putExtra("XName", binding.nameLabel.text)
//            intent.putExtra("XEmail", binding.emailLabel.text)
//            intent.putExtra("XCPF", binding.cpfLabel.text)
//            startActivity(intent)
        }
    }

    private fun setupProfileUser(model: ProfileModel) {
        binding.nameLabel.text = model.name
        binding.emailLabel.text = model.email
        binding.cpfLabel.text = model.cpf
    }

    private fun setupPurchasesList(dataSource: List<HomeModel>) {
        val recyclerView = binding.recyclerView
        val adapter = ProfileAdapter(dataSource, this.viewLifecycleOwner)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
    }

    private fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }
}