package com.example.octopoints.model.dataaccess

import android.content.Context
import androidx.room.Room

open class DataAccess(context: Context) {
    val db = Room.databaseBuilder(context, AppDatabase::class.java, "octopoints-db").build()

    fun closeDb() {
        db.close()
    }
}