package com.aza.databindingcommon

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aza.simple.mvi.BaseActivity

abstract class BaseBindingActivity<B : ViewDataBinding, VS> : BaseActivity<VS>() {

  protected lateinit var viewBinding: B
    private set

  override fun onSetContentView() {
    viewBinding = DataBindingUtil.setContentView(this, layoutResId())
  }

}