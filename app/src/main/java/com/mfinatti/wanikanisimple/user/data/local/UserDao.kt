package com.mfinatti.wanikanisimple.user.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Transaction
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Transaction
    @Update
    suspend fun updateUser(user: UserEntity)

    @Transaction
    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: String): Flow<UserEntity>
}
