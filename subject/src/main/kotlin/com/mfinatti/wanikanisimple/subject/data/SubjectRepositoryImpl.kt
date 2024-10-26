package com.mfinatti.wanikanisimple.subject.data

import android.util.Log
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.models.data.Subject
import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.models.types.ApiKey
import com.mfinatti.wanikanisimple.subject.domain.SubjectRepository
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    val remoteDataSource: RemoteWKDataSource
) : SubjectRepository {

    override suspend fun fetchSubjects(apiKey: ApiKey, user: User): Result<List<Subject>> {
        val subjects = remoteDataSource.getSubjects(apiKey.value, user.level.value)
        Log.d(Consts.TAG, "Subjects: $subjects")
        TODO("Not yet implemented")
    }
}