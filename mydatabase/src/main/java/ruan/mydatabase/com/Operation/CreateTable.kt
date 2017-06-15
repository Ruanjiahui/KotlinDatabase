package ruan.mydatabase.com.Operation

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import ruan.mydatabase.com.api.DatabaseHelper
import ruan.mydatabase.com.api.Establish

/**
 * Created by 19820 on 2017/6/15.
 */
class CreateTable {

    companion object{
        var databaseHelper : DatabaseHelper? = null
        var sqLiteDatabase : SQLiteDatabase? = null
        var key : ArrayList<String>? = null
        var db : String? = ""

        var sql : String? = ""
        var content : String? = ""


        fun getInstance(context: Context , db_name : String) : DatabaseHelper?{
            if (!db?.equals(db_name)!!)
                databaseHelper = null

            if (databaseHelper == null) {
                databaseHelper = DatabaseHelper(context, db_name)
            }
            return databaseHelper
        }


        /**
         * 创建表的方法

         * @param context
         * *
         * @param db_name     数据库名字
         * *
         * @param Table_name  表的名字
         * *
         * @param establish   封装数据的对象
         * *
         * @param auto_key    自动增长的语句如果没有则为空
         * *
         * @param primary_key 主键没有则为空
         */
        fun TABLE(context: Context, db_name: String, Table_name: String, establish: Establish, auto_key: String, primary_key: String) {
            val databaseHelper = getInstance(context, db_name)
            sqLiteDatabase = databaseHelper?.writableDatabase

            key = establish.getKey()

            content = ""
            content = getContent(key, establish, auto_key, primary_key)

            sql = "CREATE TABLE $Table_name($content)"

            try {
                sqLiteDatabase?.execSQL(sql)
            } catch (e: Exception) {
                Log.e("Activity", "创建表不成功")
            }

        }


        /**
         * 创建表的方法

         * @param context
         * *
         * @param db_name     数据库名字
         * *
         * @param Table_name  表的名字
         * *
         * @param object      封装数据的对象
         * *
         * @param auto_key    自动增长的语句如果没有则为空
         * *
         * @param primary_key 主键没有则为空
         */
        fun TABLE(context: Context, db_name: String, Table_name: String, `object`: Any, auto_key: String, primary_key: String) {
            val databaseHelper = getInstance(context, db_name)
            sqLiteDatabase = databaseHelper?.getWritableDatabase()

            key = ClassHandler.ObjectToArraryName(`object`)

            content = ""
            content = getContent(ClassHandler.ObjectToArraryContent(`object`), key, auto_key, primary_key)

            println(content)

            sql = "CREATE TABLE $Table_name($content)"

            try {
                sqLiteDatabase?.execSQL(sql)
            } catch (e: Exception) {
                Log.e("Activity", "创建表不成功")
            }

        }


        /**
         * 将封装在establish这个对象的数据解析出来

         * @param list        创建表的字段名
         * *
         * @param establish   创建表的字段参数
         * *
         * @param auto_key    创建表的自动增长
         * *
         * @param primary_key 创建表的主键
         * *
         * @return
         */
        private fun getContent(list: ArrayList<String>?, establish: Establish, auto_key: String, primary_key: String): String?{
            for (i in 0..list!!.size - 1){
                content += list[i] + " " + establish.get(list[i] )

                if (list[i] .equals(primary_key))
                    content += " primary key"
                if (list[i] .equals(auto_key))
                    content += " auto_increment"

                if (i != list.size - 1)
                    content += ","
            }
            return content
        }

        /**
         * 将封装在establish这个对象的数据解析出来

         * @param list        创建表的字段名
         * *
         * @param list1       创建表的字段参数
         * *
         * @param auto_key    创建表的自动增长
         * *
         * @param primary_key 创建表的主键
         * *
         * @return
         */
        private fun getContent(list: ArrayList<String>, list1: ArrayList<String>?, auto_key: String, primary_key: String): String? {
            for (i in 0..list!!.size){
                content += list[i] + " " + list1?.get(i)

                if (list[i].equals(primary_key))
                    content += " primary key"
                if (list[i].equals(auto_key))
                    content += " auto_increment"

                if (i != list.size - 1)
                    content += ","
            }
            return content
        }

    }




}