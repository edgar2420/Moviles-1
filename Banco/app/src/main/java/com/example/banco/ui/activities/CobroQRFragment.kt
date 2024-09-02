package com.example.banco.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.appmovilof.models.DataCuentaUser
import com.example.appmovilof.ui.interfaces.CobroQRLoaded
import com.example.banco.R
import com.example.banco.databinding.FragmentCobroQRBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CobroQRFragment : Fragment(), CobroQRLoaded {

    private lateinit var binding: FragmentCobroQRBinding
    private lateinit var mySpinner: Spinner
    private var accessToken: String = ""
    private var codes: MutableList<String> = ArrayList()
    private var idCuenta: Int = 0
    private var imagen: String = ""
    private var listaCuentaUser: ArrayList<DataCuentaUser>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCobroQRBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(acces_Token: String) =
            CobroQRFragment().apply {
                arguments = Bundle().apply {
                    accessToken = acces_Token
                    println(accessToken)
                }
            }
    }

    fun fetchGenerarQR(){
        var monto = binding.txtMontoQR.text.toString().toIntOrNull()
        var fecha = binding.txtFechaLimiteQR.text.toString()
        println(idCuenta)
        QRRepository.generarImgQR(this,accessToken, monto, idCuenta, fecha)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySpinner = binding.spinnerCuentasQR


        val call = CuentasRepository.getRetrofit().create(CuentasInterface::class.java)
            .getCuentas("Bearer " + accessToken)
        call.enqueue(object : Callback<ArrayList<DataCuentaUser>> {
            override fun onResponse(
                call: Call<ArrayList<DataCuentaUser>>,
                response: Response<ArrayList<DataCuentaUser>>
            ) {
                listaCuentaUser = response.body()
                for (i in 0 until listaCuentaUser!!.size) {
                    codes.add(listaCuentaUser!!.get(i).numero)
                }
                val adapterTime = ArrayAdapter(
                    requireActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    codes
                )

                mySpinner.adapter = adapterTime
                mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        p2: Int,
                        p3: Long
                    ) {
                        idCuenta = listaCuentaUser!!.get(p2).id
//                        Toast.makeText(activity, listaCuentaUser!!.get(p2).id.toString(), Toast.LENGTH_SHORT).show()

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        //no hace nada
                    }

                }
            }

            override fun onFailure(call: Call<ArrayList<DataCuentaUser>>, t: Throwable) {
                Log.e("Error", "Error cargando las cuentas", t)
            }

        })

        binding.btnGeneraQR.setOnClickListener {
            fetchGenerarQR()
        }
    }

    override fun onCobroQRLoaded(dataRespuestaQR: DataRespuestaCobroQR?) {
        println(dataRespuestaQR)
        imagen = dataRespuestaQR!!.imagen

        replaceFragment(VisorImgQRFragment.newInstance(imagen))

    }

    override fun onErrorLoading(error: Throwable?, message: String) {
        Log.e("ERROR", message, error)
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        fragmentTransition.replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()
    }
}