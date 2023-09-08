package com.example.deneme101

import android.content.ContentValues

class Notlardao {

    fun tumNotlar(vt: VeriTabaniH): ArrayList<ExampleItem> {
        val db = vt.writableDatabase
        val itemList = ArrayList<ExampleItem>()
        val c = db.rawQuery("SELECT * FROM notlar", null)// tüm notlar c içine atıldı

        while (c.moveToNext()) {
            val not = ExampleItem(
                c.getInt(c.getColumnIndex("not_id")),
                c.getString(c.getColumnIndex("baslik")),
                c.getString(c.getColumnIndex("not_icerik"))
            )
            itemList.add(not)
        }
        return itemList
    }

    fun notSil(vt: VeriTabaniH, not_id: Int) {
        val db = vt.writableDatabase
        db.delete("notlar", "not_id=?", arrayOf(not_id.toString()))
        db.close()
    }

    fun notEkle(vt: VeriTabaniH, baslik: String, not_icerik: String) {
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("baslik", baslik)
        values.put("not_icerik", not_icerik)
        db.insertOrThrow("notlar", null, values)
        db.close()

    }

    fun notguncelle(vt: VeriTabaniH, not_id: Int, baslik: String, not_icerik: String) {
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("baslik", baslik)
        values.put("not_icerik", not_icerik)
        db.update("notlar", values, "not_id=?", arrayOf(not_id.toString()))
        db.close()

    }
}