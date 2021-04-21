package com.samadhan.movieapp.adapters

import android.graphics.Typeface
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * Object class to handle Binding utility
 */
object ApplicationBindingAdapter {

    @BindingAdapter("imageURL")
    @JvmStatic
    fun setEditTextCursorSelection(imageView: AppCompatImageView?, imageURL: String?) {
        if (imageView != null && !imageURL.isNullOrEmpty()) {
            // Load image from URL using Library
            Picasso.get().load(imageURL).into(imageView)
        }
    }

    @BindingAdapter("setFont")
    @JvmStatic
    fun setFont(textView: TextView?, fontType: String?) {
        if (textView != null && !fontType.isNullOrEmpty()) {
            if ("Bold" == fontType)
                // Set Bold Font
                textView.typeface = Typeface.createFromAsset(
                    textView.context.assets,
                    "FreeSansBold.ttf"
                )
            else
            // Set Regular Font
                textView.typeface = Typeface.createFromAsset(
                    textView.context.assets,
                    "FreeSans.ttf"
                )
        }
    }
/*
    @BindingAdapter("setFont")
    @JvmStatic
    fun setFont(textView: AppCompatEditText?, fontType: String?) {
        if (textView != null && !fontType.isNullOrEmpty()) {
            if ("Bold" == fontType)
                textView.typeface = Typeface.createFromAsset(
                    textView.context.assets,
                    "FreeSans.ttf"
                )
            else
                textView.typeface = Typeface.createFromAsset(
                    textView.context.assets,
                    "FreeSansBold.ttf"
                )
        }
    }

    @BindingAdapter("setFont")
    @JvmStatic
    fun setFont(textView: AppCompatButton?, fontType: String?) {
        if (textView != null && !fontType.isNullOrEmpty()) {
            if ("Bold" == fontType)
                textView.typeface = Typeface.createFromAsset(
                    textView.context.assets,
                    "FreeSans.ttf"
                )
            else
                textView.typeface = Typeface.createFromAsset(
                    textView.context.assets,
                    "FreeSansBold.ttf"
                )
        }
    }*/
}