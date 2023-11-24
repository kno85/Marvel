package com.acano.marvel.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.acano.marvel.R
import com.acano.marvel.databinding.FragmentDetailBinding
import com.acano.marvel.ui.main.ITEM_ID
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {
    private lateinit var mview: View
    val detailViewModel: DetailViewModel by viewModel()

    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mview = inflater.inflate(R.layout.fragment_detail, container, false)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val itemId= arguments?.getString(ITEM_ID)
        detailViewModel.submitHero(Integer.parseInt(itemId!!))
        setupView()
        return binding.root
    }
    private fun setupView() {
        detailViewModel.hero.observe(viewLifecycleOwner) {
            val hero = it
            _binding?.detailTitle?.text = hero.name
            _binding?.detailDescription?.text = hero.description


            _binding?.detailImage?.let { imageView -> Glide.with(this).load(hero.image).into(imageView) }

        }
        detailViewModel.errorMessage.observe(viewLifecycleOwner) {
            val errorMessage = it
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


