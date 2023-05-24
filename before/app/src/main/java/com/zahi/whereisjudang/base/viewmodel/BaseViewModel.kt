package com.zahi.whereisjudang.base.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import autodispose2.lifecycle.CorrespondingEventsFunction
import autodispose2.lifecycle.LifecycleEndedException
import autodispose2.lifecycle.LifecycleScopeProvider
import com.zahi.domain.base.Failure
import com.zahi.whereisjudang.R
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel(), LifecycleScopeProvider<ViewModelEvent> {

	private val lifecycleEventDelegate =
		lazy { BehaviorSubject.createDefault(ViewModelEvent.CREATED) }

	private val lifecycleEvent by lifecycleEventDelegate

	@Inject
	lateinit var resources: Resources

	private val _failure: Channel<Failure> = Channel(Channel.BUFFERED)
	val failure: Flow<Failure> = _failure.receiveAsFlow()

	override fun lifecycle(): Observable<ViewModelEvent> {
		return lifecycleEvent.hide()
	}

	override fun correspondingEvents(): CorrespondingEventsFunction<ViewModelEvent> {
		return CORRESPONDING_EVENTS
	}

	override fun peekLifecycle(): ViewModelEvent {
		return lifecycleEvent.value
	}

	override fun onCleared() {
		if (lifecycleEventDelegate.isInitialized()) {
			lifecycleEvent.onNext(ViewModelEvent.CLEARED)
		}
		super.onCleared()
	}

	fun handleFailure(throwable: Throwable, retryAction: () -> Unit) {
		val failure = when(throwable) {
			is Failure.NoInternet -> {
				Failure.NoInternet(resources.getString(R.string.error_no_internet))
			}
			is Failure.Api -> Failure.Api(throwable.msg)

			is Failure.Timeout -> Failure.Timeout(resources.getString(R.string.error_timeout))

			is Failure.Unknown -> Failure.Unknown(resources.getString(R.string.error_unknown))

			else -> Failure.Unknown(resources.getString(R.string.error_unknown))
		}

		failure.retryAction = retryAction
		viewModelScope.launch {
			_failure.send(failure)
		}
	}

	companion object {
		private val CORRESPONDING_EVENTS = CorrespondingEventsFunction<ViewModelEvent> { event ->
			when(event) {
				ViewModelEvent.CREATED -> ViewModelEvent.CLEARED
				else -> throw LifecycleEndedException(
					"Cannot bind to ViewModel lifecycle after onCleared."
				)
			}
		}
	}
}
