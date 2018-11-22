package com.aza.simple.mvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.LazyThreadSafetyMode.NONE

abstract class BaseActivity<VS> : AppCompatActivity(), UnidirectionalFlowBinder.Callback<VS> {

  override var createdFirstTime: Boolean = true

  protected open val binder: UnidirectionalFlowBinder<VS> by lazy(NONE) {
    UnidirectionalFlowBinder(this)
  }

  abstract fun layoutResId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    createdFirstTime = savedInstanceState == null
    onSetContentView(savedInstanceState)
    lifecycle.addObserver(binder)
  }

  protected open fun onSetContentView(savedInstanceState: Bundle?) {
    setContentView(layoutResId())
  }

}