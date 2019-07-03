package com.melih.core.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

/**
 * Simple behaviour for pushing views when snackbar is animating so none of views will remain under snackbar
 */
class SnackbarBehaviour constructor(
    context: Context,
    attributeSet: AttributeSet
) : CoordinatorLayout.Behavior<View>() {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean =
        dependency is Snackbar.SnackbarLayout

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val translationY = Math.min(0.0f, (dependency.translationY - dependency.height))
        child.translationY = translationY
        return true
    }
}
