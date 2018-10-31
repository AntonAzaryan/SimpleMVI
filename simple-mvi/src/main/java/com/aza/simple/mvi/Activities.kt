package com.aza.simple.mvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.LazyThreadSafetyMode.NONE

abstract class BaseActivity<VS> : AppCompatActivity(), LifecycleDelegate.Callback<VS> {

  protected open val delegate: LifecycleDelegate<VS> by lazy(NONE) {
    LifecycleDelegate(this)
  }

  abstract fun layoutResId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    onSetContentView()
    lifecycle.addObserver(delegate)
  }

  protected open fun onSetContentView() {
    setContentView(layoutResId())
  }

}