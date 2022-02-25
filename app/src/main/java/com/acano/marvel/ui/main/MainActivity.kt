package com.acano.marvel.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acano.marvel.R
import com.acano.marvel.domain.Hero
import com.acano.marvel.ui.CustomAdapter
import com.acano.marvel.util.getMockedHeroList

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupView()

    }

    private fun setupView() {
        val rV = findViewById<RecyclerView>(R.id.rv)
        rV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainViewModel.heroList.observe(this, Observer<List<Hero>>() {
        rV.adapter = CustomAdapter(it)
        })

    }
}