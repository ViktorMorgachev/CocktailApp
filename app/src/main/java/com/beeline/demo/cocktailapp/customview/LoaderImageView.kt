package com.beeline.demo.cocktailapp.customview

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.beeline.demo.cocktailapp.R
import com.bumptech.glide.Glide

class LoaderImageView(context: Context) : ConstraintLayout(context) {
    private var imageView: ImageView
    init {
        val rootView =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.widget_image_view,
                this,
                true
            )
        imageView = rootView.findViewById(R.id.image_main)
    }

    fun loadImage(url: String) {
        Glide.with(context).load(url).placeholder(R.drawable.ic_launcher_foreground).fitCenter().into(imageView)
    }
}