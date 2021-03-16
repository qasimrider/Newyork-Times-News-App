package com.nytimes.newsapp.news.ui

import android.graphics.Color
import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.nytimes.newsapp.common.base.BaseFragment
import com.nytimes.newsapp.news.R
import com.nytimes.newsapp.news.BR

class NewsDetailFragment : BaseFragment() {
    //region Members and Props

    override var shouldBindData=true
    private val args:NewsDetailFragmentArgs by navArgs()
    //endregion
    //region Fragment Overrides
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        textView5.visibility=1
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = 400.toLong()
            scrimColor = Color.TRANSPARENT
//            setAllContainerColors(requireContext().themeColor(R.attr.colorPrimary))
        }
    }
    //endregion
    //region Base Overrides
    override fun layoutResourceId()= R.layout.fragment_news_detail
    override fun initialize(savedInstanceState: Bundle?) {
        args.also {
            setScreenTitle(it.news.title,getString(R.string.most_popular))
            binding?.apply {
                setVariable(BR.newsDetail,it.news)
            }
        }
    }


    //endregion
}

