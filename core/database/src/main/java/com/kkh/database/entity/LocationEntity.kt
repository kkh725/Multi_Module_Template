package com.kkh.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age : Int
)