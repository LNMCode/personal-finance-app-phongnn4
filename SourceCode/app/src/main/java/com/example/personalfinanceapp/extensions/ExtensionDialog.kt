package com.example.personalfinanceapp.extensions

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable

fun Context.alert(
    dialogBuilder: AlertDialog.Builder.() -> Unit,
    isHaveEditText: Boolean,
) {
    val edittext = EditText(this)
    AlertDialog.Builder(this).apply {
        setCancelable(false)
        if (isHaveEditText) {
            setView(edittext)
        }
        dialogBuilder()
        create()
        show()
    }
}

fun AlertDialog.Builder.negativeButton(
    text: String = "No",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNegativeButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun AlertDialog.Builder.positiveButton(
    text: String = "Yes",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setPositiveButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun AlertDialog.Builder.neutralButton(
    text: String = "OK",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNeutralButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}
