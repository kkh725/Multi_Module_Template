package com.kkh.multimodule.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kkh.multimodule.database.dao.UserDao
import com.kkh.multimodule.database.entity.UserEntry

@Database(
    entities = [UserEntry::class],
    version = 1,
    exportSchema = false // 스키마 자동 내보내기 비활성화 (선택)
)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

