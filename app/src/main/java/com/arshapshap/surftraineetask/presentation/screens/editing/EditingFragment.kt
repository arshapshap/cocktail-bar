package com.arshapshap.surftraineetask.presentation.screens.editing

import android.os.Bundle
import androidx.core.os.bundleOf
import com.arshapshap.surftraineetask.common.base.BaseFragment
import com.arshapshap.surftraineetask.common.di.appComponent
import com.arshapshap.surftraineetask.common.di.lazyViewModel
import com.arshapshap.surftraineetask.databinding.FragmentEditingBinding

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

    override fun initViews() {

    }

    override fun subscribe() {

    }
}