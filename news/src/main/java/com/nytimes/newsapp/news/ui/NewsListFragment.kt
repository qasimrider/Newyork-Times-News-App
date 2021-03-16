package com.nytimes.newsapp.news.ui


import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialElevationScale
import com.nytimes.newsapp.business.news.usecase.GetMostViewedNews
import com.nytimes.newsapp.common.base.BaseFragment
import com.nytimes.newsapp.common.base.BaseViewModel
import com.nytimes.newsapp.common.base.GeneralAdapter
import com.nytimes.newsapp.common.extensions.configureVerticalList
import com.nytimes.newsapp.common.extensions.fault
import com.nytimes.newsapp.common.extensions.observe
import com.nytimes.newsapp.common.extensions.sharedGraphViewModel
import com.nytimes.newsapp.dtos.news.NewsView
import com.nytimes.newsapp.dtos.news.PictureView
import com.nytimes.newsapp.news.BR
import com.nytimes.newsapp.news.R
import com.nytimes.newsapp.news.databinding.FragmentNewsListBinding
import com.nytimes.newsapp.news.viewModel.NewsViewModel

class NewsListFragment() : BaseFragment() {

    //region Props
    override var shouldBindData: Boolean = true
    private lateinit var viewBinding: FragmentNewsListBinding
    private val adapter = GeneralAdapter(BR.news, R.layout.news_item, NewsView.DIFF_CALLBACK)
    //endregion

    //region Koin Injects
    private val newsViewModel by sharedGraphViewModel<NewsViewModel>(R.id.newsNavigation)

    //endregion

    //region Fragment Overrides
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsViewModel.fetchMostViewedNews(GetMostViewedNews.Params("all-sections", 7))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }
    //endregion

    //region Listeners & Observers
    override fun attachListeners() {
        super.attachListeners()
        adapter.clickListener = { news, view ->
            exitTransition = MaterialElevationScale(false).apply {
                duration = 400L
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = 400L
            }
            val newsItemDetailTransition = getString(R.string.news_item_detail_transition_name)
            val extras = FragmentNavigatorExtras(view to newsItemDetailTransition)
            findNavController().navigate(NewsListFragmentDirections.toDetail(news), extras)
        }
    }


    private fun attachObservers() {
        newsViewModel.run {

            observe(mostViewedNews)
            {
                //only commit the largest picture from the array , so as to smoothly show the transitions between this screen and the detail
                it.forEach {
                    val largestPicture = PictureUtil.findLargestImage(it.pictures)
                    it.largestPicture = largestPicture
                }
                adapter.submitList(it)
            }
            observe(operationStatus)
            {
                it?.let {
                    when (it) {
                        BaseViewModel.Operation.Started -> showProgress(true, true)
                        BaseViewModel.Operation.Completed -> showProgress(false, false)
                    }
                }
            }
            fault(errorEntity, ::handleFailure)
        }
    }
    //endregion

    //region Base Overrides
    override fun layoutResourceId() = R.layout.fragment_news_list


    object PictureUtil {
        fun findLargestImage(pictureViews: List<PictureView>): PictureView {
            return pictureViews.maxByOrNull { it.height + it.width } ?: PictureView.empty()
        }
    }

    override fun initialize(savedInstanceState: Bundle?) {
        viewBinding = binding as FragmentNewsListBinding
        progressBar = viewBinding.progressBar

        viewBinding.newsRv.configureVerticalList(adapter)
        setScreenTitle(getString(R.string.ny_times), getString(R.string.most_popular))
        attachObservers()
    }


//endregion
}




