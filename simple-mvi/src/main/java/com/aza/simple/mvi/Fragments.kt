package com.aza.simple.mvi


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import kotlin.LazyThreadSafetyMode.NONE

abstract class BaseFragment<VS> : Fragment(), LifecycleDelegate.Callback<VS> {

  protected open val delegate: LifecycleDelegate<VS> by lazy(NONE) {
    LifecycleDelegate(this)
  }

  abstract fun layoutResId(): Int

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? = inflater.inflate(layoutResId(), container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewLifecycleOwner.lifecycle.addObserver(delegate)
  }
}

abstract class BaseDialogFragment<VS> : DialogFragment(), LifecycleDelegate.Callback<VS> {

  protected open val delegate: LifecycleDelegate<VS> by lazy(NONE) {
    LifecycleDelegate(this)
  }

  abstract fun layoutResId(): Int

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? = inflater.inflate(layoutResId(), container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewLifecycleOwner.lifecycle.addObserver(delegate)
  }
}