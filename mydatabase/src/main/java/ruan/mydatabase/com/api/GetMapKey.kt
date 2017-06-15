package ruan.mydatabase.com.api

/**
 * Created by 19820 on 2017/6/15.
 */
class GetMapKey {


    fun getMapKey(map: MutableMap<String , String>?) : ArrayList<String>{

        val key : ArrayList<String> = ArrayList<String>()

        var set : MutableSet<String>? = map?.keys
        var muIt : MutableIterator<String>? = set?.iterator()
        var it : Iterator<String>? = muIt?.iterator()

        while(it!!.hasNext()){
            key.add(it.next())
        }
        return key
    }

}