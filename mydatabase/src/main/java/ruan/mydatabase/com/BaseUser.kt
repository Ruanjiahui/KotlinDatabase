package ruan.mydatabase.com

import android.content.ContentValues
import android.content.Context
import ruan.mydatabase.com.Operation.ClassHandler
import ruan.mydatabase.com.Operation.Operation
import java.util.ArrayList

/**
 * Created by ruanjiahui on 2017/6/15.
 *
 * 数据库各个方法的使用类
 */
class BaseUser(var context: Context) : Operation(){

//    private var context : Context = null!!
//
//    init{
//        this.context = context
//    }


    /**
     * 数据库插入的方法
     *
     * 支持对象传参 和 ContentValues
     */
    /************************************************* **********************************************/
    fun INSERT(database : String , table: String , obj : Object?) : Int?{
        return insert(context, database, table, MappingClass().ClassToContentValue(obj?.`class`!!, obj))
    }

    /**
     * 数据库插入数据

     * @param database      数据库的名称
     * *
     * @param table         数据库的表名
     * *
     * @param contentValues 插入数据库的ContentValues
     */
    fun INSERT(database: String, table: String, contentValues: ContentValues): Int? {
        return insert(context, database, table, contentValues)
    }


    /**
     * 数据库删除的方法
     */

    /************************************************  ************************************************/

    /**
     * 删除数据库的数据表全部数据

     * @param database 数据库的名称
     * *
     * @param table    数据库的表名
     */
    fun DELETE(database: String, table: String): Int? {
        return delete(context, database, table, null!!, null!!)
    }

    /**
     * 删除数据库表的指定的数据

     * @param database    数据库的名称
     * *
     * @param table       数据库的表名
     * *
     * @param whereclause where后面的指定字段名
     * *
     * @param whereargs   where后面的指定字段名的参数
     */
    fun DELETE(database: String, table: String, whereclause: Array<String>, whereargs: Array<String>): Int? {
        return delete(context, database, table, ClassHandler.ArrayToString(whereclause), whereargs)
    }


    /**
     * 数据库查询数据的方法
     *
     * 支持赋值对象获取数据
     */
    /******************************************  *************************************************/

    /**
     * 获取数据库表的全部内容

     * @param database  数据库的名称
     * *
     * @param table     数据库表的名称
     * *
     * @param loadClass 存储的对象
     * *
     * @return 返回的一个链表对象
     */
    fun QUERYArray(database: String, table: String, loadClass: Class<*>): ArrayList<Any> {
        return MappingClass().CursorToArray(query(context, database, table, null!!, "", null, "", "", "", "")!!, loadClass)
    }


    /**
     * 获取数据库表的全部内容

     * @param database  数据库的名称
     * *
     * @param table     数据库表的名称
     * *
     * @param loadClass 存储的对象
     * *
     * @return 返回的一个对象(返回第一个的对象)
     */
    fun QUERY(database: String, table: String, loadClass: Class<*>): Any? {
        val list = MappingClass().CursorToArray(query(context, database, table, null!!, "", null, "", "", "", "")!!, loadClass)
        if (list != null && list.size > 0)
            return list[0]
        return null
    }

    /**
     * 获取数据库表的指定数据内容

     * @param database    数据库的名称
     * *
     * @param table       数据库表的名称
     * *
     * @param whereclause where后面指定的字段名
     * *
     * @param whereargs   where后面指定的字段名的数值
     * *
     * @param loadClass   存储的对象
     * *
     * @return 返回一个链表的对象
     */
    fun QUERYArray(database: String, table: String, whereclause: Array<String>, whereargs: Array<String>, loadClass: Class<*>): ArrayList<Any> {
        return MappingClass().CursorToArray(query(context, database, table, null!!, ClassHandler.ArrayToString(whereclause), whereargs, "", "", "", "")!!, loadClass)
    }


    /**
     * 获取数据库表的指定数据内容

     * @param database    数据库的名称
     * *
     * @param table       数据库表的名称
     * *
     * @param whereclause where后面指定的字段名
     * *
     * @param whereargs   where后面指定的字段名的数值
     * *
     * @param loadClass   存储的对象
     * *
     * @return 返回一个对象
     */
    fun QUERY(database: String, table: String, whereclause: Array<String>, whereargs: Array<String>, loadClass: Class<*>): Any? {
        val list = MappingClass().CursorToArray(query(context, database, table, null!!, ClassHandler.ArrayToString(whereclause), whereargs, "", "", "", "")!!, loadClass)
        if (list != null && list.size > 0)
            return list[0]
        return null
    }


    /**
     * 获取数据库的数据（去重即就是去掉相同的数据）

     * @param database     数据库的名称
     * *
     * @param table        数据库表的名称
     * *
     * @param distinctType 去重的字段名
     * *
     * @param loadClass    存储对象
     * *
     * @return 返回一个链表的对象
     */
    fun distinctQUERY(database: String, table: String, distinctType: Array<String>, loadClass: Class<*>): ArrayList<Any> {
        return MappingClass().CursorToArray(distinctQuery(context, database, table, null!!, "", null!!, "", "", "", "", distinctType)!!, loadClass)
    }


