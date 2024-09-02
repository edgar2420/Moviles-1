package com.example.appmovilof.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovilof.R
import com.example.appmovilof.api.repositories.ExtratoRepository
import com.example.appmovilof.databinding.FragmentExtractosBinding
import com.example.appmovilof.models.DataExtracto
import com.example.appmovilof.ui.adapters.CuentasAdapter
import com.example.appmovilof.ui.adapters.ExtractosAdapter
import com.example.appmovilof.ui.interfaces.ExtractosLoaded
import com.example.appmovilof.ui.interfaces.IOnBackPressed


class ExtractosFragment : Fragment(), ExtractosLoaded{

    private lateinit var binding: FragmentExtractosBinding
    private lateinit var adapter: ExtractosAdapter
    private var idCuenta: Int = 0
    private var accessToken: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchExtractosCuenta()

    }

    fun fetchExtractosCuenta(){
        ExtratoRepository.getExtractoCuentaUser(this, idCuenta,accessToken)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExtractosBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(id_Cuenta: Int?, access_token: String) =
            ExtractosFragment().apply {
                arguments = Bundle().apply {

                    idCuenta = id_Cuenta!!
                    accessToken = access_token

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExtractosAdapter(arrayListOf())
        val linearLayoutV = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.lstExtractos.layoutManager = linearLayoutV
        val dividerItemDecoration = DividerItemDecoration(
            binding.lstExtractos.context,
            linearLayoutV.orientation
        )
        binding.lstExtractos.addItemDecoration(dividerItemDecoration)
        binding.lstExtractos.adapter = adapter
    }


    


    override fun onExtractosLoaded(extractosCuenta: ArrayList<DataExtracto>?) {
        adapter.setData(extractosCuenta)
    }

    override fun onErrorLoading(error: Throwable?, message: String) {
        Log.e("ERROR", message, error)
    }



    fun replaceFragment(fragment: Fragment, istransition: Boolean) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        fragmentTransition.replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()
    }
}