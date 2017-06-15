package ruan.mydatabase.com.api

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import java.util.*
import java.util.Map.Entry

/**
 * Created by 19820 on 2017/6/15.
 */
class LoadResouce : LoadClass() {

    private var context: Context? = null

    /**
     * 将数据库Cursor游标封装成对象(这个获取对象的属性包括父类)

     * @param cursor    数据库的游标（保存数据库的数据）
     * *
     * @param loadclass 封装的类的对象
     * *
     * @return
     */
    override fun AnalysisCursors(cursor: Cursor, loadclass: Class<*>): ArrayList<Any> {
        val list = ArrayList<Any>()
        if (cursor != null) {
            //新建链表对象存在数据
            try {
                //获取该类的所有属性
                //包括父类的所有属性
                val fields = loadclass.fields
                //将数据库返回的数据
                val colum = cursor.columnNames
                Log.d("Ruan", Arrays.toString(colum))
                //将cursor用游标的方式取出来
                while (cursor.moveToNext()) {
                    val `object` = loadclass.newInstance()
                    //循环的方法把数据库的列名称给取出来
                    for (columName in colum) {
                        for (i in fields.indices) {
                            //获取类里面的所有属性
                            val field = fields[i]
                            //设置可以访问
                            field.isAccessible = true
                            //如果数据库的字段名和类的属性名称一样就说明是同一个变量
                            if (field.name == columName) {
                                //将数据库的数据设置给类的属性
                                field.set(`object`, cursor.getString(cursor.getColumnIndex(columName)))
                                break
                            }
                        }
                    }
                    list.add(`object`)
                }
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }

        }
        return list
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
        val list = ArrayList<Any>()
        if (cursor != null) {
            //新建链表对象存在数据
            try {
                //获取该类的所有属性
                //包括父类的所有属性
                val fields = loadclass.declaredFields
                //将数据库返回的数据
                val colum = cursor.columnNames

                //将cursor用游标的方式取出来
                while (cursor.moveToNext()) {
                    val `object` = loadclass.newInstance()
                    //循环的方法把数据库的列名称给取出来
                    for (columName in colum) {
                        for (i in fields.indices) {
                            //获取类里面的所有属性
                            val field = fields[i]
                            //设置可以访问
                            field.isAccessible = true
                            //如果数据库的字段名和类的属性名称一样就说明是同一个变量
                            if (field.name == columName) {
                                //将数据库的数据设置给类的属性
                                field.set(`object`, cursor.getString(cursor.getColumnIndex(columName)))
                                break
                            }
                        }
                    }
                    list.add(`object`)
                }
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }

        }
        return list
    }

