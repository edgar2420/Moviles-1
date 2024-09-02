package com.example.appmovilof.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.appmovilof.api.repositories.RegistroRepository
import com.example.appmovilof.databinding.ActivityRegistroUsuarioBinding
import com.example.appmovilof.models.DataRespuestaRegistro
import com.example.appmovilof.ui.interfaces.RegistroLoaded
import android.util.Patterns
import java.util.regex.Pattern


class RegistroUsuarioActivity : AppCompatActivity(), RegistroLoaded {

    private lateinit var binding: ActivityRegistroUsuarioBinding
    private var nombreCompleto: String = ""
    private var email: String = ""
    private var password: String = ""
    private var ci: String = ""
    private var fechaNacimiento: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupEventListeners()
    }

    fun fetchRegistrarUsuario() {
        nombreCompleto = binding.txtNombreCompletoUser.text.toString()
        email = binding.txtCorreoUser.text.toString()
        password = binding.txtPasswordUser.text.toString()
        ci = binding.txtCiUser.text.toString()
        fechaNacimiento = binding.txtFechaNacUser.text.toString()
        println(nombreCompleto + email + password + ci + fechaNacimiento)
        RegistroRepository.registrarUsuario(this, nombreCompleto, email, password, ci, fechaNacimiento)
//        RegistroRepository.registrarUsuario(this,
//            "Diego Andre Casasola",
//            "diegocasasola23@gmail.com",
//            "23072017Dm",
//            "89122155",
//            "2000-07-18")
    }

    fun setupEventListeners() {
        binding.btnRegistrarUsuario.setOnClickListener {
            validarCampos()
            fetchRegistrarUsuario()
        }
    }

    fun validarCampos() {
        if (nombreCompleto.isEmpty()) {
            binding.txtNombreCompletoUser.setError("Ingrese su nombre")
            return
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Ingrese su correo", Toast.LENGTH_SHORT).show()
            return
        }
        if (!validarEmail(email)) {
            binding.txtCorreoUser.setError("Ingrese un email válido")
            return
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()
            return
        }
        if (ci.isEmpty()) {
            Toast.makeText(this, "Ingrese su Cédula de identidad", Toast.LENGTH_SHORT).show()
            return
        }
        if (fechaNacimiento.isEmpty()) {
            Toast.makeText(this, "Ingrese su Fecha de nacimiento", Toast.LENGTH_SHORT).show()
            return
        }

    }

    override fun onRegistroUsuarioLoaded(dataRespuestaRegistro: DataRespuestaRegistro?) {
        println(dataRespuestaRegistro)

        if (dataRespuestaRegistro == null) {
            Toast.makeText(this, "Verifique los campos", Toast.LENGTH_SHORT).show()
            return
        }

        println(dataRespuestaRegistro)
        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun onErrorLoading(error: Throwable?, message: String) {
        Log.e("ERROR", message, error)
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}