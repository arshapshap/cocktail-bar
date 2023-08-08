package com.arshapshap.surftraineetask.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.arshapshap.surftraineetask.R
import com.arshapshap.surftraineetask.common.extensions.showAlert
import com.arshapshap.surftraineetask.common.extensions.showToast

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val inflate: Inflate<VB>,
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribe()
    }

    abstract fun inject()

    abstract fun initViews()

    open fun subscribe() {
        viewModel.error.observe(viewLifecycleOwner) {
            when (it.level) {
                ViewModelErrorLevel.Error -> showAlert(
                    title = getString(R.string.error),
                    message = getString(it.messageRes)
                )

                ViewModelErrorLevel.Message -> showToast(
                    message = getString(it.messageRes)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}