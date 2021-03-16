package com.nytimes.newsapp.common.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.nytimes.newsapp.common.R
import com.nytimes.newsapp.common.error.ErrorEntity
import com.nytimes.newsapp.common.error.ErrorEntity.*
import com.nytimes.newsapp.common.extensions.invisible
import com.nytimes.newsapp.common.extensions.visible


abstract class BaseFragment : Fragment(){

    //region Props
    protected var binding: ViewDataBinding? = null
    protected var progressBar: ProgressBar? = null
    //endregion

    //region Progress bar
    protected fun showProgress(showProgress: Boolean, lockScreen: Boolean) {
        if (showProgress) {
            progressBar?.visible()

        } else {
            progressBar?.invisible()

        }
        if (lockScreen) {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            activity?.window?.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    //endregion


    //region Toast Message
    private fun showMessage(
        messageBody: String,
        onClick: (String) -> Unit ={}
    ) {
        Toast.makeText(context,messageBody,Toast.LENGTH_LONG).show()
    }
    //endregion

    //region Error handling
    protected fun handleFailure(errorEntity: ErrorEntity?) {
        showProgress(false, lockScreen = false)
        when (errorEntity) {
            is AuthError -> showMessage(
                getString(R.string.failure_authorization)
            )
            is ErrorEntity.Forbidden -> showMessage(getString(R.string.failure_forbidden))
            is InternalServerError -> showMessage(
                getString(R.string.failure_forbidden)
            )
            is BadRequest -> showMessage(getString(R.string.failure_bad_request))
            is NotFound -> showMessage(getString(R.string.failure_not_found))
            is AndroidError -> showMessage(getString(R.string.failure_android_error))
            is UnSupportedMediaType -> showMessage(getString(R.string.failure_unsupportedmedia))
            is MalFormedJson -> showMessage(getString(R.string.failure_malformedJson))
            is IllegalStateException -> showMessage(getString(R.string.failure_malformedJson))
            is JsonSyntaxException -> showMessage(getString(R.string.failure_malformedJson))
            is UniqueConstraintError -> showMessage(getString(R.string.failure_unique_constraint))
            is ServerError -> showMessage(getString(R.string.failure_server_error))
            is NetworkConnection -> showMessage(getString(R.string.failure_network_connection))
        }
    }
    //endregion

    //region Abstracts
    protected open var shouldBindData = false
    protected abstract fun layoutResourceId(): Int
    protected open fun attachListeners() {

    }
    abstract fun  initialize (savedInstanceState:Bundle?)
    //endregion

    //region Fragment Title and Subtitle
    protected fun setScreenTitle(title: String, subtitle: String) {
        (activity as AppCompatActivity).supportActionBar?.title = title
        (activity as AppCompatActivity).supportActionBar?.subtitle = subtitle

    }
    //endregion

    //region LifeCycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (shouldBindData) {
            binding = DataBindingUtil.inflate(
                inflater, layoutResourceId(), container, false
            )
            Log.d("Binding", "OnCreateView");
            binding!!.lifecycleOwner = viewLifecycleOwner
            binding!!.root

        } else
            inflater.inflate(layoutResourceId(), container, false)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if (activity is AppCompatActivity) {
//            progressBar =
//                ((activity as AppCompatActivity).findViewById(R.id.toolbar) as Toolbar).findViewById(
//                    R.id.progressBar
//                )
//            showProgress(false, false)
//        }
        attachListeners()
        initialize(savedInstanceState)
    }


    //endregion
}