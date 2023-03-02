package com.otokansosoti.maxsales.product

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.otokansosoti.maxsales.databinding.ActivityProductListBinding
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.product.adapter.ProductListAdapter
import com.otokansosoti.maxsales.root.RootActivity
import com.otokansosoti.maxsales.webview.WebViewActivity

class ProdutListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = createViewModel()
        setupObservers()

        val categoryId = intent.getStringExtra("XCategoryId")
        val categoryName = intent.getStringExtra("XCategoryName")
        binding.categoryTitle.text = categoryName
        if (categoryId != null) {
            viewModel.loadProdutsBy(categoryId, this)
        }
    }

    fun createViewModel(): ProductViewModel {
        val factory = ProductViewModelFactory(application)
        return ViewModelProvider(this, factory).get(ProductViewModel::class.java)
    }

    fun setupObservers() {
        viewModel.showToastError().observe(this) { showErrorToast(it) }
        viewModel.showProductList().observe(this) { setupProductList(it) }
    }

    fun setupProductList(dataSource: List<HomeModel>) {
        val recyclerView = binding.recyclerView
        val adapter = ProductListAdapter(dataSource)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickListener(object: ProductListAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val webViewUrl = dataSource[position].link_action
                openWebView(webViewUrl)
            }
        })
    }

    private fun openWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("XUrl", url)
        startActivity(intent)
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}