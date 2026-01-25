package com.kkh.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kkh.database.dao.UserDao
import com.kkh.database.entity.UserEntry

@Database(
    entities = [UserEntry::class],
    version = 1,
    // 스키마 자동 내보내기 비활성화 (선택)
    exportSchema = false,
)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}