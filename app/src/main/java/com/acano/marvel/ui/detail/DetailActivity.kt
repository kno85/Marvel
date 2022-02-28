package com.acano.marvel.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.acano.marvel.R
import com.acano.marvel.domain.Hero
import com.acano.marvel.ui.main.ITEM_ID
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val itemId= intent.getIntExtra(ITEM_ID,0)
        detailViewModel.submitHero(itemId)
        setupView()
    }
    private fun setupView() {
        detailViewModel.hero.observe(this, Observer<Hero>() {
            findViewById<TextView>(R.id.detail_title).text=it.name
            findViewById<TextView>(R.id.detail_description).text=it.description
            val imageView = findViewById<ImageView>(R.id.detail_image)
            Glide.with(this).load(it.image).into(imageView)

        })
        detailViewModel.errorMessage.observe(this, Observer<String>() {
            Toast.makeText(this,it, Toast.LENGTH_LONG).show()
        })
    }

}