    /**
     * 将数据库的Cursor转成对象列表

     * @param cursor
     * *
     * @param loadClass
     * *
     * @return
     */
    override fun CursorToArray(cursor: Cursor, loadClass: Class<*>): ArrayList<Any> {
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
    override fun getContentValues(loadClass: Class<*>, objects: Any): ContentValues {
        val contentValues = ContentValues()

        val fields = loadClass.fields
        try {
            for (field in fields) {
                field.isAccessible = true
                if (!field.name.startsWith("&") && !field.name.startsWith("CREATOR") && !field.name.startsWith("CONTENTS_FILE_DESCRIPTOR") && !field.name.startsWith("PARCELABLE_WRITE_RETURN_VALUE")) {
                    contentValues.put(field.name, "" + field.get(objects))
                    break
                }
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }


        return contentValues
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
        val contentValues = ContentValues()

        val fields = loadClass.fields
        try {
            for (colum in colums) {
                for (field in fields) {
                    field.isAccessible = true
                    if (field.name == colum) {
                        contentValues.put(field.name, field.get(objects) as String)
                        break
                    }
                }
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return contentValues
    }

    /**
     * Class对象转ContentValues

     * @param loadClass
     * *
     * @param objects
     * *
     * @return
     */
    override fun ClassToContentValue(loadClass: Class<*>, objects: Any): ContentValues {
        return null!!
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
        val contentValues = ContentValues()

        val fields = loadClass.declaredFields
        try {
            for (field in fields) {
                field.isAccessible = true
                if (field.get(objects) as String != null && "" == field.get(objects) as String) {
                    contentValues.put(field.name, field.get(objects) as String)
                    break
                }
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return contentValues
    }

    /**
     * 将Map集合解析封装成对象

     * @param map
     * *
     * @param loadClass
     * *
     * @return
     */
    override fun AnalysisMap(list: ArrayList<Map<Any, Any>>, loadClass: Class<*>): ArrayList<Any> {
        val obj = ArrayList<Any>()
        if (list != null && list.size != 0) {
            // 方法 一 ：
            // 转化成  Set>>，然后在转化为迭代器
            val iterator = list[0].entries.iterator()
            val colum = ArrayList<String>()
            while (iterator.hasNext()) {
                val entry = iterator.next() as Entry<*, *>
                colum.add(entry.key as String)
            }

            if (list != null) {
                //新建链表对象存在数据
                try {
                    //获取该类的所有属性
                    val fields = loadClass.declaredFields
                    //将cursor用游标的方式取出来
                    for (map in list) {
                        val `object` = loadClass.newInstance()
                        //循环的方法把数据库的列名称给取出来
                        for (columName in colum) {
                            for (i in fields.indices) {
                                //获取类里面的所有属性
                                val field = fields[i]
                                //设置可以访问
                                field.isAccessible = true
                                //如果数据库的字段名和类的属性名称一样就说明是同一个变量
                                if (field.name == columName) {
                                    //将数据库的数据设置给类的属性
                                    field.set(`object`, map.get(columName))
                                    break
                                }
                            }
                        }
                        obj.add(`object`)
                    }
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InstantiationException) {
                    e.printStackTrace()
                }

            }
        }
        return obj
    }


    /**
     * 对外面开放的接口（包括父类）

     * @param cursor
     * *
     * @param loadclass
     * *
     * @return
     */
    fun CursorToObjects(context: Context, cursor: Cursor, loadclass: Class<*>): ArrayList<Any> {
        this.context = context
        return AnalysisCursors(cursor, loadclass)
    }

    /**
     * 对外面开放的接口（不包括父类）

     * @param cursor
     * *
     * @param loadclass
     * *
     * @return
     */
    fun CursorToObject(context: Context, cursor: Cursor, loadclass: Class<*>): ArrayList<Any> {
        this.context = context
        return AnalysisCursor(cursor, loadclass)
    }

    /**
     * 对外面开放的接口(包括父类)

     * @param loadClass
     * *
     * @param objects
     * *
     * @param colums
     * *
     * @return
     */
    fun ObjectToContentValues(context: Context, loadClass: Class<*>, objects: Any, colums: Array<String>): ContentValues {
        this.context = context
        return getContentValues(loadClass, objects, colums)
    }


    /**
     * 对外面开放的接口(包括父类)

     * @param loadClass
     * *
     * @param objects
     * *
     * @return
     */
    fun ObjectToContentValues(context: Context, loadClass: Class<*>, objects: Any): ContentValues {
        this.context = context
        return getContentValues(loadClass, objects)
    }

    /**
     * 对外面开放的接口(不包括父类)

     * @param loadClass
     * *
     * @param objects
     * *
     * @param
     * *
     * @return
     */
    fun ObjectToContentValue(context: Context, loadClass: Class<*>, objects: Any): ContentValues {
        this.context = context
        return getContentValue(loadClass, objects)
    }


    /**
     * 对外面开放的接口

     * @param map
     * *
     * @param loadclass
     * *
     * @return
     */
    fun MapToObjects(map: ArrayList<Map<Any, Any>>, loadclass: Class<*>): ArrayList<Any> {
        return AnalysisMap(map, loadclass)
    }

}