    /**
     * 获取数据库的数据这个是满足所有要求的数据库查询，可以自定义的传数据

     * @param database      数据库的名称
     * *
     * @param table         数据库表的名称
     * *
     * @param colums        获取数据库项名
     * *
     * @param selection     where后面指定查询的字段名
     * *
     * @param selectionArgs where后面指定查询的字段名值
     * *
     * @param groupBy       groupby后面的参数
     * *
     * @param having        having后面的参数
     * *
     * @param orderBy       orderBy后面的参数
     * *
     * @param limit         limit后面的参数
     * *
     * @return 返回一个链表的对象
     */
    fun QUERYArray(database: String, table: String, colums: Array<String>, selection: String, selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String, limit: String, loadClass: Class<*>): ArrayList<Any> {
        return MappingClass().CursorToArray(query(context, database, table, colums, selection, selectionArgs, groupBy, having, orderBy, limit)!!, loadClass)
    }


    /**
     * 修改数据库的数据的方法
     *
     * 支持传参为对象 和 ContentValues
     */


    /*************************************************************         **************************************************************/
    /**
     * 更新数据库的数据 更新全部的数据

     * @param database 数据库的名称
     * *
     * @param table    数据库表的名称
     * *
     * @param object   更新的对象
     * *
     * @return 返回执行状态1为成功
     */
    fun UPDATE(database: String, table: String, `object`: Any): Int? {
        return update(context, database, table, MappingClass().ClassToContentValue(`object`.javaClass, `object`), "", null!!)
    }

    /**
     * 更新数据库的数据  更新指定的数据

     * @param database    数据库的名称
     * *
     * @param table       数据库表的名称
     * *
     * @param whereclause where后面指定的字段名
     * *
     * @param whereargs   where后面指定的字段名的值
     * *
     * @param object      更新的对象
     * *
     * @return 返回执行的状态1为成功
     */
    fun UPDATA(database: String, table: String, whereclause: Array<String>, whereargs: Array<String>, `object`: Any): Int? {
        return update(context, database, table, MappingClass().ClassToContentValue(`object`.javaClass, `object`), ClassHandler.ArrayToString(whereclause), whereargs)
    }


    /**
     * 自动判断数据库是更新还是插入的操作
     * 如果数据库的数据没有该条数据则执行 插入，
     * 如果有该条数据则进行更新操作

     * @param database    数据库的名称
     * *
     * @param table       数据库表的名称
     * *
     * @param whereclause where后面的字段名
     * *
     * @param whereargs   where后面的字段名的值
     * *
     * @param object      更新的对象
     * *
     * @return 返回的执行状态  1 为更新成功
     */
    fun INSERTorUPDATE(database: String, table: String, whereclause: Array<String>, whereargs: Array<String>, `object`: Any): Int? {
        //如果isEmptry返回来的是true 说明没有该数据这个时候应该是插入数据
        if (isEmptry(database, table, whereclause, whereargs))
            return insert(context, database, table, MappingClass().ClassToContentValue(`object`.javaClass, `object`))
        return update(context, database, table, MappingClass().ClassToContentValue(`object`.javaClass, `object`), ClassHandler.ArrayToString(whereclause), whereargs)
    }


    /**
     * 自动判断数据库是更新还是插入的操作
     * 如果数据库的数据没有该条数据则执行 插入，
     * 如果有该条数据则进行更新操作

     * @param database      数据库的名称
     * *
     * @param table         数据库表的名称
     * *
     * @param whereclause   where后面的字段名
     * *
     * @param whereargs     where后面的字段名的值
     * *
     * @param contentValues 插入数据或者更新数据的对象
     * *
     * @return 返回的执行状态  1 为更新成功
     */
    fun INSERTorUPDATE(database: String, table: String, whereclause: Array<String>, whereargs: Array<String>, contentValues: ContentValues): Int? {
        //如果isEmptry返回来的是true 说明没有该数据这个时候应该是插入数据
        if (isEmptry(database, table, whereclause, whereargs))
            return insert(context, database, table, contentValues)
        return update(context, database, table, contentValues, ClassHandler.ArrayToString(whereclause), whereargs)
    }

    /**
     * 表的结构发生变化处理方法
     */

    /*********************************            **************************************************/

    /**
     * 添加表的字段

     * @param database 数据库的名称
     * *
     * @param table    数据库表的名称
     * *
     * @param values   传输的数据
     */
    fun ALTERTABLE(database: String, table: String, values: Array<String>?) {
        if (values != null && values.size > 0)
            for (value in values) {
                onUpgrade(context, database, table, value)
            }
    }


    /**
     * 判断该数据库表的是否存在此条数据

     * @param database    数据库的名称
     * *
     * @param table       数据库表的名称
     * *
     * @param whereclause where后面指定的字段名
     * *
     * @param whereargs   where后面指定的字段名的值
     * *
     * @return 返回boolean  true 为空， false 不为空
     */
    fun isEmptry(database: String, table: String, whereclause: Array<String>, whereargs: Array<String>): Boolean {
        val cursor = query(context, database, table, null!!, ClassHandler.ArrayToString(whereclause), whereargs, "", "", "", "")
        if (cursor == null || cursor.count == 0)
            return true
        return false
    }

}