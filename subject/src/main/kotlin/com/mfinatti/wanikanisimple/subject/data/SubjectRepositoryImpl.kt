package com.mfinatti.wanikanisimple.subject.data

import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.models.data.Subject
import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.subject.domain.SubjectRepository
import javax.inject.Inject

@Suppress("UnusedPrivateProperty")
class SubjectRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteWKDataSource
) : SubjectRepository {

    override suspend fun fetchSubjects(user: User): Result<List<Subject>> {
        TODO("Not yet implemented")
    }
}
