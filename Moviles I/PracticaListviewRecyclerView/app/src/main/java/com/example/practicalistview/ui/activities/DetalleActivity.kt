package com.example.practicalistview.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicalistview.R
import com.example.practicalistview.databinding.ActivityDetalleBinding
import com.example.practicalistview.models.Contacto

class DetalleActivity : AppCompatActivity() {
    private var id: Int = 0
    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadContact()
        setupListeners()
    }

    private fun saveContact() {
        val nombre = binding.txtNombres.text.toString()
        val apellido = binding.txtApellidos.text.toString()
        val telefono = binding.txtTelefono.text.toString()
        val contacto = Contacto(id, nombre, apellido, telefono)
        val intent = intent
        intent.putExtra("respuesta", contacto)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun setupListeners() {
        binding.btnCancelar.setOnClickListener { finish() }
        binding.btnGuardar.setOnClickListener { saveContact() }
    }

    private fun loadContact() {
        if (intent.getSerializableExtra("contact") == null) {
            return
        }
        val contact = intent.getSerializableExtra("contact") as Contacto
        val nombre = contact.nombres
        val apellidos = contact.apellidos
        val telefono = contact.telefono
        binding.txtNombres.setText(nombre)
        binding.txtApellidos.setText(apellidos)
        binding.txtTelefono.setText(telefono)
        this.id = contact.id
    }
}