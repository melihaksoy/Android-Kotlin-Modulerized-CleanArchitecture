package com.melih.core.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar

class SnackbarBehaviour constructor(
    context: Context,
    attributeSet: AttributeSet
) : CoordinatorLayout.Behavior<SwipeRefreshLayout>() {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: SwipeRefreshLayout, dependency: View): Boolean =
        dependency is Snackbar.SnackbarLayout

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: SwipeRefreshLayout, dependency: View): Boolean {
        val translationY = Math.min(0.0f, (dependency.translationY - dependency.height))
        child.translationY = translationY
        return true
    }
}
