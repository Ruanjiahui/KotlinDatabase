package ruan.mydatabase.com.Operation

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import ruan.mydatabase.com.Crash.LogException
import ruan.mydatabase.com.api.LoadResouce
import java.util.ArrayList
import java.util.HashMap

/**
 * Created by 19820 on 2017/6/15.
 */
class GetDatabaseData : Operation() {


    /**
     * 这个是获取数据库的数据
     *
     *
     * 返回类型是Map集合

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @param colums
     * *
     * @param selection
     * *
     * @param selectionArgs
     * *
     * @param groupBy
     * *
     * @param having
     * *
     * @param orderBy
     * *
     * @param limit
     * *
     * @return
     */
    @Deprecated("")
    fun Query(context: Context, db: String, Table_Name: String, colums: Array<String>, selection: String, selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String, limit: String): Map<String, String> {
        val cursor = query(context, db, Table_Name, colums, selection, selectionArgs, groupBy, having, orderBy, limit)

        return AnalysisCursor(cursor)
    }


    /**
     * 这个是获取多个数据的方法

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @param colums
     * *
     * @param selection
     * *
     * @param selectionArgs
     * *
     * @param groupBy
     * *
     * @param having
     * *
     * @param orderBy
     * *
     * @param limit
     * *
     * @return
     */
    @Deprecated("")
    fun QueryArray(context: Context, db: String, Table_Name: String, colums: Array<String>, selection: String, selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String, limit: String): ArrayList<Map<String, String>> {
        val cursor = query(context, db, Table_Name, colums, selection, selectionArgs, groupBy, having, orderBy, limit)

        return AnalysisCursorArray(cursor)
    }


    /***
     * 这个是获取多个数据的方法 , 将数据库返回的数据封装成对象

     * @param context
     * *
     * @param db            数据库名称
     * *
     * @param Table_Name    表名称
     * *
     * @param colums        列名称
     * *
     * @param selection     where后面的是字符串
     * *
     * @param selectionArgs where后面的变量
     * *
     * @param groupBy
     * *
     * @param having
     * *
     * @param orderBy
     * *
     * @param limit
     * *
     * @param loadclass     封装成的对象名称(类名)
     * *
     * @return
     */
    fun QueryArray(context: Context, db: String, Table_Name: String, colums: Array<String>, selection: String, selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String, limit: String, loadclass: Class<*>, SuperClass: Boolean): ArrayList<Any> {
        val cursor = query(context, db, Table_Name, colums, selection, selectionArgs, groupBy, having, orderBy, limit)
        //如果传入对象是有父类则调用第一个
        if (SuperClass)
            return LoadResouce().CursorToObjects(context, cursor!!, loadclass)
        return LoadResouce().CursorToObject(context, cursor!!, loadclass)
    }


    /**
     * 这个是获取去重多个数据的方法

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @param colums
     * *
     * @param selection
     * *
     * @param selectionArgs
     * *
     * @param groupBy
     * *
     * @param having
     * *
     * @param orderBy
     * *
     * @param limit
     * *
     * @return
     */
    @Deprecated("")
    fun distinctQueryArray(context: Context, db: String, Table_Name: String, colums: Array<String>, selection: String, selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String, limit: String, distinct: Array<String>): ArrayList<Map<String, String>> {
        val cursor = distinctQuery(context, db, Table_Name, colums, selection, selectionArgs, groupBy, having, orderBy, limit, distinct)

        return AnalysisCursorArray(cursor)
    }


    /**
     * 这个是获取去重多个数据的方法

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @param colums
     * *
     * @param selection
     * *
     * @param selectionArgs
     * *
     * @param groupBy
     * *
     * @param having
     * *
     * @param orderBy
     * *
     * @param limit
     * *
     * @return
     */
    fun distinctQueryArray(context: Context, db: String, Table_Name: String, colums: Array<String>, selection: String, selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String, limit: String, distinctType: Array<String>, loadClass: Class<*>, SuperClass: Boolean): ArrayList<Any> {
        val cursor = distinctQuery(context, db, Table_Name, colums, selection, selectionArgs, groupBy, having, orderBy, limit, distinctType)

        if (SuperClass)
            return LoadResouce().CursorToObjects(context, cursor!!, loadClass)
        return LoadResouce().CursorToObject(context, cursor!!, loadClass)
    }


    /**
     * 这个是更新数据库的操作

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
    @Deprecated("")
    fun Update(context: Context, db: String, table: String, contentValues: ContentValues, whereclause: String, whereargs: Array<String>): Int? {
        return update(context, db, table, contentValues, whereclause, whereargs)
    }


    /**
     * 这个是更新数据库的操作

     * @param context
     * *
     * @param db          数据库的名称
     * *
     * @param table       数据库的表
     * *
     * @param whereclause 更新数据库where的key
     * *
     * @param whereargs   更新数据库where的value
     * *
     * @param object      更新数据（接收对象）
     * *
     * @param SuperClass  true当前这个是继承类false不是
     * *
     * @return
     */
    fun Update(context: Context, db: String, table: String, whereclause: String, whereargs: Array<String>, `object`: Any?, SuperClass: Boolean): Int? {
        if (`object` != null) {
            if (SuperClass)
                return update(context, db, table, LoadResouce().ObjectToContentValues(context, `object`.javaClass, `object`), whereclause, whereargs)
            return update(context, db, table, LoadResouce().ObjectToContentValue(context, `object`.javaClass, `object`), whereclause, whereargs)
        } else {
            LogException.ThrowRunTime("对象不能为空")
            return 0
        }
    }


