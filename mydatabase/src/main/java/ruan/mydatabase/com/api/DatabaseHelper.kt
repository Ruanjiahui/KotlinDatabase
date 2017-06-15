package ruan.mydatabase.com.api

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by ruanjiahui on 2017/6/15.
 */
class DatabaseHelper : SQLiteOpenHelper{


    private var context : Context? = null
    private var sqlite : SQLiteDatabase? = null


    constructor(context: Context? , name : String? , factory: SQLiteDatabase.CursorFactory? , version : Int) : super(context , name , factory , version)

    constructor(context: Context? , name : String? , version : Int) : super(context , name , null , version)

    constructor(context: Context? , name : String?) : super(context , name , null , 1)



    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.

     * @param db The database.
     */
    override fun onCreate(db: SQLiteDatabase?) {
    }

    /**
     *更新数据库的操作
     *
     * @param db The database.
     * *
     * @param oldVersion The old database version.
     * *
     * @param newVersion The new database version.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion != oldVersion)
            onCreate(db)
    }

}