package com.arshapshap.surftraineetask.presentation.screens.list.recyclerview

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.arshapshap.surftraineetask.R
import com.arshapshap.surftraineetask.databinding.ItemCocktailCardBinding
import com.arshapshap.surftraineetask.domain.models.Cocktail

class CocktailsAdapter(
    private var list: List<Cocktail> = listOf(),
    private val onItemClick: (Long) -> Unit
) : RecyclerView.Adapter<CocktailsAdapter.ViewHolder>() {

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
            binding = getBinding(parent),
            onClick = onItemClick
        )

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    private fun getBinding(parent: ViewGroup): ItemCocktailCardBinding =
        ItemCocktailCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    class ViewHolder(
        private val binding: ItemCocktailCardBinding,
        private val onClick: (Long) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(cocktail: Cocktail) {
            with (binding) {
                imageView.setImageResource(R.drawable.img_summer_holidays)
                if (cocktail.imageUri.isNotBlank())
                    imageView.load(Uri.parse(cocktail.imageUri))

                nameTextView.text = cocktail.name

                root.setOnClickListener {
                    onClick.invoke(cocktail.id)
                }
            }
        }
    }
}