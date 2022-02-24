package com.acano.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
       val rV = findViewById<RecyclerView>(R.id.rv)
        rV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        rV.adapter = CustomAdapter(getMockedHeroList())
    }

    private fun getMockedHeroList(): List<Hero> {
       val list= ArrayList<Hero>()
        for(i in 1..10){
            list.add(
                Hero("Hero "+i,
                "bla bla bla,bla bla bla,bla bla bla,bla bla bla,bla bla bla,bla bla bla",
                    R.drawable.ic_hero))
        }
        return list
    }
}