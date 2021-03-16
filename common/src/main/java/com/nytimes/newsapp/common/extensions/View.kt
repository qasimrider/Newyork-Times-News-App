package com.nytimes.newsapp.common.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

//ahmed
fun <T : ViewDataBinding> ViewGroup.dataBind(@LayoutRes layoutRes: Int) =
    DataBindingUtil.inflate<T>(LayoutInflater.from(context), layoutRes, this, false)!!

fun <T : ViewDataBinding> ViewGroup.dataBind(@LayoutRes layoutRes: Int, viewType: Int) =
    DataBindingUtil.inflate<T>(LayoutInflater.from(context), layoutRes, this, false)!!

fun ImageView.loadImageFromUrl(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}




