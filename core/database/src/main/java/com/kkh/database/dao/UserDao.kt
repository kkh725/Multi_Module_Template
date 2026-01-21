package com.kkh.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kkh.database.entity.UserEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    /** -------------------------------
     *  CREATE
     *  ------------------------------- */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntry>)

    /** -------------------------------
     *  READ
     *  ------------------------------- */
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<UserEntry>>

    @Query("SELECT * FROM user WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): UserEntry?

    @Query("SELECT * FROM user WHERE name LIKE '%' || :keyword || '%'")
    fun searchByName(keyword: String): Flow<List<UserEntry>>

    /** -------------------------------
     *  UPDATE
     *  ------------------------------- */
    @Update
    suspend fun update(user: UserEntry)

    /** -------------------------------
     *  DELETE
     *  ------------------------------- */
    @Delete
    suspend fun delete(user: UserEntry)

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM user")
    suspend fun clearAll()
}
