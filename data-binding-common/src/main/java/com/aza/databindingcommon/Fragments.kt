package com.aza.databindingcommon


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aza.simple.mvi.BaseDialogFragment
import com.aza.simple.mvi.BaseFragment


abstract class BaseBindingFragment<B : ViewDataBinding, VS> : BaseFragment<VS>() {

  var viewBinding: B? = null
    private set

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? = DataBindingUtil.inflate<B>(inflater, layoutResId(), container, false).run {
    viewBinding = this
    root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    viewBinding = null
  }

}

abstract class BaseBindingDialogFragment<B : ViewDataBinding, VS> : BaseDialogFragment<VS>() {

  var viewBinding: B? = null
    private set

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? = DataBindingUtil.inflate<B>(inflater, layoutResId(), container, false).run {
    viewBinding = this
    root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    viewBinding = null
  }

}