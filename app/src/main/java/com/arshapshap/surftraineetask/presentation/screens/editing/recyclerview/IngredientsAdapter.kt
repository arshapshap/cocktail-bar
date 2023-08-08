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
    private var _list: List<String> = listOf(),
    private val onAddButtonClick: () -> Unit,
    private val onDeleteIngredientClick: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<String>) {
        val result = _list.toMutableList()
        _list.forEachIndexed { i, it ->
            if (it !in list) {
                result.remove(it)
                notifyItemRemoved(i)
            }
        }
        list.forEach {
            if (it !in _list) {
                result.add(it)
                notifyItemInserted(this._list.size)
            }
        }
        this._list = result
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
        _list.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is IngredientViewHolder -> holder.onBind(_list[position])
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
        private val onDeleteClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(ingredient: String) {
            with (binding) {
                ingredientTextView.text = ingredient

                root.setOnClickListener {
                    onDeleteClick.invoke(ingredient)
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