package com.acano.marvel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.acano.marvel.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(com.acano.marvel.R.layout.activity_main)
            val navHostFragment = supportFragmentManager.findFragmentById(com.acano.marvel.R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.findNavController()
        }

}