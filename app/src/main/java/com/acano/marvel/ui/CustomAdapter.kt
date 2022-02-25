package com.acano.marvel.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.acano.marvel.ui.CustomAdapter.CustomViewHolder
import android.view.LayoutInflater
import com.acano.marvel.R
import com.acano.marvel.domain.Hero
import com.bumptech.glide.Glide


class CustomAdapter(val heroList:List<Hero>) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parentView: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parentView.context)
            .inflate(R.layout.hero_row, parentView, false)

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(heroList[position])
    }

    override fun getItemCount(): Int= heroList.size

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero: Hero) {
            val imageView = itemView.findViewById<ImageView>(R.id.image)
            itemView.findViewById<TextView>(R.id.title).text= hero.name
            itemView.findViewById<TextView>(R.id.description).text= hero.description
            Glide.with(itemView.context).load(hero.image).into(imageView)

        }

    }

}
