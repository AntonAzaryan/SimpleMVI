package com.aza.simple.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer


interface Presenter<VS> {
  val eventsConsumer: Consumer<Any>
  val stateProducer: LiveData<VS>
}

typealias EventHandler = (Observable<Any>) -> Observable<Any>
typealias StateReducer<VS> = (VS, Any) -> VS

abstract class BasePresenter<VS>(
  initState: () -> VS,
  eventsHandler: EventHandler,
  stateReducer: StateReducer<VS>
) : ViewModel(), Presenter<VS> {

  protected val disposables: CompositeDisposable = CompositeDisposable()

  protected val eventRelay: Relay<Any> = PublishRelay.create<Any>().toSerialized()
  protected val stateLiveData: MutableLiveData<VS> = MutableLiveData<VS>().apply { value = initState() }
  protected val eventProcessingStream: Observable<Any> = eventsHandler(eventRelay).share()

  override val eventsConsumer: Consumer<Any> = eventRelay
  override val stateProducer: LiveData<VS> = stateLiveData

  init {
    disposables.addAll(
      eventProcessingStream.subscribe(eventRelay), // for side effects
      eventProcessingStream
        .scan(stateLiveData.value, BiFunction(stateReducer))
        .distinctUntilChanged()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { stateLiveData.value = it }
    )
  }

  override fun onCleared() {
    disposables.dispose()
  }

}
