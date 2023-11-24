package com.acano.marvel.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.acano.marvel.R
import com.acano.marvel.databinding.FragmentMainBinding
import com.acano.marvel.ui.CustomAdapter
import com.acano.marvel.ui.viewActions
import org.koin.androidx.viewmodel.ext.android.viewModel

val ITEM_ID: String="item_id"


class MainFragment : Fragment(), viewActions {
    private val mainViewModel by viewModel<MainViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {

            _binding?.rv?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mainViewModel.heroList.observe(viewLifecycleOwner) {
            val heroList = it
            _binding?.rv?.adapter = CustomAdapter(heroList, this)
        }
        mainViewModel.errorMessage.observe(viewLifecycleOwner) {
            val errorMessage = it
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun onItemClick(itemId: Int) {
        val bundle = bundleOf(ITEM_ID to itemId.toString())

        _binding?.let {
            Navigation.findNavController(it.root).navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


