package com.example.deneme101

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeriTabaniH (context:Context):SQLiteOpenHelper(context,"notlar.sqlite",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE notlar(not_id INTEGER PRIMARY KEY AUTOINCREMENT, baslik TEXT, not_icerik TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
       db?.execSQL("DROP TABLE IF EXISTS notlar")
        onCreate(db)
    }
}