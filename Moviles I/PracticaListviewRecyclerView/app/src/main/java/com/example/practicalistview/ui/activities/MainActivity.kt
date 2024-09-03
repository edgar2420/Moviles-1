package com.example.practicalistview.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalistview.R
import com.example.practicalistview.models.Contacto
import com.example.practicalistview.ui.adapters.ContactoRecycleAdapter

class MainActivity : AppCompatActivity(), ContactoRecycleAdapter.OnContactClickListener {
    private lateinit var lstNames: RecyclerView
    private lateinit var adapter: ContactoRecycleAdapter
    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == RESULT_OK){
                val contact = result.data?.getSerializableExtra("respuesta")
                contact?.let {
                    editContact(it as Contacto)
                }
            }
        }

    // private lateinit var contactos: ArrayList<Contacto>
    //  private lateinit var lstNames: ListView

    private fun editContact(obj: Contacto){
        adapter.updateItem(obj)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstNames = findViewById(R.id.lstNames)
        setupListView()
        //  setupListeners()
    }

    /*  private fun setupListeners() {
          lstNames.setOnItemClickListener { _, _, i, _ ->
              val contacto = contactos[i]
              Toast.makeText(this, contacto.telefono, Toast.LENGTH_SHORT).show()
          }
      }*/

    private fun setupListView() {
        val contactos = arrayListOf<Contacto>(
            Contacto(1, "Juan", "Perez", "123456789"),
            Contacto(2, "Pedro", "Perez", "123456789"),
            Contacto(3, "Carlos", "Perez", "123456789"),
            Contacto(4, "Juan", "Perez", "123456789"),
            Contacto(5, "Juan", "Perez", "123456789"),
            Contacto(6, "Juan", "Perez", "123456789"),
            Contacto(7, "Juan", "Perez", "123456789"),
            Contacto(8, "Juan", "Perez", "123456789"),
            Contacto(9, "Juan", "Perez", "123456789"),
            Contacto(10, "Juan", "Perez", "123456789"),
            Contacto(11, "Juan", "Perez", "123456789"),
            Contacto(12, "Juan", "Perez", "123456789"),
            Contacto(13, "Juan", "Perez", "123456789"),
            Contacto(14, "Juan", "Perez", "123456789"),
            Contacto(15, "Juan", "Perez", "123456789"),
            Contacto(16, "Juan", "Perez", "123456789"),
            Contacto(17, "Juan", "Perez", "123456789"),
            Contacto(18, "Juan", "Perez", "123456789"),
            Contacto(19, "Juan", "Perez", "123456789"),
            Contacto(20, "Juan", "Perez", "123456789"),
            Contacto(21, "Juan", "Perez", "123456789"),
            Contacto(22, "Juan", "Perez", "123456789")
        )
        adapter = ContactoRecycleAdapter(contactos, this)
        lstNames.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL,
            false
        )

        //  val adapter = ContactoAdapter(this, contactos, this)
        lstNames.adapter = adapter
    }

    override fun onEditClick(contacto: Contacto) {
      /*  Toast.makeText(
            this,
            "Presionó editar en el contacto: " + contacto.nombres + " " + contacto.apellidos,
            Toast.LENGTH_SHORT
        ).show()
        */
        val intent = Intent(this,DetalleActivity::class.java)
        intent.putExtra("contact",contacto)
        resultLauncher.launch(intent)
    }

    override fun onDeleteClick(contacto: Contacto) {
        adapter.removeItem(contacto)
    }

    /*  override fun onEditClick(contacto: Contacto) {
          Toast.makeText(
              this,
              "Editando contacto: " + contacto.nombres + " " + contacto.apellidos,
              Toast.LENGTH_SHORT
          ).show()
      }*/
}