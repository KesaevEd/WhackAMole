package com.example.moleinhole.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class DbManager(context: Context) {

    val DbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = DbHelper.writableDatabase
    }

    fun closeDb(){
        DbHelper.close()
    }

    fun insertToDb(score: String){
        val values = ContentValues().apply {
            put(DB.COLUMN_NAME_SCORE, score)
        }
        db?.insert(DB.TABLE_RECORD, null, values)
    }

    fun readDbData(): String{
        var score = String()

        val cursor = db?.query(DB.TABLE_RECORD, null, null, null, null, null, null)

        while (cursor?.moveToNext()!!){
            val dataScore = cursor.getString(cursor.getColumnIndexOrThrow(DB.COLUMN_NAME_SCORE))

            score = dataScore
        }

        cursor.close()
        return score
    }

    fun checkDbOnFilling(): Boolean{
        return !readDbData().isEmpty()
    }

    fun isNewRecord(newScore:Int): Boolean{
        return newScore > readDbData().toInt()
    }

    //ФУНКЦИЯ ИЗМЕНЕНИЯ ДАННЫХ
    fun updateItem(score: String, id: Int){

        val selection = BaseColumns._ID + "=$id"

        val values = ContentValues().apply {
            put(DB.COLUMN_NAME_SCORE, score)

        }
        db?.update(DB.TABLE_RECORD, values, selection, null)
    }

}