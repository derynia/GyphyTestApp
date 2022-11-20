package com.gyphytestapp.data.db.dao

import androidx.room.*
import com.gyphytestapp.data.db.entity.LoadedPics

@Dao
interface LoadedPicsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(loadedPics: List<LoadedPics>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(loadedPic: LoadedPics)

    @Query("SELECT * FROM LoadedPics WHERE is_deleted = 1 AND id IN (:idsList)")
    fun selectDeleted(idsList: List<String>): List<LoadedPics>

    @Delete
    fun delete(loadedPic: LoadedPics)

    @Query("DELETE FROM LoadedPics")
    fun deleteAll()

    @Update
    fun setDeleted(loadedPic: LoadedPics)
}