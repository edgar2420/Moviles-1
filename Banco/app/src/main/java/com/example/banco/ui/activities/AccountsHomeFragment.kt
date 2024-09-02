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
import com.example.appmovilof.api.repositories.CuentasRepository
import com.example.appmovilof.databinding.FragmentAccountsHomeBinding
import com.example.appmovilof.models.DataCuentaUser
import com.example.appmovilof.ui.adapters.CuentasAdapter
import com.example.appmovilof.ui.interfaces.CuentasLoaded


class AccountsHomeFragment : Fragment(), CuentasAdapter.onCuentaClickListener, CuentasLoaded {

    private lateinit var binding: FragmentAccountsHomeBinding
    private lateinit var adapter: CuentasAdapter
    private var accessToken: String = ""
    //private var listaCuentasUser: ArrayList<DataCuentaUser>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setupRecyclerView()
        fetchCuentaUser()
        //adapter.notifyDataSetChanged()

    }

    fun fetchCuentaUser() {
        CuentasRepository.getCuentasUser(this, accessToken)
    }

    fun fetchAddCuentaUser() {
        CuentasRepository.addCuentaUser(this, accessToken)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(access_token: String) =
            AccountsHomeFragment().apply {
                arguments = Bundle().apply {
                    accessToken = access_token
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.btnAddCuenta.setOnClickListener {

            fetchAddCuentaUser()
            fetchCuentaUser()


        }
    }

    fun setupRecyclerView() {
        binding.lstCuentas.setHasFixedSize(true)
        adapter = CuentasAdapter(arrayListOf(), this)
        val linearLayoutV = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.lstCuentas.layoutManager = linearLayoutV
        val dividerItemDecoration = DividerItemDecoration(
            binding.lstCuentas.context,
            linearLayoutV.orientation
        )
        binding.lstCuentas.addItemDecoration(dividerItemDecoration)
        //adapter!!.setData(listaCuentasUser)
        binding.lstCuentas.adapter = adapter
    }

    override fun onClicked(dataCuentaUser: DataCuentaUser?) {
        var fragment: Fragment
        fragment = ExtractosFragment.newInstance(dataCuentaUser?.id, accessToken)

        replaceFragment(fragment, false)
    }

    override fun onLoginLoaded(cuentaUser: ArrayList<DataCuentaUser>?) {
        println(accessToken)
        //listaCuentasUser = cuentaUser
        adapter.setData(cuentaUser)
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