    /**
     * 将数据插进数据库

     * @param context
     * *
     * @param db
     * *
     * @param table
     * *
     * @param contentValues
     */
    @Deprecated("")
    fun Insert(context: Context, db: String, table: String, contentValues: ContentValues): Int? {
        return insert(context, db, table, contentValues)
    }


    /**
     * 将数据插进数据库

     * @param context
     * *
     * @param db      数据库的名称
     * *
     * @param table   数据表的名称
     * *
     * @param object  插入数据（接收对象）
     */
    fun Insert(context: Context, db: String, table: String, `object`: Any?, SuperClass: Boolean): Int? {
        if (`object` != null) {
            if (SuperClass)
                return insert(context, db, table, LoadResouce().ObjectToContentValues(context, `object`.javaClass, `object`))
            return insert(context, db, table, LoadResouce().ObjectToContentValue(context, `object`.javaClass, `object`))
        } else {
            LogException.ThrowRunTime("对象不能为空")
            return 0
        }
    }

    /**
     * 删除表数据

     * @param context
     * *
     * @param db
     * *
     * @param table
     * *
     * @param whereclause
     * *
     * @param whereargs
     */
    fun Delete(context: Context, db: String, table: String, whereclause: String, whereargs: Array<String>): Int? {
        return delete(context, db, table, whereclause, whereargs)
    }

    /**
     * 判断数据库有没有该表

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @return
     */
    fun QueryTable(context: Context, db: String, Table_Name: String): Boolean {
        val cursor = query(context, db, Table_Name, arrayOf("count(*)"), "", null, "", "", "", "") ?: return false
        //如果拥有数据则说明有该表
        //没有数据则没有该表
        return true
    }






    private fun AnalysisCursor(cursor: Cursor?): Map<String, String> {
        val map = HashMap<String, String>()

        val colum = cursor?.columnNames
        //将cursor用游标的方式取出来
        if (cursor!!.moveToNext()) {
            //循环的方法把数据库的列名称给取出来
            for (columName in colum!!) {
                map.put(columName, cursor.getString(cursor.getColumnIndex(columName)))
            }
        }
        return map
    }


    private fun AnalysisCursorArray(cursor: Cursor?): ArrayList<Map<String, String>> {
        val arrayList = ArrayList<Map<String, String>>()

        val colum = cursor?.columnNames
        //判断cursor是否为空

        //将cursor用游标的方式取出来
        while (cursor!!.moveToNext()) {
            val map = HashMap<String, String>()
            //循环的方法把数据库的列名称给取出来
            for (columName in colum!!) {
                map.put(columName, cursor.getString(cursor.getColumnIndex(columName)))
            }
            arrayList.add(map)
        }
        return arrayList
    }


    /**
     * 判断数据库有没有该条的信息

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @param whereclause
     * *
     * @param wherevalues
     * *
     * @return
     */
    fun RepeatData(context: Context, db: String, Table_Name: String, whereclause: String, wherevalues: Array<String>): Boolean {
        val map = Query(context, db, Table_Name, arrayOf("count(*)"), whereclause, wherevalues, "", "", "", "")
        //数据库里面拥有该条的数据
        if (Integer.parseInt(map["count(*)"]) == 1) {
            return true
        }
        return false
    }

    /**
     * 判断数据存在则更新，不存在插入

     * @param context
     * *
     * @param db
     * *
     * @param Table_name
     * *
     * @param clause
     * *
     * @param wherevalues
     * *
     * @param contentValues
     * *
     * @param whereclause
     * *
     * @param whereargs
     */
    fun Insert2Update(context: Context, db: String, Table_name: String, clause: String, wherevalues: Array<String>, contentValues: ContentValues, whereclause: String, whereargs: Array<String>): Int? {
        //返回false则没有改用户的数据
        //true则有该条的数据

        if (RepeatData(context, db, Table_name, clause, wherevalues))
        //将信息更新
            return Update(context, db, Table_name, contentValues, whereclause, whereargs)
        else
        //将信息写到数据库
            return Insert(context, db, Table_name, contentValues)?.toInt()
    }

    /**
     * 获取数据库表里面的表字段

     * @param context
     * *
     * @param db
     * *
     * @param Table_Name
     * *
     * @return
     */
    fun getTableField(context: Context, db: String, Table_Name: String): Array<String>? {
        return query(context, db, Table_Name, null!!, "", null, "", "", "", "")?.columnNames
    }


}