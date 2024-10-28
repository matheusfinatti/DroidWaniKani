package com.mfinatti.wanikanisimple.subject.data

import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.models.types.Level
import com.mfinatti.wanikanisimple.subject.data.mapper.toSubject
import com.mfinatti.wanikanisimple.subject.domain.SubjectRepository
import com.mfinatti.wanikanisimple.subject.domain.SubjectState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteWKDataSource
) : SubjectRepository {

    override fun fetchSubjects(level: Level): Flow<SubjectState> = flow {
        emit(SubjectState.Loading)

        val result = remoteDataSource.getSubjects(level.value)
        result.fold(
            onSuccess = { subjectDto ->
                try {
                    val subjects = subjectDto.map { it.toSubject() }
                    emit(SubjectState.Success(subjects))
                } catch (e: IllegalStateException) {
                    emit(SubjectState.Error(e.message))
                }
            },
            onFailure = { error ->
                emit(SubjectState.Error(error.message))
            }
        )
    }
}
