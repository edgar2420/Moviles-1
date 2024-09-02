package com.example.practicaretrofit.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.practicaretrofit.databinding.ActivityCategoriaListBinding
import com.example.practicaretrofit.models.Categoria
import com.example.practicaretrofit.models.CategoriaDeleteResponse

import com.example.practicaretrofit.repositories.CategoriaRepository
import com.example.practicaretrofit.ui.adapters.CategoriaAdapter


class CategoriaListActivity : AppCompatActivity(), CategoriaAdapter.categoriaEventListener,
    CategoriaRepository.CategoriaListListener, CategoriaRepository.CategoriaDeleteListener {
    private lateinit var binding: ActivityCategoriaListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriaListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.fabAddCategoria.setOnClickListener {
            val intent = Intent(this, CategoriaDetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        CategoriaRepository.fetchListaCategoria(this)
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration =
            DividerItemDecoration(binding.lstCategoria.context, DividerItemDecoration.VERTICAL)
        binding.lstCategoria.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@CategoriaListActivity).apply {
                    orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                }
            adapter = CategoriaAdapter(emptyList(), this@CategoriaListActivity)
            addItemDecoration(dividerItemDecoration)
        }
    }



    //#region PersonaListListener
    override fun onCategoriaListFetched(categoria: List<Categoria>) {
        binding.lstCategoria.adapter.let { adapter ->
            if (adapter is CategoriaAdapter) {
                adapter.updateList(categoria)
            }
        }
    }

    override fun onCategoriaListFetchError(t: Throwable) {
        t.printStackTrace()
    }
    //#endregion
    //#region PersonaDeleteListener

    override fun onCategoriaDeleteSuccess(respuesta: CategoriaDeleteResponse) {
        if (respuesta.res == "success") {
            CategoriaRepository.fetchListaCategoria(this@CategoriaListActivity)
        } else {
            Toast.makeText(
                this@CategoriaListActivity,
                respuesta.reason,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCategoriaDeleteError(t: Throwable) {
        t.printStackTrace()
    }
    interface CategoriaEventListener {
        fun onCategoriaClick(categoria: Categoria)
        fun onCategoriaDeleteClick(categoria: Categoria)
    }

    override fun oncategoriaClick(categoria: Categoria) {
        val intent = Intent(this, CategoriaDetailActivity::class.java)
        intent.putExtra("id", categoria.id)
        startActivity(intent)
    }

    override fun oncategoriaDeleteClick(categoria: Categoria) {
        CategoriaRepository.deleteCategoria(categoria, this)
    }

    //#endregion
}