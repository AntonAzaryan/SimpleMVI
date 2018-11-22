package com.aza.simple.mvi


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import kotlin.LazyThreadSafetyMode.NONE

abstract class BaseFragment<VS> : Fragment(), UnidirectionalFlowBinder.Callback<VS> {

  override var createdFirstTime: Boolean = true

  protected open val binder: UnidirectionalFlowBinder<VS> by lazy(NONE) {
    UnidirectionalFlowBinder(this)
  }

  abstract fun layoutResId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    createdFirstTime = savedInstanceState == null
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? = inflater.inflate(layoutResId(), container, false)

  @CallSuper
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    viewLifecycleOwner.lifecycle.addObserver(binder)
  }
}

abstract class BaseDialogFragment<VS> : DialogFragment(), UnidirectionalFlowBinder.Callback<VS> {

  override var createdFirstTime: Boolean = true

  protected open val binder: UnidirectionalFlowBinder<VS> by lazy(NONE) {
    UnidirectionalFlowBinder(this)
  }

  abstract fun layoutResId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    createdFirstTime = savedInstanceState == null
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? = inflater.inflate(layoutResId(), container, false)

  @CallSuper
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    viewLifecycleOwner.lifecycle.addObserver(binder)
  }
}