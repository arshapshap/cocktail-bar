package com.arshapshap.surftraineetask.presentation.screens.editing.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arshapshap.surftraineetask.databinding.ItemAddIngredientBinding
import com.arshapshap.surftraineetask.databinding.ItemIngredientBinding

private const val VIEW_TYPE_INGREDIENT = 0
private const val VIEW_TYPE_ADD_BUTTON = 1
class IngredientsAdapter(
    private var list: List<String> = listOf(),
    private val onAddButtonClick: () -> Unit,
    private val onDeleteIngredientClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addElement(ingredient: String) {
        this.list = list.plus(ingredient)
        notifyItemInserted(this.list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ADD_BUTTON -> AddButtonViewHolder(
                binding = getAddButtonBinding(parent),
                onClick = onAddButtonClick
            )
            else -> IngredientViewHolder(
                binding = getIngredientBinding(parent),
                onDeleteClick = onDeleteIngredientClick
            )
        }
    }

    override fun getItemCount(): Int =
        list.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is IngredientViewHolder -> holder.onBind(list[position], position)
            else -> { }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            VIEW_TYPE_ADD_BUTTON
        } else {
            VIEW_TYPE_INGREDIENT
        }
    }

    private fun getIngredientBinding(parent: ViewGroup): ItemIngredientBinding =
        ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    private fun getAddButtonBinding(parent: ViewGroup): ItemAddIngredientBinding =
        ItemAddIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    class IngredientViewHolder(
        private val binding: ItemIngredientBinding,
        private val onDeleteClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(ingredient: String, id: Int) {
            with (binding) {
                ingredientTextView.text = ingredient

                deleteImageView.setOnClickListener {
                    onDeleteClick.invoke(id)
                }
            }
        }
    }

    class AddButtonViewHolder(
        binding: ItemAddIngredientBinding,
        private val onClick: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick.invoke()
            }
        }
    }
}