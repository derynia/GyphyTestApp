package com.gyphytestapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gyphytestapp.data.db.dao.LoadedPicsDao
import com.gyphytestapp.data.db.entity.LoadedPics

@Database(entities = [LoadedPics::class], version = 1)

abstract class GifsAppDb : RoomDatabase() {

    internal abstract fun loadedPicsDao(): LoadedPicsDao

    companion object {
        fun getInstance(context: Context): GifsAppDb {
            return Room.databaseBuilder(context, GifsAppDb::class.java, "gifs_app_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
