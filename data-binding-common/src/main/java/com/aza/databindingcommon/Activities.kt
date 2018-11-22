package com.aza.databindingcommon

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aza.simple.mvi.BaseActivity

abstract class BaseBindingActivity<B : ViewDataBinding, VS> : BaseActivity<VS>() {

  protected lateinit var viewBinding: B
    private set

  override fun onSetContentView(savedInstanceState: Bundle?) {
    viewBinding = DataBindingUtil.setContentView(this, layoutResId())
  }

}