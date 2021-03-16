package com.nytimes.newsapp.common.extensions
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nytimes.newsapp.common.R


//endregion
@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context.applicationContext).load(url).placeholder(R.drawable.ic_gallery).into(this)
}

