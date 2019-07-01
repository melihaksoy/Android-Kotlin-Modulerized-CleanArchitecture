package com.melih.core.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * Loads image in given [url] to this [ImageView]
 *
 * @param url url of image
 */
@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrBlank()) {
        Picasso.get()
            .load(url)
            .into(this)
    }
}
