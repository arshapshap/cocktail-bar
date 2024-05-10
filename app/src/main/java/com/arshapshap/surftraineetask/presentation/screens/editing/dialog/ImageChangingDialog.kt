package com.arshapshap.surftraineetask.presentation.screens.editing.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.DialogFragment
import com.arshapshap.surftraineetask.R
import com.arshapshap.surftraineetask.databinding.DialogImageChangingBinding

class ImageChangingDialog(
    private val onChangeImage: () -> Unit,
    private val onRemoveImage: () -> Unit,
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogImageChangingBinding.inflate(layoutInflater)
        with (binding) {
            changeImageButton.setOnClickListener {
                onChangeImage.invoke()
                dismiss()
            }
            removeImageButton.setOnClickListener {
                onRemoveImage.invoke()
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