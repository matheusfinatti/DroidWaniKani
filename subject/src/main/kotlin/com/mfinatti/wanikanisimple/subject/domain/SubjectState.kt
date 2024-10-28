package com.mfinatti.wanikanisimple.subject.domain

import com.mfinatti.wanikanisimple.models.data.Subject

sealed interface SubjectState {
    data object Loading : SubjectState
    data class Success(val subjects: List<Subject>) : SubjectState
    data class Error(val message: String?) : SubjectState
}
