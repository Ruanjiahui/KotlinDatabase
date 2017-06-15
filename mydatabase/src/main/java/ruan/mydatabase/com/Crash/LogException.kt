package ruan.mydatabase.com.Crash

import android.util.Log

/**
 * Created by 19820 on 2017/6/15.
 */
class LogException {

    companion object{
        var Top = ""

        fun ThrowRunTime(msg: String) {
            Log.e(Top, msg, Throwable())
        }
    }
}