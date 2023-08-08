package com.arshapshap.surftraineetask.utils.extensions

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.arshapshap.surftraineetask.R

fun Fragment.showToast(
    message: String,
    longLength: Boolean = false
) {
    Toast.makeText(requireContext(), message, if (longLength) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Fragment.showAlert(
    title: String,
    message: String,
    onClick: () -> Unit = { }
) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(R.string.ok) { _, _ ->
            onClick.invoke()
        }
        .show()
}