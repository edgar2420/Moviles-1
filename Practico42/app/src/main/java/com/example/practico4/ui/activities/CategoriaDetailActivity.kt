package com.example.practicaretrofit.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicaretrofit.R
import com.example.practicaretrofit.databinding.ActivityCategoriaDetailBinding
import com.example.practicaretrofit.models.Categoria
import com.example.practicaretrofit.models.Producto
import com.example.practicaretrofit.repositories.CategoriaRepository


class CategoriaDetailActivity : AppCompatActivity(), CategoriaRepository.CategoriaDetailListener,
    CategoriaRepository.CategoriaInsertListener,
    CategoriaRepository.CategoriaUpdateListener {
    private var id: Int = 0
    private lateinit var binding: ActivityCategoriaDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id", 0)
        if (id != 0) {
            CategoriaRepository.fetchcategoriaDetail(id, this)
        }
        setupEventListeners()
    }
    private fun setupEventListeners() {
        binding.btnCancel.setOnClickListener { finish() }
        binding.btnSave.setOnClickListener { saveCategoria() }
    }

    private fun saveCategoria() {
        val nombre = binding.txtName.text.toString()


        if (id == 0) {
            CategoriaRepository.saveInsertCategoria(
                nombre,
                this
            )
        } else {
            CategoriaRepository.saveUpdateCategoria(
                id,
                nombre,
                this
            )
        }
    }


    private fun loadcategoriaInForm(categoria: Categoria?) {
        binding.txtName.setText(categoria?.nombre)

    }


    override fun onCategoriaDetailFetched(categoria: Categoria) {
        loadcategoriaInForm(categoria)
    }

    override fun onCategoriaDetailFetchError(t: Throwable) {
        t.printStackTrace()
    }

    override fun onCategoriaInsertSuccess(categoria: Categoria) {
        finish()
    }

    override fun onCategoriaInsertError(t: Throwable) {
        t.printStackTrace()
    }

    override fun onCategoriaUpdateSuccess(categoria: Categoria) {
        finish()
    }

    override fun onCategoriaUpdateError(t: Throwable) {
        t.printStackTrace()
    }

}


