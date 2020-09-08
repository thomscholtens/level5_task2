package com.example.level5_task2.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "gamesTable")
data class Game (
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "platform")
    var platform: String,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: Date,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)