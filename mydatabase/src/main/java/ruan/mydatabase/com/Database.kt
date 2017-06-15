package ruan.mydatabase.com

import ruan.mydatabase.com.api.Establish

/**
 * Created by 19820 on 2017/6/15.
 */
interface Database {

    interface Check {

        /**
         * 自动创建表的接口
         * @param database      数据库的名称
         * *
         * @param table         数据库的表名称
         * *
         * @param state         数据库的表是否存在
         * *
         * @return              返回一个创建数据的对象
         */
        fun CreateTable(database: String, table: String, state: Boolean): Establish


    }

}