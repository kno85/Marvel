package com.acano.marvel.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acano.marvel.R
import com.acano.marvel.domain.Hero
import com.acano.marvel.ui.CustomAdapter
import com.acano.marvel.ui.detail.DetailActivity
import com.acano.marvel.ui.viewActions
import org.koin.androidx.viewmodel.ext.android.viewModel


val ITEM_ID: String?="item_id"

class MainActivity : AppCompatActivity(),viewActions {
    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()

    }

    private fun setupView() {
        val rV = findViewById<RecyclerView>(R.id.rv)
        rV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainViewModel.heroList.observe(this, Observer<List<Hero>>() {
        rV.adapter = CustomAdapter(it, this)
        })
        mainViewModel.errorMessage.observe(this, Observer<String>() {
            Toast.makeText(this,it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onItemClick(itemId: Int) {
        val intent= Intent(this, DetailActivity::class.java)
        intent.putExtra(ITEM_ID, itemId)
        startActivity(intent)
    }
}