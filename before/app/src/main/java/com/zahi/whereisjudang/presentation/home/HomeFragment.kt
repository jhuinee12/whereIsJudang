package com.zahi.whereisjudang.presentation.home

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.zahi.whereisjudang.R
import com.zahi.whereisjudang.base.ViewState
import com.zahi.whereisjudang.base.fragment.BaseFragment
import com.zahi.whereisjudang.databinding.FragmentHomeBinding
import com.zahi.whereisjudang.extensions.viewBinding
import com.zahi.whereisjudang.presentation.MainActivity
import com.zahi.whereisjudang.viewmodel.HomeViewModel

class HomeFragment : BaseFragment(R.layout.fragment_home)  {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val activity: MainActivity by lazy { requireActivity() as MainActivity }
    private lateinit var callback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun setupUI() {
    }

    override fun setupDataBinding() {
        viewModel.run {
            state.observe(this@HomeFragment) {
                when (it) {
                    is ViewState.Success<*, *> -> processSuccessBlock(it)
                    is ViewState.Failure<*, *> -> processFailureBlock(it)
                }
            }
        }
    }

    override fun sendScreenLogEvent() { }

    private fun processSuccessBlock(state: ViewState.Success<*,*>) {
        when (state.state) {

        }
    }

    private fun processFailureBlock(state: ViewState.Failure<*,*>) {
        when(state.state) {
            else -> toast(state.result ?: resources.getString(R.string.error_unknown))
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

}