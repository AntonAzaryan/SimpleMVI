package com.aza.simple.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer


interface Presenter<VS> {
  val eventsConsumer: Consumer<Any>
  val stateProducer: LiveData<VS>
}

abstract class BasePresenter<VS>(
  initState: () -> VS,
  eventsHandler: Observable<Any>.() -> Observable<Any>,
  stateReducer: BiFunction<VS, Any, VS>
) : ViewModel(), Presenter<VS> {

  private val disposables: CompositeDisposable = CompositeDisposable()

  private val eventRelay: Relay<Any> = PublishRelay.create<Any>().toSerialized()
  private val stateLiveData: MutableLiveData<VS> = MutableLiveData<VS>().apply { value = initState() }

  override val eventsConsumer: Consumer<Any> = eventRelay
  override val stateProducer: LiveData<VS> = stateLiveData

  init {
    val intentStream = eventRelay.eventsHandler().share()
    disposables.addAll(
      intentStream.subscribe(eventRelay), // for side effects
      intentStream
        .scan(stateLiveData.value, stateReducer)
        .distinctUntilChanged()
        .subscribe { stateLiveData.postValue(it) }
    )
  }

  override fun onCleared() {
    disposables.dispose()
  }

}
