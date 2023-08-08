package com.arshapshap.surftraineetask.presentation.screens.list

import androidx.core.view.isGone
import com.arshapshap.surftraineetask.common.base.BaseFragment
import com.arshapshap.surftraineetask.common.di.lazyViewModel
import com.arshapshap.surftraineetask.databinding.FragmentListBinding
import com.arshapshap.surftraineetask.presentation.screens.list.recyclerview.CocktailsAdapter

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>(
    FragmentListBinding::inflate
) {

    override val viewModel: ListViewModel by lazyViewModel {
        ListViewModel()
    }

    override fun inject() {
        // TODO("Добавить DI")
    }

    override fun initViews() {
        with (binding) {
            cocktailsRecyclerView.adapter = CocktailsAdapter()
        }
    }

    override fun subscribe() {
        super.subscribe()
        with (viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                binding.loadingProgressBar.isGone = !it
            }

            cocktails.observe(viewLifecycleOwner) {
                binding.noCocktailsGroup.isGone = it.isNotEmpty()
                binding.cocktailsRecyclerView.isGone = it.isEmpty()

                if (it.isNotEmpty()) {
                    getCocktailsAdapter().setList(it)
                }
            }
        }
    }

    private fun getCocktailsAdapter(): CocktailsAdapter
        = binding.cocktailsRecyclerView.adapter as CocktailsAdapter
}