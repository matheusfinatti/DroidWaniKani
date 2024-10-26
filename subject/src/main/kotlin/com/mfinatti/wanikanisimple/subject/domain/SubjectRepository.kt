package com.mfinatti.wanikanisimple.subject.domain

import com.mfinatti.wanikanisimple.models.data.Subject
import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.models.types.ApiKey

interface SubjectRepository {

    suspend fun fetchSubjects(apiKey: ApiKey, user: User): Result<List<Subject>>
}
