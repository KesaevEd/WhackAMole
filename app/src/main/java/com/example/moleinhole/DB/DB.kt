package com.example.moleinhole.DB

import android.provider.BaseColumns

object DB: BaseColumns {

    const val TABLE_RECORD = "RECORD"
    const val COLUMN_NAME_SCORE = "score"


    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "ServisEngeneering.db"

    const val CREATE_RECORD = "CREATE TABLE IF NOT EXISTS $TABLE_RECORD (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_SCORE TEXT)"


    const val SQL_DELETE_RECORD = "DROP TABLE IF EXISTS $TABLE_RECORD"

}