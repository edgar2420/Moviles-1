package com.example.practicomvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.practicomvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(binding.root)
        setupViewModelObserver()
        setupEventListeners()
    }

    private fun setupEventListeners() {

    }

    private fun setupViewModelObserver() {
        viewModel.setupRecycler(this,binding.recycler)
    }

    private fun reload() {
        viewModel.setupRecycler(this,binding.recycler)
    }
}