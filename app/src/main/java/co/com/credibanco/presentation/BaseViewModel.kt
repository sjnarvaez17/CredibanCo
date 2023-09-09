package co.com.credibanco.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.credibanco.util.LOG_MESSAGE_EVENT
import co.com.credibanco.util.TAG_VIEW_UPDATE
import co.com.credibanco.util.launchSafe
import kotlinx.coroutines.CoroutineDispatcher

abstract class BaseViewModel<State : ViewState, Event : ViewEvent>(
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    val state: MutableLiveData<State> = MutableLiveData()
    val toastMessage: MutableLiveData<String> = MutableLiveData()

    init {
        setInitialState()
    }

    protected abstract fun getInitialState(): State

    private fun setInitialState() {
        state.value = getInitialState()
    }

    fun getCurrentState() = state.value ?: getInitialState()

    fun dispatchEvent(event: Event) {
        viewModelScope.launchSafe(coroutineDispatcher) {
            Log.i(TAG_VIEW_UPDATE, "$LOG_MESSAGE_EVENT${event.getName()}")
            processEvent(event)
        }
    }

    protected abstract suspend fun processEvent(event: Event)

    protected fun setState(state: State) {
        this.state.postValue(state)
    }
}
