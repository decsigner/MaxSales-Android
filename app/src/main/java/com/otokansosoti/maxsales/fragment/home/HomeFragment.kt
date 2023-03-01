package com.otokansosoti.maxsales.fragment.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
//import com.otokansosoti.maxsales.about.AboutActivity
import com.otokansosoti.maxsales.databinding.FragmentHomeBinding
import com.otokansosoti.maxsales.fragment.home.adapter.HomeAdapter
import com.otokansosoti.maxsales.fragment.home.viewHolder.product.ProductViewModel
import com.otokansosoti.maxsales.fragment.home.viewHolder.product.ProductViewModelFactory
//import com.otokansosoti.maxsales.webview.WebViewActivity

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = createViewModel()
        productViewModel = createProductViewModel()
        setupObservers()
        viewModel.loadHomeList(this.requireContext())
        return binding.root
    }

    private fun createViewModel() : HomeViewModel {
        val factory = HomeViewModelFactory(requireActivity().application)
        return ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    private fun createProductViewModel() : ProductViewModel {
        val factory = ProductViewModelFactory(requireActivity().application, requireContext())
        return  ViewModelProvider(this, factory).get(ProductViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.showToastError().observe(this.viewLifecycleOwner) { showErrorToast(it) }
        viewModel.setupHomeList().observe(this.viewLifecycleOwner) { setupHomeList(it) }
    }

    private fun setupHomeList(dataSource: List<HomeModel>) {
        val recyclerView = binding.recyclerView
        val adapter = HomeAdapter(dataSource, this.viewLifecycleOwner, this.requireContext())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter.setOnItemClickListener(object : HomeAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                var model = dataSource[position]
                if (model.type == "product" && model.available) {
                    showWebView(model.link_action)
                } else if (model.type == "product" && !model.available) {
                    Toast.makeText(this@HomeFragment.context, "Seu plano não está liberado para este serviço.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        adapter.openSac().observe(this.viewLifecycleOwner) { showSac() }
        adapter.openAbout().observe(this.viewLifecycleOwner) { showAbout() }
    }

    private fun showWebView(url: String) {
//        val intent = Intent(this.requireContext(), WebViewActivity::class.java)
//        intent.putExtra("XUrl", url)
//        startActivity(intent)
    }

    private fun showAbout() {
//        val intent = Intent(this.requireContext(), AboutActivity::class.java)
//        startActivity(intent)
    }

    private fun showSac() {
        val whatsUrl = "https://api.whatsapp.com/send?phone=5511937414931"
        val whatsIntent = Intent(Intent.ACTION_VIEW)
        whatsIntent.setData(Uri.parse(whatsUrl))
        startActivity(whatsIntent)
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }
}
