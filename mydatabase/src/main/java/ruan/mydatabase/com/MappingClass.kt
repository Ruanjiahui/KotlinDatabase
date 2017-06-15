package ruan.mydatabase.com

import android.content.ContentValues
import android.database.Cursor
import ruan.mydatabase.com.Operation.ClassHandler
import ruan.mydatabase.com.api.LoadClass
import java.util.ArrayList

/**
 * Created by ruanjiahui on 2017/6/15.
 *
 * 将对象一一利用反射机制将对象转化成自己需要的东西
 */
class MappingClass : LoadClass(){
    /**
     * 将数据库Cursor游标封装成对象(这个获取对象的属性包括父类)

     * @param cursor    数据库的游标（保存数据库的数据）
     * *
     * @param loadclass 封装的类的对象
     * *
     * @return
     */
    override fun AnalysisCursors(cursor: Cursor, loadclass: Class<*>): ArrayList<Any> {
        return null!!
    }

    /**
     * 将数据库Cursor游标封装成对象(这个获取对象的属性不包括父类)

     * @param cursor    数据库的游标（保存数据库的数据）
     * *
     * @param loadclass 封装的类的对象
     * *
     * @return
     */
    override fun AnalysisCursor(cursor: Cursor, loadclass: Class<*>): ArrayList<Any> {
        return null!!
    }

    /**
     * 将数据库的Cursor转成对象列表

     * @param cursor
     * *
     * @param loadClass
     * *
     * @return
     */
    public override fun CursorToArray(cursor: Cursor, loadClass: Class<*>): ArrayList<Any> {
        val arrayList = ArrayList<Any>()
        val fields = loadClass.fields
        val declaredFields = loadClass.declaredFields

        val list = ClassHandler.FieldsToOne(fields, declaredFields)

        if (cursor.columnNames != null && cursor.columnNames.size > 0) {
            val colums = cursor.columnNames

            while (cursor.moveToNext()) {
                arrayList.add(ClassHandler.CursorToObject(list, cursor, colums, loadClass)!!)
            }
        }

        return arrayList
    }

    /**
     * 将对象的数据封装成可以插进数据库的数据(包括父类)

     * @param loadClass 封装的类对象
     * *
     * @param objects   类对象实体类
     * *
     * @return
     */
    override fun getContentValues(loadClass: Class<*>, objects: Any): ContentValues {
        return null!!
    }

    /**
     * 将对象的数据封装成可以插进数据库的数据(包括父类)

     * @param loadClass 封装的类对象
     * *
     * @param objects   类对象实体类
     * *
     * @return
     */
    override fun getContentValues(loadClass: Class<*>, objects: Any, colums: Array<String>): ContentValues {
        return null!!
    }

    /**
     * Class对象转ContentValues

     * @param loadClass
     * *
     * @param objects
     * *
     * @return
     */
    public override fun ClassToContentValue(loadClass: Class<*>, objects: Any): ContentValues {
        val fields = loadClass.fields
        val declaredFields = loadClass.declaredFields

        val list = ClassHandler.FieldsToOne(fields, declaredFields)

        val contentValues = ClassHandler.ObjectToContentValues(list, objects)

        return contentValues
    }

    /**
     * 将对象的数据封装成可以插进数据库的数据(不包括父类)

     * @param loadClass 封装的类对象
     * *
     * @param objects   类对象实体类
     * *
     * @return
     */
    override fun getContentValue(loadClass: Class<*>, objects: Any): ContentValues {
        return null!!
    }

    /**
     * 将Map集合解析封装成对象

     * @param map
     * *
     * @param loadClass
     * *
     * @return
     */
    override fun AnalysisMap(map: ArrayList<Map<Any, Any>>, loadClass: Class<*>): ArrayList<Any> {
        return null!!
    }

}