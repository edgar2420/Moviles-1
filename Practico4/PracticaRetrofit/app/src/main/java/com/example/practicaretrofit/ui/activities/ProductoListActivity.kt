package com.example.practicaretrofit.ui.activities

import ProductoDeleteListener
import ProductoListListener
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.practicaretrofit.databinding.ActivityProductoListBinding
import com.example.practicaretrofit.models.Producto
import com.example.practicaretrofit.models.ProductoDeleteResponse

import com.example.practicaretrofit.ui.adapters.ProductoAdapter

class ProductoListActivity : AppCompatActivity(), ProductoAdapter.ProductoEventListener,
    ProductoListListener, ProductoDeleteListener {
    private lateinit var binding: ActivityProductoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.fabAddProducto.setOnClickListener {
            val intent = Intent(this, ProductoDetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        ProductoRepository.fetchListaProducto(this)
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration =
            DividerItemDecoration(binding.lstProducto.context, DividerItemDecoration.VERTICAL)
        binding.lstProducto.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@ProductoListActivity).apply {
                    orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                }
            adapter = ProductoAdapter(emptyList(), this@ProductoListActivity)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onProductoClick(producto: Producto) {
        val intent = Intent(this, ProductoDetailActivity::class.java)
        intent.putExtra("id", producto.id)
        startActivity(intent)
    }

    override fun onProductoDeleteClick(producto: Producto) {
        ProductoRepository.deleteProducto(producto, this)
    }


    //#region PersonaListListener
    override fun onProductoListFetched(producto: List<Producto>) {
        binding.lstProducto.adapter.let { adapter ->
            if (adapter is ProductoAdapter) {
                adapter.updateList(producto)
            }
        }
    }

    override fun onProductoListFetchError(t: Throwable) {
        t.printStackTrace()
    }


    override fun onProductoDeleteSuccess(response: ProductoDeleteResponse) {
        if (response.res == "success") {
            ProductoRepository.fetchListaProducto(this@ProductoListActivity)
        } else {
            Toast.makeText(
                this@ProductoListActivity,
                response.reason,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onProductoDeleteError(t: Throwable) {
        t.printStackTrace()
    }

}