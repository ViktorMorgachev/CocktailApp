package com.beeline.demo.cocktailapp.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.beeline.demo.cocktailapp.R
import com.bumptech.glide.Glide

class LoaderImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private var imageView: ImageView

    init {
        val rootView = View.inflate(context, R.layout.widget_image_view, this)
        imageView = rootView.findViewById(R.id.image_main)
    }

    fun loadImage(url: String) {
        Glide.with(context).load(url).placeholder(R.drawable.ic_launcher_foreground).fitCenter()
            .into(imageView)
    }
}