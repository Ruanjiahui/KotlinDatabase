package ruan.mydatabase.com.Operation

import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import ruan.mydatabase.com.api.DataType
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.*

/**
 * Created by 19820 on 2017/6/15.
 */
class ClassHandler {

    companion object {
        /**
         * 将两个Fields的数组去重

         * @param fields
         * *
         * @param declaredFields
         * *
         * @return
         */
        fun FieldsToOne(fields: Array<Field>, declaredFields: Array<Field>): ArrayList<Field> {
            val list = ArrayList<Field>()

            // 首先将public 的属性全部添加到list里面之后进行去重
            for (field in fields)
                list.add(field)
            for (declaredField in declaredFields) {
                if (isSame(list, declaredField))
                    list.add(declaredField)
            }
            return list
        }

        /**
         * 判断两个属性是否一样

         * @param list
         * *
         * @param declaredField
         * *
         * @return
         */
        private fun isSame(list: ArrayList<Field>, declaredField: Field): Boolean {
            for (field in list) {
                if (field.name == declaredField.name)
                    return false
            }
            return true
        }

        /**
         * 将Object转化成ContentValues；

         * @param list
         * *
         * @param o
         * *
         * @return
         */
        fun ObjectToContentValues(list: ArrayList<Field>, o: Any): ContentValues {
            val contentValues = ContentValues()
            try {
                for (field in list) {
                    field.isAccessible = true

                    //如果属性的修饰符是private 和 public
                    if (DataType.PRIVATE.equals(Modifier.toString(field.modifiers)) || DataType.PUBLIC.equals(Modifier.toString(field.modifiers))) {
                        when (field.type.toString()) {
                            DataType.STRING -> contentValues.put(field.name, field.get(o) as String)
                            DataType.INTEGER -> contentValues.put(field.name, field.get(o) as Int)
                            DataType.INT -> contentValues.put(field.name, field.get(o) as Int)
                            DataType.ClassDOUBLE -> contentValues.put(field.name, field.get(o) as Double)
                            DataType.ClassSHORT -> contentValues.put(field.name, field.get(o) as Short)
                            DataType.ClassLONG -> contentValues.put(field.name, field.get(o) as Long)
                            DataType.ClassBOOLEAN -> contentValues.put(field.name, field.get(o) as Boolean)
                            DataType.ClassFLOAT -> contentValues.put(field.name, field.get(o) as Float)
                            DataType.ClassBYTE -> contentValues.put(field.name, field.get(o) as Byte)
                            DataType.BOOLEAN -> contentValues.put(field.name, field.get(o) as Boolean)
                            DataType.SHORT -> contentValues.put(field.name, field.get(o) as Short)
                            DataType.DOUBLE -> contentValues.put(field.name, field.get(o) as Double)
                            DataType.FLOAT -> contentValues.put(field.name, field.get(o) as Float)
                            DataType.LONG -> contentValues.put(field.name, field.get(o) as Long)
                            DataType.BYTE -> contentValues.put(field.name, field.get(o) as Byte)
                        }
                    }
                }
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            return contentValues
        }

        /**
         * 通过将Cursor 转化成 对象

         * @param list
         * *
         * @param cursor
         * *
         * @param colums
         * *
         * @param loadClass
         * *
         * @return
         */
        fun CursorToObject(list: ArrayList<Field>, cursor: Cursor, colums: Array<String>, loadClass: Class<*>): Any? {
            var `object`: Any? = null
            try {
                `object` = loadClass.newInstance()
                for (columName in colums) {
                    for (field in list) {
                        //设置可以访问
                        field.isAccessible = true

                        //如果数据库的字段名和类的属性名称一样就说明是同一个变量
                        if (field.name == columName) {
                            //将数据库的数据设置给类的属性
                            when (field.type.toString()) {
                                DataType.STRING -> field.set(`object`, cursor.getString(cursor.getColumnIndex(columName)))
                                DataType.INTEGER -> field.set(`object`, cursor.getInt(cursor.getColumnIndex(columName)))
                                DataType.INT -> field.set(`object`, cursor.getInt(cursor.getColumnIndex(columName)))
                                DataType.ClassDOUBLE -> field.set(`object`, cursor.getDouble(cursor.getColumnIndex(columName)))
                                DataType.ClassSHORT -> field.set(`object`, cursor.getShort(cursor.getColumnIndex(columName)))
                                DataType.ClassLONG -> field.set(`object`, cursor.getLong(cursor.getColumnIndex(columName)))
                                DataType.ClassFLOAT -> field.set(`object`, cursor.getFloat(cursor.getColumnIndex(columName)))
                                DataType.BOOLEAN -> if (cursor.getInt(cursor.getColumnIndex(columName)) == 0)
                                    field.set(`object`, false)
                                else
                                    field.set(`object`, true)
                                DataType.SHORT -> field.set(`object`, cursor.getInt(cursor.getColumnIndex(columName)))
                                DataType.DOUBLE -> field.set(`object`, cursor.getDouble(cursor.getColumnIndex(columName)))
                                DataType.FLOAT -> field.set(`object`, cursor.getFloat(cursor.getColumnIndex(columName)))
                                DataType.LONG -> field.set(`object`, cursor.getLong(cursor.getColumnIndex(columName)))
                                DataType.BYTE -> field.set(`object`, cursor.getString(cursor.getColumnIndex(columName)))
                            }
                            break
                        }
                    }
                }
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            return `object`
        }

        /**
         * 将名称数据转化成字符串

         * @param name
         * *
         * @return
         */
        fun ArrayToString(name: Array<String>?): String {
            var wherearg = ""
            if (name != null && name.size > 0) {
                for (i in name.indices) {
                    wherearg += name[i] + "=?"
                    if (i != name.size - 1)
                        wherearg += " and "
                }
            }
            return wherearg
        }


        /**
         * 将名称数据转化成字符串

         * @param distinctType
         * *
         * @return
         */
        fun distinctArrayToString(distinctType: Array<String>): String {
            var wherearg = ""
            for (i in distinctType.indices) {
                wherearg += distinctType[i]
                if (i != distinctType.size - 1)
                    wherearg += ","
            }
            return wherearg
        }

        /**
         * 通过对象Object 获取private获取public每个属性的类型

         * @param object
         * *
         * @return
         */
        fun ObjectToArraryName(`object`: Any): ArrayList<String> {
            val list = ArrayList<String>()

            val loadClass = `object`.javaClass

            val fields = loadClass.fields
            val declaredFields = loadClass.declaredFields

            println(Arrays.toString(fields) + "--" + fields.size)

            val fieldList = ClassHandler.FieldsToOne(fields, declaredFields)

            for (field in fieldList) {
                field.isAccessible = true

                //如果属性的修饰符是private 和 public
                if (DataType.PRIVATE.equals(Modifier.toString(field.modifiers)) || DataType.PUBLIC.equals(Modifier.toString(field.modifiers))) {
                    Log.e("Ruan", field.type.toString())
                    when (field.type.toString()) {
                        DataType.STRING -> list.add("varchar(255)")
                        DataType.INTEGER -> list.add("integer")
                        DataType.INT -> list.add("integer")
                        DataType.ClassDOUBLE -> list.add("double")
                        DataType.ClassSHORT ->
                            //sqlite不支持short类型使用int代替
                            list.add("integer")
                        DataType.ClassLONG ->
                            //sqlite不支持long类型使用int代替
                            list.add("integer")
                        DataType.ClassBOOLEAN ->
                            //sqlite不支持boolean类型使用int代替
                            list.add("integer")
                        DataType.ClassFLOAT -> list.add("float")
                        DataType.ClassBYTE ->
                            //sqlite不支持byte类型使用varchar代替
                            list.add("varchar(255)")
                        DataType.BOOLEAN -> list.add("integer")
                        DataType.SHORT -> list.add("integer")
                        DataType.DOUBLE -> list.add("double")
                        DataType.FLOAT -> list.add("float")
                        DataType.LONG -> list.add("integer")
                        DataType.BYTE -> list.add("varchar(255)")
                    }
                }
            }
            return list
        }

        /**
         * 通过对象Object 获取属性名称

         * @param object
         * *
         * @return
         */
        fun ObjectToArraryContent(`object`: Any): ArrayList<String> {
            val list = ArrayList<String>()

            val loadClass = `object`.javaClass

            Log.e("Ruan", loadClass.name)

            val fields = loadClass.fields
            val declaredFields = loadClass.declaredFields

            val fieldList = ClassHandler.FieldsToOne(fields, declaredFields)

            for (field in fieldList) {
                field.isAccessible = true

                //如果属性的修饰符是private 和 public
                if (DataType.PRIVATE.equals(Modifier.toString(field.modifiers)) || DataType.PUBLIC.equals(Modifier.toString(field.modifiers))) {
                    list.add(field.name)
                }
            }
            return list
        }
    }

}