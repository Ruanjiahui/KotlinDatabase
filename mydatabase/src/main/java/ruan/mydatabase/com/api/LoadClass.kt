package ruan.mydatabase.com.api

import android.content.ContentValues
import android.database.Cursor
import java.util.ArrayList

/**
 * Created by 19820 on 2017/6/15.
 */
abstract class LoadClass {

    /**
     * 将数据库Cursor游标封装成对象(这个获取对象的属性包括父类)

     * @param cursor    数据库的游标（保存数据库的数据）
     * *
     * @param loadclass 封装的类的对象
     * *
     * @return
     */
    protected abstract fun AnalysisCursors(cursor: Cursor, loadclass: Class<*>): ArrayList<Any>


    /**
     * 将数据库Cursor游标封装成对象(这个获取对象的属性不包括父类)

     * @param cursor    数据库的游标（保存数据库的数据）
     * *
     * @param loadclass 封装的类的对象
     * *
     * @return
     */
    protected abstract fun AnalysisCursor(cursor: Cursor, loadclass: Class<*>): ArrayList<Any>


    /**
     * 将数据库的Cursor转成对象列表

     * @param cursor
     * *
     * @param loadClass
     * *
     * @return
     */
    protected abstract fun CursorToArray(cursor: Cursor, loadClass: Class<*>): ArrayList<Any>

    /**
     * 将对象的数据封装成可以插进数据库的数据(包括父类)

     * @param loadClass 封装的类对象
     * *
     * @param objects   类对象实体类
     * *
     * @return
     */
    @Deprecated("")
    protected abstract fun getContentValues(loadClass: Class<*>, objects: Any): ContentValues


    /**
     * 将对象的数据封装成可以插进数据库的数据(包括父类)

     * @param loadClass 封装的类对象
     * *
     * @param objects   类对象实体类
     * *
     * @return
     */
    @Deprecated("")
    protected abstract fun getContentValues(loadClass: Class<*>, objects: Any, colums: Array<String>): ContentValues

    /**
     * Class对象转ContentValues

     * @param loadClass
     * *
     * @param objects
     * *
     * @return
     */
    protected abstract fun ClassToContentValue(loadClass: Class<*>, objects: Any): ContentValues


    /**
     * 将对象的数据封装成可以插进数据库的数据(不包括父类)

     * @param loadClass 封装的类对象
     * *
     * @param objects   类对象实体类
     * *
     * @return
     */
    protected abstract fun getContentValue(loadClass: Class<*>, objects: Any): ContentValues


    /**
     * 将Map集合解析封装成对象

     * @param map
     * *
     * @param loadClass
     * *
     * @return
     */
    protected abstract fun AnalysisMap(map: ArrayList<Map<Any, Any>>, loadClass: Class<*>): ArrayList<Any>

}