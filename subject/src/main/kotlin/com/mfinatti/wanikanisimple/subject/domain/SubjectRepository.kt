package com.mfinatti.wanikanisimple.subject.domain

import com.mfinatti.wanikanisimple.models.data.Subject
import com.mfinatti.wanikanisimple.models.data.User

interface SubjectRepository {

    suspend fun fetchSubjects(user: User): Result<List<Subject>>
}
