package com.nytimes.newsapp.news.ui


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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

const val DAILY = 1
const val WEEKLY = 7
const val MONTHLY = 30

class NewsListFragment() : BaseFragment() {

    //region Props
    override var shouldBindData: Boolean = true
    private lateinit var viewBinding: FragmentNewsListBinding
    private val adapter = GeneralAdapter(BR.news, R.layout.news_item, NewsView.DIFF_CALLBACK)
    private var lastPeriod: Int = DAILY
    //endregion

    //region Koin Injects
    private val newsViewModel by sharedGraphViewModel<NewsViewModel>(R.id.newsNavigation)

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel.fetchMostViewedNews(GetMostViewedNews.Params("all-sections", DAILY))

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

    // region option menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                newsViewModel.fetchMostViewedNews(
                    GetMostViewedNews.Params(
                        "all-sections",
                        lastPeriod
                    )
                )
            }
            R.id.daily -> {
                if (lastPeriod != DAILY) {
                    lastPeriod = DAILY
                    newsViewModel.fetchMostViewedNews(
                        GetMostViewedNews.Params(
                            "all-sections",
                            DAILY
                        )
                    )
                } else {
                    showMessage(getString(R.string.already_updated))
                }


            }
            R.id.weekly -> {
                if (lastPeriod != WEEKLY) {
                    lastPeriod = WEEKLY
                    newsViewModel.fetchMostViewedNews(
                        GetMostViewedNews.Params(
                            "all-sections",
                            WEEKLY
                        )
                    )

                } else {
                    showMessage(getString(R.string.already_updated))
                }

            }
            R.id.monthly -> {
                if (lastPeriod != MONTHLY) {
                    lastPeriod = MONTHLY
                    newsViewModel.fetchMostViewedNews(
                        GetMostViewedNews.Params(
                            "all-sections",
                            MONTHLY
                        )
                    )
                } else {
                    showMessage(getString(R.string.already_updated))
                }


            }
        }

        return super.onOptionsItemSelected(item)
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





