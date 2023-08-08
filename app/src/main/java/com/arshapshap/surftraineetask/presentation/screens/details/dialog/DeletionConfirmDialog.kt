package com.arshapshap.surftraineetask.presentation.screens.details.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.DialogFragment
import com.arshapshap.surftraineetask.R
import com.arshapshap.surftraineetask.databinding.DialogCocktailDeletionBinding

class DeletionConfirmDialog(
    private val onDelete: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogCocktailDeletionBinding.inflate(layoutInflater)
        with (binding) {
            deleteButton.setOnClickListener {
                onDelete.invoke()
            }
            cancelButton.setOnClickListener {
                dismiss()
            }
        }

        val builder = AlertDialog.Builder(requireContext())
            .setView(binding.root)

        val alertDialog = builder.create()
        val drawable: Drawable? = AppCompatResources.getDrawable(requireContext(), R.drawable.shape_radius54)
        alertDialog.window?.setBackgroundDrawable(drawable)

        return alertDialog
    }
}