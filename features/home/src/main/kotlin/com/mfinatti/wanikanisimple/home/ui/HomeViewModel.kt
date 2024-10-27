package com.mfinatti.wanikanisimple.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfinatti.wanikanisimple.home.domain.GetSummaryUseCase
import com.mfinatti.wanikanisimple.home.domain.state.SummaryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model for the home screen.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSummaryUseCase: GetSummaryUseCase,
) : ViewModel() {

    private val _summaryState = MutableStateFlow<SummaryState>(SummaryState.Loading)

    /**
     * State flow for the summary data.
     */
    val summaryState: StateFlow<SummaryState> = _summaryState

    init {
        viewModelScope.launch {
            getSummaryUseCase.execute().collect { state ->
                _summaryState.value = state
            }
        }
    }
}
