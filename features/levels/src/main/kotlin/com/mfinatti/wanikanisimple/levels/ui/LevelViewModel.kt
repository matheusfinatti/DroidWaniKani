package com.mfinatti.wanikanisimple.levels.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfinatti.wanikanisimple.models.data.Subject
import com.mfinatti.wanikanisimple.models.types.Level
import com.mfinatti.wanikanisimple.models.types.SubscriptionType
import com.mfinatti.wanikanisimple.subject.domain.SubjectRepository
import com.mfinatti.wanikanisimple.subject.domain.SubjectState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * View model for the levels screen.
 */
@HiltViewModel(assistedFactory = LevelViewModel.Factory::class)
class LevelViewModel @AssistedInject constructor(
    @Assisted level: Int,
    subjectRepository: SubjectRepository,
) : ViewModel() {

    /**
     * State flow for the level data.
     */
    val subjectState: StateFlow<LevelUiModel> = subjectRepository
        .fetchSubjects(
            Level.from(
                level,
                SubscriptionType.lifetime
            ).getOrThrow()
        ).map { state ->
            when (state) {
                is SubjectState.Error -> LevelUiModel.Error(state.message)
                SubjectState.Loading -> LevelUiModel.Loading
                is SubjectState.Success -> LevelUiModel.Subjects(
                    state.subjects.map(Subject::toSubjectItem)
                )
            }
        }.stateIn(
            scope = viewModelScope,
            initialValue = LevelUiModel.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )

    @AssistedFactory
    interface Factory {
        fun create(level: Int): LevelViewModel
    }
}

sealed interface LevelUiModel {
    data object Loading : LevelUiModel
    data class Subjects(val subjects: List<SubjectItem>) : LevelUiModel
    data class Error(val error: String?) : LevelUiModel
}
