package ruan.mydatabase.com.api

import kotlin.collections.MutableMap.MutableEntry


/**
 * Created by ruanjiahui on 2017/6/15.
 */
class Establish {


    var map: MutableMap<String , String>? = null

    init {
        map = mutableMapOf()
    }

    fun put(key: String, value: String) {
        map?.put(key , value)
    }


    fun get(key: String): String? {
        return map?.get(key)
    }

    fun getKey(): ArrayList<String> {
        return GetMapKey().getMapKey(map)
    }
}