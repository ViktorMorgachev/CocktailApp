package com.beeline.demo.cocktailapp.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.beeline.demo.cocktailapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import gone
import visible

class LoaderImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private var imageView: ImageView
    private var progressBar: ProgressBar

    init {
        val rootView = View.inflate(context, R.layout.widget_image_view, this)
        imageView = rootView.findViewById(R.id.image_main)
        progressBar = rootView.findViewById(R.id.progress)
    }

    fun loadImage(url: String) {
        progressBar.visible()
        Glide.with(context).load(url).listener(object : RequestListener<Drawable?>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>?,
                isFirstResource: Boolean
            ): Boolean {
               progressBar.gone()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable?>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.gone()
                return false
            }

        }).fitCenter().into(imageView)
    }
}