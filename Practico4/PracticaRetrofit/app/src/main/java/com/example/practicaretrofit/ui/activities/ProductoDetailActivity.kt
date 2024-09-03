package com.example.practicaretrofit.ui.activities

import ProductoDetailListener
import ProductoInsertListener
import ProductoUpdateListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaretrofit.databinding.ActivityProductoDetailBinding
import com.example.practicaretrofit.models.Producto


class ProductoDetailActivity : AppCompatActivity(), ProductoDetailListener, ProductoInsertListener,
    ProductoUpdateListener {
    private var id: Int = 0
    private lateinit var binding: ActivityProductoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id", 0)
        if (id != 0) {
            ProductoRepository.fetchProductoDetail(id, this)
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnCancel.setOnClickListener { finish() }
        binding.btnSave.setOnClickListener { saveProducto() }
    }

    private fun saveProducto() {
        val nombre = binding.txtName.text.toString()
        val descripcion= binding.txtDescripcion.text.toString()
        val precio_actual = binding.txtPrecioActual.text.toString().toDouble()
        val categoria_id = binding.txtCategoria.text.toString().toInt()


        if (id == 0) {
            ProductoRepository.saveInsertProducto(
                nombre,
                descripcion,
                precio_actual,
                categoria_id,
                this
            )
        } else {
            ProductoRepository.saveUpdateProducto(
                id,
                nombre,
                descripcion,
                precio_actual,
                categoria_id,
                this
            )
        }
    }


    private fun loadproductoInForm(producto: Producto?) {
        binding.txtName.setText(producto?.nombre)
        binding.txtDescripcion.setText(producto?.descripcion)
        binding.txtPrecioActual.setText(producto?.precio_actual.toString())
        binding.txtCategoria.setText(producto?.categoria?.id.toString())
    }

    //#region productoDetailListener
    override fun onProductoDetailFetched(producto: Producto) {
        loadproductoInForm(producto)
    }

    override fun onProductoDetailFetchError(t: Throwable) {
        t.printStackTrace()
    }

    //#endregion
    //#region productoInsertListener
    override fun onProductoInsertSuccess(producto: Producto) {
        finish()
    }

    override fun onProductoInsertError(t: Throwable) {
        t.printStackTrace()
    }
    //#endregion
    //#region productoUpdateListener

    override fun onProductoUpdateSuccess(producto: Producto) {
        finish()
    }

    override fun onProductoUpdateError(t: Throwable) {
        t.printStackTrace()
    }

}

