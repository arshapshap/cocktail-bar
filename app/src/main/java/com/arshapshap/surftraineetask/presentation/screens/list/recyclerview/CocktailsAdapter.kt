package com.arshapshap.surftraineetask.presentation.screens.list.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arshapshap.surftraineetask.databinding.ItemCocktailCardBinding
import com.arshapshap.surftraineetask.domain.models.Cocktail

class CocktailsAdapter(
    private var list: List<Cocktail> = listOf()
) : RecyclerView.Adapter<CocktailsAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ItemCocktailCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(cocktail: Cocktail) {

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Cocktail>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addElement(cocktail: Cocktail) {
        this.list = list.plus(cocktail)
        notifyItemInserted(this.list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            binding = getBinding(parent)
        )

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    private fun getBinding(parent: ViewGroup): ItemCocktailCardBinding =
        ItemCocktailCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
}