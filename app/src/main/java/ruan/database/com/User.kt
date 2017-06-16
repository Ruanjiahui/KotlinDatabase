package ruan.database.com

/**
 * Created by 19820 on 2017/6/16.
 */
class User {

    private var name: String = ""

    private var id: Int = 0

    fun setName(name: String) {
        this.name = name
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String {
        return name
    }

    fun getId(): Int {
        return id
    }

}