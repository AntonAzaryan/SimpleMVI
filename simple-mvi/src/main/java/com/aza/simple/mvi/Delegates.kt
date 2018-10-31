package com.aza.simple.mvi

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

open class LifecycleDelegate<VS>(
  protected val callback: Callback<VS>
) : DefaultLifecycleObserver {

  protected open var disposable: Disposable? = null

  lateinit var presenter: Presenter<VS>
    protected set

  override fun onCreate(owner: LifecycleOwner) {
    if (!::presenter.isInitialized)
      presenter = callback.onCreatePresenter()

    presenter.stateProducer.observe(owner, Observer<VS>(callback::onRenderState))
    disposable = callback.onCreateEventStream()
      .subscribe(presenter.eventsConsumer)
  }

  override fun onDestroy(owner: LifecycleOwner) {
    disposable?.dispose()
    disposable = null
  }

  interface Callback<VS> {
    fun onCreatePresenter(): Presenter<VS>
    fun onCreateEventStream(): Observable<Any>
    fun onRenderState(state: VS)
  }
}