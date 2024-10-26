package com.mfinatti.wanikanisimple.subject.data

import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.models.data.Subject
import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.subject.domain.SubjectRepository
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    val remoteDataSource: RemoteWKDataSource
) : SubjectRepository {

    override suspend fun fetchSubjects(user: User): Result<List<Subject>> {
        val subjects = remoteDataSource.getSubjects(user.level.value)
        TODO("Not yet implemented")
    }
}
