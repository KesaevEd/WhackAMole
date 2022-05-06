package com.example.moleinhole

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract

class Prefs(context: Context?) {
    private val PREF_STAR = "appPrefOne"
    private val STAR_KEY = "result"
    private val preferences: SharedPreferences =
        context!!.getSharedPreferences(PREF_STAR, Context.MODE_PRIVATE)

    fun saveResult(countStar: Int) {
        preferences.edit().putInt(STAR_KEY, countStar).apply()
    }

    fun getResult(): Int {
        return preferences.getInt(STAR_KEY, 0)
    }

    fun isPrefsEmpty(): Boolean{
        return getResult() == 0
    }

    fun isNewRecord(newResult: Int): Boolean{
        return newResult > getResult()
    }
}