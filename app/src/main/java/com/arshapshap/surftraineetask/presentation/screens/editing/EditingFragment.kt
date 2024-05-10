package com.arshapshap.surftraineetask.presentation.screens.editing

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.widget.doAfterTextChanged
import com.arshapshap.surftraineetask.R
import com.arshapshap.surftraineetask.databinding.FragmentEditingBinding
import com.arshapshap.surftraineetask.presentation.screens.editing.dialog.ImageChangingDialog
import com.arshapshap.surftraineetask.presentation.screens.editing.dialog.IngredientAddingDialog
import com.arshapshap.surftraineetask.presentation.screens.editing.recyclerview.IngredientsAdapter
import com.arshapshap.surftraineetask.utils.base.BaseFragment
import com.arshapshap.surftraineetask.utils.di.appComponent
import com.arshapshap.surftraineetask.utils.di.lazyViewModel

class EditingFragment : BaseFragment<FragmentEditingBinding, EditingScreenViewModel>(
    FragmentEditingBinding::inflate
) {

    companion object {

        fun createBundle(cocktailId: Long): Bundle {
            return bundleOf(COCKTAIL_ID_KEY to cocktailId)
        }

        private const val COCKTAIL_ID_KEY = "COCKTAIL_ID_KEY"
    }

    override val viewModel: EditingScreenViewModel by lazyViewModel {
        requireContext().appComponent().editingScreenViewModel().create(
            arguments?.getLong(COCKTAIL_ID_KEY)
        )
    }

    override fun inject() {
        requireContext().appComponent().inject(this)
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri ->
        if (uri != null)
            viewModel.changeImageURI(uri.toString())
    }

    override fun initViews() {
        with (binding) {
            ingredientsRecyclerView.adapter = IngredientsAdapter(
                onAddButtonClick = ::showIngredientAddingDialog,
                onDeleteIngredientClick = viewModel::deleteIngredient
            )
            loadedImageView.setOnClickListener {
                if (viewModel.editingCocktail.value?.imageUri == "")
                    getImageFromGallery()
                else
                    showImageChangingDialog()
            }
            titleEditText.editText?.doAfterTextChanged {
                binding.titleEditText.error = null
                viewModel.changeName(it?.toString() ?: "")
            }
            descriptionEditText.editText?.doAfterTextChanged {
                viewModel.changeDescription(it?.toString() ?: "")
            }
            recipeEditText.editText?.doAfterTextChanged {
                viewModel.changeRecipe(it?.toString() ?: "")
            }
            saveButton.setOnClickListener {
                viewModel.save()
            }
            cancelButton.setOnClickListener {
                viewModel.closeFragment()
            }
        }
    }

    override fun subscribe() {
        with (viewModel) {
            inputErrors.observe(viewLifecycleOwner) { list ->
                list.forEach {
                    when (it) {
                        CocktailInputError.EmptyTitle -> binding.titleEditText.error = getString(R.string.add_title)
                        CocktailInputError.NoIngredients -> binding.noIngredientsError.isGone = false
                    }
                }
            }
            startCocktailValues.observe(viewLifecycleOwner) {
                with (binding) {
                    titleEditText.editText?.setText(it.name)
                    descriptionEditText.editText?.setText(it.description)
                    recipeEditText.editText?.setText(it.recipe)

                    getIngredientsAdapter().setList(it.ingredients)
                }
            }
            editingCocktail.observe(viewLifecycleOwner) { cocktail ->
                getIngredientsAdapter().setList(cocktail.ingredients)
                changeImage(cocktail.imageUri)
            }
        }
    }

    private fun changeImage(imageUri: String) = with (binding) {
        if (imageUri != "") {
            loadedImageView.setImageURI(Uri.parse(imageUri))
            loadedImageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            loadedImageView.setImageDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_photo, requireContext().theme)
            )
            loadedImageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
    }

    private fun showImageChangingDialog() {
        binding.root.clearFocus()
        val dialogFragment = ImageChangingDialog(
            onChangeImage = { getImageFromGallery() },
            onRemoveImage = { viewModel.changeImageURI(null) }
        )
        dialogFragment.show(childFragmentManager, "CHANGE_IMAGE_DIALOG")
    }

    private fun getImageFromGallery() {
        getContent.launch("image/*")
    }

    private fun showIngredientAddingDialog() {
        binding.noIngredientsError.isGone = true
        binding.root.clearFocus()
        val dialogFragment = IngredientAddingDialog {
            viewModel.addIngredient(it)
        }
        dialogFragment.show(childFragmentManager, "ADD_INGREDIENT_DIALOG")
    }

    private fun getIngredientsAdapter(): IngredientsAdapter
            = binding.ingredientsRecyclerView.adapter as IngredientsAdapter
}