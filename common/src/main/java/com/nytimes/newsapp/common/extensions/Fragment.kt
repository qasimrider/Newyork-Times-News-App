package com.nytimes.newsapp.common.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.nytimes.newsapp.common.error.ErrorEntity
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

fun <L : LiveData<ErrorEntity>> Fragment.fault(liveData: L, body: (ErrorEntity?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

inline fun <reified VM : ViewModel> Fragment.sharedGraphViewModel(
    @IdRes navGraphId: Int,
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy {
    val store = findNavController().getViewModelStoreOwner(navGraphId)
    getViewModel<VM>(qualifier, { store }, null, parameters)
}
