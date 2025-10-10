package com.kkh.multimodule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        @Volatile
        private var INSTANCE: MyAppDatabase? = null

        fun getInstance(context: Context): MyAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyAppDatabase::class.java,
                    "my_app_database" // 데이터베이스 파일명
                )
                    .fallbackToDestructiveMigration(false) // 스키마 변경 시 데이터 초기화 (테스트용)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}

