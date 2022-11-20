package com.gyphytestapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LoadedPics")
data class LoadedPics(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "is_deleted") var isDeleted: Boolean = false
)
