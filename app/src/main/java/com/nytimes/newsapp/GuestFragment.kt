package com.nytimes.newsapp

import com.nytimes.newsapp.common.base.BaseFragment
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.nytimes.newsapp.databinding.GuestFragmentBinding

class GuestFragment : BaseFragment() {


    //region Props
    lateinit var viewBinding : GuestFragmentBinding
    override var shouldBindData: Boolean = true
//        get() = true
//        set(value) {}
    //endregion


    override fun layoutResourceId()=R.layout.guest_fragment

    override fun initialize(savedInstanceState: Bundle?) {

        viewBinding = binding as GuestFragmentBinding


        viewBinding.button.setOnClickListener{
            findNavController().navigate(GuestFragmentDirections.toNews())
        }
    }
}