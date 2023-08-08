package com.arshapshap.surftraineetask.presentation.screens.list

import androidx.core.view.isGone
import com.arshapshap.surftraineetask.common.base.BaseFragment
import com.arshapshap.surftraineetask.common.di.appComponent
import com.arshapshap.surftraineetask.common.di.lazyViewModel
import com.arshapshap.surftraineetask.databinding.FragmentListBinding
import com.arshapshap.surftraineetask.presentation.screens.list.recyclerview.CocktailsAdapter

class ListFragment : BaseFragment<FragmentListBinding, ListScreenViewModel>(
    FragmentListBinding::inflate
) {

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
            }

            loadCocktails()
        }
    }

    private fun getCocktailsAdapter(): CocktailsAdapter
        = binding.cocktailsRecyclerView.adapter as CocktailsAdapter
}