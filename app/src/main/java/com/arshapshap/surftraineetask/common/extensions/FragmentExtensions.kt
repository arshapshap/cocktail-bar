package com.arshapshap.surftraineetask.common.extensions

import android.content.Context
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.arshapshap.surftraineetask.R

@ColorInt
fun Context.getColorAttributeFromTheme(@AttrRes attr: Int): Int {
    val typedValue = TypedValue()
    theme?.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}

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

fun Fragment.showAlertWithTwoButtons(
    title: String,
    message: String? = null,
    positiveButtonText: String = getString(R.string.ok),
    negativeButtonText: String = getString(R.string.cancel),
    onPositiveButtonClick: () -> Unit = { },
    onNegativeButtonClick: () -> Unit = { }
) {
    var builder = AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setPositiveButton(positiveButtonText) { _, _ ->
            onPositiveButtonClick.invoke()
        }.setNegativeButton(negativeButtonText) { _, _ ->
            onNegativeButtonClick.invoke()
        }
    if (message != null)
        builder = builder.setMessage(message)
    builder.show()
}

fun Fragment.showAlertWithThreeButtons(
    title: String,
    message: String? = null,
    neutralButtonText: String,
    positiveButtonText: String = getString(R.string.ok),
    negativeButtonText: String = getString(R.string.cancel),
    onPositiveButtonClick: () -> Unit = { },
    onNeutralButtonClick: () -> Unit = { },
    onNegativeButtonClick: () -> Unit = { }
) {
    // The neutral and negative buttons had to be replaced in order to position the neutral button in the center
    var builder = AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setPositiveButton(positiveButtonText) { _, _ ->
            onPositiveButtonClick.invoke()
        }.setNegativeButton(neutralButtonText) { _, _ ->
            onNeutralButtonClick.invoke()
        }.setNeutralButton(negativeButtonText) { _, _ ->
            onNegativeButtonClick.invoke()
        }
    if (message != null)
        builder = builder.setMessage(message)
    builder.show()
}

fun Fragment.hideKeyboard() {
    val imm = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.showKeyboard(editText: EditText) {
    val imm = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
}