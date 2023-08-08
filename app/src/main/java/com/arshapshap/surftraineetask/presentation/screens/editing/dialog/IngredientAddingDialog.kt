package com.arshapshap.surftraineetask.presentation.screens.editing.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import com.arshapshap.surftraineetask.R
import com.arshapshap.surftraineetask.databinding.DialogIngredientAddingBinding

class IngredientAddingDialog(
    private val onAddIngredient: (String) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogIngredientAddingBinding.inflate(layoutInflater)
        with (binding) {
            titleEditText.editText?.doAfterTextChanged {
                titleEditText.error = null
            }
            addButton.setOnClickListener {
                if (titleEditText.editText == null || titleEditText.editText?.text?.isBlank() == true) {
                    titleEditText.error = getString(R.string.add_title)
                } else {
                    onAddIngredient.invoke(titleEditText.editText!!.text.toString())
                    dismiss()
                }
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