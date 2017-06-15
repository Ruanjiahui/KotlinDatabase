package ruan.mydatabase.com.Operation

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import ruan.mydatabase.com.api.DatabaseHelper

/**
 * Created by 19820 on 2017/6/15.
 */
open class Operation {

    companion object{
        private var sqLiteDatabase : SQLiteDatabase? = null
        private var databaseHelper : DatabaseHelper? = null
    }

    /**
     * 添加数据库数据
     *
     * @param context
     * @param db
     * @param table
     * @param contentValues
     */
    protected fun insert(context: Context , db : String , table : String , contentValues: ContentValues) : Int?{
        databaseHelper = CreateTable.getInstance(context , db)
        sqLiteDatabase = databaseHelper?.writableDatabase

        return sqLiteDatabase?.insert(table , null , contentValues)?.toInt()
    }


    /**
     * 更新数据库数据

     * @param context
     * *
     * @param db
     * *
     * @param table
     * *
     * @param contentValues
     * *
     * @param whereclause
     * *
     * @param whereargs
     */
    protected fun update(context: Context, db: String, table: String, contentValues: ContentValues, whereclause: String, whereargs: Array<String>): Int? {
        databaseHelper = CreateTable.getInstance(context, db)
        sqLiteDatabase = databaseHelper?.writableDatabase

        return sqLiteDatabase?.update(table, contentValues, whereclause, whereargs)
    }


    /**
     * 查询数据库

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @param colums        获取数据的列名称
     * *
     * @param selection     where后面的内容
     * *
     * @param selectionArgs where后面内容的参数
     * *
     * @param groupBy       GROUP BY后面的字符串
     * *
     * @param having        HAVING后面的字符串
     * *
     * @param orderBy       ORDER BY后面的字符串
     * *
     * @param limit         返回的数量
     * *
     * @return
     */
    protected fun query(context: Context, db: String, Table_Name: String, colums: Array<String>, selection: String?, selectionArgs: Array<String>?, groupBy: String?, having: String?, orderBy: String?, limit: String?): Cursor? {
        databaseHelper = CreateTable.getInstance(context, db)
        sqLiteDatabase = databaseHelper?.readableDatabase

        try {
            return sqLiteDatabase?.query(Table_Name, colums, selection, selectionArgs, groupBy, having, orderBy, limit)
        } catch (e: Exception) {
            return null
        }

    }


    /**
     * 查询去重数据库

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @param colums        获取数据的列名称
     * *
     * @param selection     where后面的内容
     * *
     * @param selectionArgs where后面内容的参数
     * *
     * @param groupBy       GROUP BY后面的字符串
     * *
     * @param having        HAVING后面的字符串
     * *
     * @param orderBy       ORDER BY后面的字符串
     * *
     * @param limit         返回的数量
     * *
     * @param distinctType      是否去重
     * *
     * @return
     */
    protected fun distinctQuery(context: Context, db: String, Table_Name: String, colums: Array<String>, selection: String, selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String, limit: String, distinctType: Array<String>?): Cursor? {
        databaseHelper = CreateTable.getInstance(context, db)
        sqLiteDatabase = databaseHelper?.getReadableDatabase()
        try {
            var distincts = ""
            if (distinctType != null && distinctType.size > 0) {
                distincts += distinctType[0]
                for (i in 1..distinctType.size - 1) {
                    distincts += "," + distinctType[i]
                }
            }
            return sqLiteDatabase?.query(Table_Name, colums, selection, selectionArgs, groupBy, having, distincts, limit)
        } catch (e: Exception) {
            return null
        }

    }


    /**
     * 删除表数据和删除但条数据

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @param whereclause
     * *
     * @param whereargs
     */

    protected fun delete(context: Context, db: String, Table_Name: String, whereclause: String, whereargs: Array<String>): Int? {
        databaseHelper = CreateTable.getInstance(context, db)
        sqLiteDatabase = databaseHelper?.getWritableDatabase()

        return sqLiteDatabase?.delete(Table_Name, whereclause, whereargs)
    }


    /**
     * 给表增加字段

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @param value
     */
    protected fun onUpgrade(context: Context, db: String, Table_Name: String, value: String) {
        databaseHelper = CreateTable.getInstance(context, db)
        sqLiteDatabase = databaseHelper?.getWritableDatabase()

        sqLiteDatabase?.execSQL("alter table $Table_Name add column $value")
    }

    /**
     * 判断有没有这个表

     * @param context
     * *
     * @param db
     * *
     * @param table
     * *
     * @return
     */
    protected fun TableVisiable(context: Context, db: String, table: String): Boolean {
        //这句的意思就是"select count(*) from table"
        val cursor = query(context, db, table, arrayOf("count(*)"), null, null, null, null, null, null)
        //循环读取cursor的数据
        if (cursor!!.moveToNext()) {
            //如果数据为0则视为没有该表
            if (cursor.getInt(0) == 0) {
                return false
            }
            //否则则有该表
            return true
        }
        return false
    }

    /**
     * 判断表有没有这条数据

     * @param context
     * *
     * @param db
     * *
     * @param table
     * *
     * @param key
     * *
     * @param value
     * *
     * @return
     */
    protected fun DataVisiable(context: Context, db: String, table: String, key: String, value: Array<String>): Boolean {
        //这句的意思就是"select count(*) from table"
        val cursor = query(context, db, table, arrayOf("count(*)"), key + "=?", value, null, null, null, null)
        //循环读取cursor的数据
        if (cursor!!.moveToNext()) {
            //如果数据为0则视为没有该表
            if (cursor.getInt(0) == 0) {
                return false
            }
            //否则则有该表
            return true
        }
        return false
    }

}