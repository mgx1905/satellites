package com.mgx1905.satellites.base.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.listener.NavigationListener
import com.mgx1905.satellites.utils.EMPTY

/**
 * Created by mgx1905 on 26.12.2021
 */

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    val navigationListener by lazy(LazyThreadSafetyMode.NONE) { activity as NavigationListener }

    fun showAlert(
        title: String = EMPTY,
        message: String,
        buttonText: String = getString(R.string.ok),
        onCancel: (() -> Unit)? = null,
    ) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(buttonText) { dialog, _ ->
                onCancel?.invoke()
                dialog.dismiss()
            }.show()
    }
}