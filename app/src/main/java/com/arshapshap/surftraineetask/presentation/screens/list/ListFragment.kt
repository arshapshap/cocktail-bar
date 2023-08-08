package com.arshapshap.surftraineetask.presentation.screens.list

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import com.arshapshap.surftraineetask.utils.base.BaseFragment
import com.arshapshap.surftraineetask.utils.di.appComponent
import com.arshapshap.surftraineetask.utils.di.lazyViewModel
import com.arshapshap.surftraineetask.databinding.FragmentListBinding
import com.arshapshap.surftraineetask.presentation.screens.list.recyclerview.CocktailsAdapter

class ListFragment : BaseFragment<FragmentListBinding, ListScreenViewModel>(
    FragmentListBinding::inflate
) {

    companion object {

        fun createBundle(cocktailToScrollId: Long): Bundle {
            return bundleOf(COCKTAIL_TO_SCROLL_ID_KEY to cocktailToScrollId)
        }

        private const val COCKTAIL_TO_SCROLL_ID_KEY = "COCKTAIL_TO_SCROLL_ID_KEY"
    }

    private var firstOpening = true

    override val viewModel: ListScreenViewModel by lazyViewModel {
        requireContext().appComponent().listScreenViewModel().create()
    }

    override fun inject() {
        requireContext().appComponent().inject(this)
    }

    override fun initViews() {
        with (binding) {
            cocktailsRecyclerView.adapter = CocktailsAdapter() {
                viewModel.openCocktailDetails(it)
            }
            addButton.setOnClickListener {
                viewModel.createCocktail()
            }
        }
    }

    override fun subscribe() {
        with (viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                binding.loadingProgressBar.isGone = !it
                if (it) {
                    binding.noCocktailsGroup.isGone = true
                    binding.cocktailsRecyclerView.isGone = true
                }
            }

            cocktails.observe(viewLifecycleOwner) {
                binding.noCocktailsGroup.isGone = it.isNotEmpty()
                binding.cocktailsRecyclerView.isGone = it.isEmpty()

                if (it.isNotEmpty()) {
                    getCocktailsAdapter().setList(it)
                }
                if (firstOpening) {
                    binding.cocktailsRecyclerView.scrollToPosition(it.indexOfFirst { it.id == arguments?.getLong(COCKTAIL_TO_SCROLL_ID_KEY) })
                    firstOpening = false
                }
            }

            loadCocktails()
        }
    }

    private fun getCocktailsAdapter(): CocktailsAdapter
        = binding.cocktailsRecyclerView.adapter as CocktailsAdapter
}