package com.bookreader.bookworm_kotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBaseHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table users(username TEXT primary key, email TEXT, password TEXT)")
        //db?.execSQL("create table book()")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists users")
    }

    public fun insertData(
        username: String,
        email: String,
        password: String
    ): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put("username", username)
        values.put("email", email)
        values.put("password", password)

        val result = db.insert("users", null, values)
        return result != (-1).toLong()

    }

    public fun checkUsername(username: String): Boolean {
        val db = writableDatabase
        val cursor = db.rawQuery("select * from users where username=?", arrayOf(username))

        return cursor.count > 0

    }

    public fun checkEmail(email: String): Boolean {
        val db = writableDatabase
        val cursor = db.rawQuery("select * from users where email=?", arrayOf(email))

        return cursor.count > 0

    }

    public fun checkUsernamePassword(
        username: String,
        password: String
    ): Boolean {
        val db = writableDatabase
        val cursor = db.rawQuery("select * from users where username=? and password=?", arrayOf(username, password))

        return cursor.count > 0

    }

}