package com.example.practicaretrofit.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.practicaretrofit.databinding.ActivityVentasListBinding
import com.example.practicaretrofit.models.Ventas
import com.example.practicaretrofit.models.VentasDeleteResponse
import com.example.practicaretrofit.repositories.VentasDeleteListener
import com.example.practicaretrofit.repositories.VentasDetailListener
import com.example.practicaretrofit.repositories.VentasListListener
import com.example.practicaretrofit.repositories.VentasRepository
import com.example.practicaretrofit.ui.adapters.VentasAdapter

class VentasListActivity : AppCompatActivity(), VentasAdapter.VentasEventListener,
    VentasListListener, VentasDeleteListener {
    private lateinit var binding: ActivityVentasListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVentasListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.fabAddVentas.setOnClickListener {
            val intent = Intent(this, VentasDetailListener::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        VentasRepository.fetchListaVentas(this)
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration =
            DividerItemDecoration(binding.lstVentas.context, DividerItemDecoration.VERTICAL)
        binding.lstVentas.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@VentasListActivity).apply {
                    orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                }
            adapter = VentasAdapter(emptyList(), this@VentasListActivity)
            addItemDecoration(dividerItemDecoration)
        }
    }


    override fun onVentasListFetched(ventas: List<Ventas>) {
        binding.lstVentas.adapter.let { adapter ->
            if (adapter is VentasAdapter) {
                adapter.updateList(ventas)
            }
        }
    }

    override fun onVentasListFetchError(t: Throwable) {
        t.printStackTrace()
    }

    override fun onVentasDeleteSuccess(response: VentasDeleteResponse) {
        if (response.res == "success") {
            VentasRepository.fetchListaVentas(this@VentasListActivity)
        } else {
            Toast.makeText(
                this@VentasListActivity,
                response.reason,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onVentasDeleteError(t: Throwable) {
        t.printStackTrace()
    }

    override fun onventasClick(ventas: Ventas) {
        val intent = Intent(this, ProductoDetailActivity::class.java)
        intent.putExtra("id", ventas.id)
        startActivity(intent)
    }

    override fun onventasDeleteClick(ventas: Ventas) {
        VentasRepository.deleteVentas(ventas, this)
    }

}